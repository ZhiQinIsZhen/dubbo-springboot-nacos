package com.liyz.dubbo.common.apollo;

import com.ctrip.framework.apollo.spring.config.PropertySourcesConstants;
import com.liyz.dubbo.common.apollo.util.EnvironmentUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/10 11:15
 */
@Slf4j
public class AfterApollo implements EnvironmentPostProcessor, PropertySourcesConstants, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        if (environment.getProperty("jasypt.encryptor.password", "").isEmpty()) {
            // 如果启动参数没指定jasypt.encryptor.password则从Apollo加载
            EnvironmentUtil.addLast(environment, "secrets.system");
        }
        //%d{yyyy-MM-dd HH:mm:ss.SSS} ${hostname} [%thread] %highlight(%-5level) %logger{50}:%line - %msg%n
        EnvironmentUtil.addLast(environment, new ClassPathResource("LoggingTemplateConfig.properties"));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
