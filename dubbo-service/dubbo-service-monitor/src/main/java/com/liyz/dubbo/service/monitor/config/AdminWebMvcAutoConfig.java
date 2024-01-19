package com.liyz.dubbo.service.monitor.config;

import com.liyz.dubbo.security.client.constant.SecurityClientConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/26 20:34
 */
@Slf4j
@Configuration
@AutoConfigureOrder(value = Ordered.HIGHEST_PRECEDENCE)
public class AdminWebMvcAutoConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("WYF-Thread-");
        taskExecutor.setDaemon(true);
        return taskExecutor;
    }

    @Bean
    public SecurityFilterChain monitorConfigure(HttpSecurity http) throws Exception {
        log.info("monitorConfigure init");
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, SecurityClientConstant.OPTIONS_PATTERNS).permitAll()
                .antMatchers(SecurityClientConstant.ACTUATOR_IGNORE_RESOURCES).permitAll()
                .antMatchers(HttpMethod.GET, SecurityClientConstant.ADMIN_IGNORE_RESOURCES).permitAll()
                .antMatchers(SecurityClientConstant.ADMIN_LOGIN).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(SecurityClientConstant.ADMIN_LOGIN).successHandler(successHandler).and()
                .logout().logoutUrl(SecurityClientConstant.ADMIN_LOGOUT)
                .and()
                .httpBasic()
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers(SecurityClientConstant.ACTUATOR_IGNORE_RESOURCES);
        return http.build();
    }
}
