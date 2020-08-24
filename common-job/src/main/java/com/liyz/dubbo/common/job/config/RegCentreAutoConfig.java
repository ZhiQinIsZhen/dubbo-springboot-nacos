package com.liyz.dubbo.common.job.config;

import com.liyz.dubbo.common.job.annotation.EnableElasticJob;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注释:注册配置类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/24 14:14
 */
@Configuration
@ConditionalOnClass(ZookeeperRegistryCenter.class)
@ConditionalOnBean(annotation = EnableElasticJob.class)
@EnableConfigurationProperties(RegCenterProperties.class)
public class RegCentreAutoConfig {

    @Autowired
    RegCenterProperties regCenterProperties;

    @Bean
    public CoordinatorRegistryCenter coordinatorRegistryCenter() {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(regCenterProperties.getServerLists(),
                regCenterProperties.getNamespace());
        zkConfig.setBaseSleepTimeMilliseconds(regCenterProperties.getBaseSleepTimeMilliseconds());
        zkConfig.setConnectionTimeoutMilliseconds(regCenterProperties.getConnectionTimeoutMilliseconds());
        zkConfig.setDigest(regCenterProperties.getDigest());
        zkConfig.setMaxRetries(regCenterProperties.getMaxRetries());
        zkConfig.setMaxSleepTimeMilliseconds(regCenterProperties.getMaxSleepTimeMilliseconds());
        zkConfig.setSessionTimeoutMilliseconds(regCenterProperties.getSessionTimeoutMilliseconds());
        CoordinatorRegistryCenter center = new ZookeeperRegistryCenter(zkConfig);
        center.init();
        return center;
    }
}
