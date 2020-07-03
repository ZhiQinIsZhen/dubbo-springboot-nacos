package com.liyz.dubbo.common.web.security.config;

import com.liyz.dubbo.common.base.service.LoginInfoService;
import com.liyz.dubbo.common.remote.service.RemoteJwtUserService;
import com.liyz.dubbo.common.web.security.core.JwtUserDetailsServiceImpl;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 注释: jwt bean 初始化
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/7 17:44
 */
@Configuration
public class MyBeansConfig {

    @DubboReference(version = "1.0.0")
    RemoteJwtUserService remoteJwtUserService;

    @Autowired
    LoginInfoService loginInfoService;

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService userDetailsService() {
        return new JwtUserDetailsServiceImpl(remoteJwtUserService, loginInfoService);
    }
}
