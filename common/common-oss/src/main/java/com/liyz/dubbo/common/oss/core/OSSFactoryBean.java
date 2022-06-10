package com.liyz.dubbo.common.oss.core;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/10 13:33
 */
@Slf4j
public class OSSFactoryBean implements FactoryBean<OSSBucket>, EnvironmentAware {

    private Environment environment;

    @Override
    public OSSBucket getObject() {
        log.info("@@init oss client");
        String name = environment.getProperty("application.oss.name");
        String endpoint = environment.getProperty(String.format("oss_%s_endpoint", name));
        String accessKeyId = environment.getProperty(String.format("oss_%s_accessKeyId", name));
        String secretAccessKey = environment.getProperty(String.format("oss_%s_secretAccessKey", name));
        String bucketName = environment.getProperty(String.format("oss_%s_bucketName", name));
        if (endpoint == null || accessKeyId == null || secretAccessKey == null || bucketName == null) {
            throw new BeanCreationException("未找到oss配置");
        }
        OSS oss = new OSSClientBuilder().build(endpoint, accessKeyId, secretAccessKey);
        return new OSSBucket(oss, bucketName);
    }

    @Override
    public Class<?> getObjectType() {
        return OSS.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
