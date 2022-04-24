package com.liyz.dubbo.security.client.config;

import com.liyz.dubbo.security.client.core.RestfulAccessDeniedHandler;
import com.liyz.dubbo.security.core.remote.RemoteGrantedAuthorityCoreService;
import com.liyz.dubbo.security.core.remote.RemoteJwtAuthCoreService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 注释:security client 字段配置类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 9:58
 */
@Configuration
@ComponentScan(value = {"com.liyz.dubbo.security.client"})
public class SecurityClientAutoConfig {

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    @DubboReference(timeout = 10000)
    private RemoteJwtAuthCoreService remoteJwtAuthCoreService;
    @DubboReference(timeout = 10000)
    private RemoteGrantedAuthorityCoreService remoteGrantedAuthorityCoreService;

    @Bean
    public RemoteJwtAuthCoreService remoteJwtAuthCoreService() {
        return remoteJwtAuthCoreService;
    }

    @Bean
    public RemoteGrantedAuthorityCoreService remoteGrantedAuthorityCoreService() {
        return remoteGrantedAuthorityCoreService;
    }
}
