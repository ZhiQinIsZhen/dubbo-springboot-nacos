package com.liyz.dubbo.common.apollo.config;

import com.ctrip.framework.apollo.spring.boot.ApolloAutoConfiguration;
import com.ctrip.framework.apollo.spring.config.PropertySourcesConstants;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/10 11:11
 */
@Slf4j
@Configuration
@ConditionalOnClass(EncryptablePropertySource.class)
@ConditionalOnProperty(PropertySourcesConstants.APOLLO_BOOTSTRAP_ENABLED)
@AutoConfigureBefore(ApolloAutoConfiguration.class)
public class JasyptApolloAutoConfiguration {

    /**
     * 定义PropertySourcesProcessor代替apollo的默认Bean
     */
    @Bean
    public JasyptPropertySourcesProcessor jasyptConfigPropertySourcesProcessor() {
        log.info("@@Configure JasyptPropertySourcesProcessor");
        return new JasyptPropertySourcesProcessor();
    }
}
