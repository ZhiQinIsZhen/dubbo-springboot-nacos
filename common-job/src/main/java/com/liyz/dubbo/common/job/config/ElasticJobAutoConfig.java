package com.liyz.dubbo.common.job.config;

import com.liyz.dubbo.common.job.annotation.ElasticJob;
import com.liyz.dubbo.common.job.annotation.EnableElasticJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.dataflow.job.DataflowJob;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 注释:elastic job 自动配置类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/24 14:26
 */
@Slf4j
@Configuration
@ConditionalOnClass({SimpleJob.class, DataflowJob.class})
@ConditionalOnBean(annotation = EnableElasticJob.class)
@AutoConfigureAfter(RegCentreAutoConfig.class)
public class ElasticJobAutoConfig implements InitializingBean, DisposableBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Autowired
    CoordinatorRegistryCenter coordinatorRegistryCenter;

    /**
     * 注：用法：可以从环境中获取配置好的属性，可设置优先级
     * String cron = getEnvironmentStringValue(jobName, "cron", elasticJob.cron());
     */
    private Environment environment;
    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public void afterPropertiesSet() throws Exception {
        this.environment = this.applicationContext.getEnvironment();
        log.info("can ElasticJob Annotation start");
        //扫描目标类
        Map<String, Object> beanMap = this.applicationContext.getBeansWithAnnotation(ElasticJob.class);
        if (beanMap != null && beanMap.size() > 0) {
            for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
                this.initElasticJobBean(entry.getKey(), entry.getValue());
            }
        }
        log.info("Scan ElasticJob Annotation end, job count:{}", counter.get());
    }

    @Override
    public void destroy() throws Exception {
        environment = null;
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private void initElasticJobBean(String beanName, Object bean) throws BeansException {
        Class<?> clz = bean.getClass();
        ElasticJob elasticJob = clz.getAnnotation(ElasticJob.class);
        JobConfiguration configuration = JobConfiguration
                .newBuilder(elasticJob.jobName(), elasticJob.shardingTotalCount())
                .cron(elasticJob.cron())
                .shardingItemParameters(elasticJob.shardingItemParameters())
                .jobParameter(elasticJob.jobParameter())
                .monitorExecution(elasticJob.monitorExecution())
                .failover(elasticJob.failover())
                .misfire(elasticJob.misfire())
                .maxTimeDiffSeconds(elasticJob.maxTimeDiffSeconds())
                .reconcileIntervalMinutes(elasticJob.reconcileIntervalMinutes())
                .jobShardingStrategyType(elasticJob.jobShardingStrategyType())
                .jobExecutorServiceHandlerType(elasticJob.jobExecutorServiceHandlerType())
                .jobErrorHandlerType(elasticJob.jobErrorHandlerType())
                .description(elasticJob.description())
                .disabled(elasticJob.disabled())
                .overwrite(elasticJob.overwrite())
                .build();
        if (bean instanceof SimpleJob) {
            new ScheduleJobBootstrap(coordinatorRegistryCenter, (SimpleJob)bean, configuration).schedule();
            counter.getAndIncrement();
        }
    }
}
