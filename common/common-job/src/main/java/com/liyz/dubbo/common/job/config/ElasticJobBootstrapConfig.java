package com.liyz.dubbo.common.job.config;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.liyz.dubbo.common.job.annotation.ElasticJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.OneOffJobBootstrap;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.lite.spring.boot.job.ElasticJobConfigurationProperties;
import org.apache.shardingsphere.elasticjob.lite.spring.boot.job.ElasticJobProperties;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * 注释:扫描注解bean并注册到job中
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/3 14:26
 */
@Slf4j
public class ElasticJobBootstrapConfig implements ApplicationContextAware, BeanPostProcessor {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void createJobBootstrapBeans() {
        //获取配置信息
        ElasticJobProperties elasticJobProperties = this.applicationContext.getBean(ElasticJobProperties.class);
        //获取注解bean
        Map<String, Object> beanMap = this.applicationContext.getBeansWithAnnotation(ElasticJob.class);
        if (CollectionUtils.isEmpty(beanMap)) {
            log.info("get no beans from @ElasticJob ...");
            return;
        }
        log.info("get {} beans from @ElasticJob ...", beanMap.size());
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            Class<?> clz = entry.getValue().getClass();
            if (org.apache.shardingsphere.elasticjob.api.ElasticJob.class.isAssignableFrom(clz)) {
                ElasticJob elasticJob = clz.getAnnotation(ElasticJob.class);
                ElasticJobConfigurationProperties Properties = new ElasticJobConfigurationProperties();
                Properties.setElasticJobClass((Class<? extends org.apache.shardingsphere.elasticjob.api.ElasticJob>) clz);
                Properties.setCron(elasticJob.cron());
                Properties.setShardingTotalCount(elasticJob.shardingTotalCount());
                Properties.setShardingItemParameters(elasticJob.shardingItemParameters());
                Properties.setOverwrite(elasticJob.overwrite());
                elasticJobProperties.getJobs().put(entry.getKey(), Properties);
            }
        }
        SingletonBeanRegistry singletonBeanRegistry = ((ConfigurableApplicationContext)this.applicationContext).getBeanFactory();
        CoordinatorRegistryCenter registryCenter = this.applicationContext.getBean(CoordinatorRegistryCenter.class);
        TracingConfiguration<?> tracingConfig = this.getTracingConfiguration();
        this.constructJobBootstraps(elasticJobProperties, singletonBeanRegistry, registryCenter, tracingConfig);
    }

    /**
     * 获取trace config
     *
     * @return
     */
    private TracingConfiguration<?> getTracingConfiguration() {
        Map<String, TracingConfiguration> tracingConfigurationBeans = this.applicationContext.getBeansOfType(TracingConfiguration.class);
        if (tracingConfigurationBeans.isEmpty()) {
            return null;
        } else if (1 == tracingConfigurationBeans.size()) {
            return (TracingConfiguration)tracingConfigurationBeans.values().iterator().next();
        } else {
            throw new BeanCreationException("More than one [org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration] beans found. Consider disabling [org.apache.shardingsphere.elasticjob.tracing.boot.ElasticJobTracingAutoConfiguration].");
        }
    }

    /**
     * 构造属性
     *
     * @param elasticJobProperties
     * @param singletonBeanRegistry
     * @param registryCenter
     * @param tracingConfig
     */
    private void constructJobBootstraps(ElasticJobProperties elasticJobProperties, SingletonBeanRegistry singletonBeanRegistry, CoordinatorRegistryCenter registryCenter, TracingConfiguration<?> tracingConfig) {
        Iterator var5 = elasticJobProperties.getJobs().entrySet().iterator();

        while(var5.hasNext()) {
            Map.Entry<String, ElasticJobConfigurationProperties> entry = (Map.Entry)var5.next();
            ElasticJobConfigurationProperties jobConfigurationProperties = entry.getValue();
            Preconditions.checkArgument(null != jobConfigurationProperties.getElasticJobClass() || !Strings.isNullOrEmpty(jobConfigurationProperties.getElasticJobType()), "Please specific [elasticJobClass] or [elasticJobType] under job configuration.");
            Preconditions.checkArgument(null == jobConfigurationProperties.getElasticJobClass() || Strings.isNullOrEmpty(jobConfigurationProperties.getElasticJobType()), "[elasticJobClass] and [elasticJobType] are mutually exclusive.");
            if (null != jobConfigurationProperties.getElasticJobClass()) {
                this.registerClassedJob(entry.getKey(), (entry.getValue()).getJobBootstrapBeanName(), singletonBeanRegistry, registryCenter, tracingConfig, jobConfigurationProperties);
            } else if (!Strings.isNullOrEmpty(jobConfigurationProperties.getElasticJobType())) {
                this.registerTypedJob(entry.getKey(), (entry.getValue()).getJobBootstrapBeanName(), singletonBeanRegistry, registryCenter, tracingConfig, jobConfigurationProperties);
            }
        }
    }

    /**
     * 注册
     *
     * @param jobName
     * @param jobBootstrapBeanName
     * @param singletonBeanRegistry
     * @param registryCenter
     * @param tracingConfig
     * @param jobConfigurationProperties
     */
    private void registerClassedJob(String jobName, String jobBootstrapBeanName, SingletonBeanRegistry singletonBeanRegistry,
                                    CoordinatorRegistryCenter registryCenter, TracingConfiguration<?> tracingConfig,
                                    ElasticJobConfigurationProperties jobConfigurationProperties) {
        JobConfiguration jobConfig = jobConfigurationProperties.toJobConfiguration(jobName);
        Optional var10000 = Optional.ofNullable(tracingConfig);
        Collection var10001 = jobConfig.getExtraConfigurations();
        var10000.ifPresent(var10001::add);
        org.apache.shardingsphere.elasticjob.api.ElasticJob elasticJob = this.applicationContext.getBean(jobConfigurationProperties.getElasticJobClass());
        if (Strings.isNullOrEmpty(jobConfig.getCron())) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(jobBootstrapBeanName), "The property [jobBootstrapBeanName] is required for One-off job.");
            singletonBeanRegistry.registerSingleton(jobBootstrapBeanName, new OneOffJobBootstrap(registryCenter, elasticJob, jobConfig));
        } else {
            String beanName = !Strings.isNullOrEmpty(jobBootstrapBeanName) ? jobBootstrapBeanName : jobConfig.getJobName() + "ScheduleJobBootstrap";
            singletonBeanRegistry.registerSingleton(beanName, new ScheduleJobBootstrap(registryCenter, elasticJob, jobConfig));
        }
    }

    /**
     * 注册
     *
     * @param jobName
     * @param jobBootstrapBeanName
     * @param singletonBeanRegistry
     * @param registryCenter
     * @param tracingConfig
     * @param jobConfigurationProperties
     */
    private void registerTypedJob(String jobName, String jobBootstrapBeanName, SingletonBeanRegistry singletonBeanRegistry,
                                  CoordinatorRegistryCenter registryCenter, TracingConfiguration<?> tracingConfig,
                                  ElasticJobConfigurationProperties jobConfigurationProperties) {
        JobConfiguration jobConfig = jobConfigurationProperties.toJobConfiguration(jobName);
        Optional var10000 = Optional.ofNullable(tracingConfig);
        Collection var10001 = jobConfig.getExtraConfigurations();
        var10000.ifPresent(var10001::add);
        if (Strings.isNullOrEmpty(jobConfig.getCron())) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(jobBootstrapBeanName), "The property [jobBootstrapBeanName] is required for One-off job.");
            singletonBeanRegistry.registerSingleton(jobBootstrapBeanName, new OneOffJobBootstrap(registryCenter, jobConfigurationProperties.getElasticJobType(), jobConfig));
        } else {
            String beanName = !Strings.isNullOrEmpty(jobBootstrapBeanName) ? jobBootstrapBeanName : jobConfig.getJobName() + "ScheduleJobBootstrap";
            singletonBeanRegistry.registerSingleton(beanName, new ScheduleJobBootstrap(registryCenter, jobConfigurationProperties.getElasticJobType(), jobConfig));
        }
    }
}
