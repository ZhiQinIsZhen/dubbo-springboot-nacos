package com.liyz.dubbo.common.job.config;

import org.apache.shardingsphere.elasticjob.lite.spring.boot.job.ElasticJobProperties;
import org.apache.shardingsphere.elasticjob.lite.spring.boot.job.ScheduleJobBootstrapStartupRunner;
import org.apache.shardingsphere.elasticjob.lite.spring.boot.reg.ElasticJobRegistryCenterConfiguration;
import org.apache.shardingsphere.elasticjob.lite.spring.boot.reg.snapshot.ElasticJobSnapshotServiceConfiguration;
import org.apache.shardingsphere.elasticjob.lite.spring.boot.tracing.ElasticJobTracingConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 注释:自动配置
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/3 14:25
 */
@Configuration
@EnableConfigurationProperties({ElasticJobProperties.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@ConditionalOnProperty(name = {"elastic.job.enabled"}, havingValue = "true", matchIfMissing = true)
@Import({ElasticJobRegistryCenterConfiguration.class, ElasticJobTracingConfiguration.class, ElasticJobSnapshotServiceConfiguration.class})
public class CommonElasticJobLiteAutoConfig {

    @Configuration(proxyBeanMethods = false)
    @Import({ElasticJobBootstrapConfig.class, ScheduleJobBootstrapStartupRunner.class})
    protected static class ElasticJobConf {

    }
}
