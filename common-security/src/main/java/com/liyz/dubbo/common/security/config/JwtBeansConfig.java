package com.liyz.dubbo.common.security.config;

import com.liyz.dubbo.common.security.core.RestfulAccessDeniedHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 注释:jwt bean 初始化
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 17:46
 */
@Configuration
public class JwtBeansConfig {

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }
}
