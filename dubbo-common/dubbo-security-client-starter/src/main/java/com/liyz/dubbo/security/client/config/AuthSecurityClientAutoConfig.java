package com.liyz.dubbo.security.client.config;

import com.liyz.dubbo.security.client.constant.SecurityClientConstant;
import com.liyz.dubbo.security.client.context.AuthContext;
import com.liyz.dubbo.security.client.filter.JwtAuthenticationTokenFilter;
import com.liyz.dubbo.security.client.handler.JwtAuthenticationEntryPoint;
import com.liyz.dubbo.security.client.handler.RestfulAccessDeniedHandler;
import com.liyz.dubbo.security.client.user.impl.UserDetailsServiceImpl;
import com.liyz.dubbo.service.auth.remote.RemoteAuthService;
import com.liyz.dubbo.service.auth.remote.RemoteJwtParseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/13 20:19
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthSecurityClientAutoConfig implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("module dubbo-security-client-starter init");
    }

    @Bean
    public AuthContext authContext() {
        return new AuthContext();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public AnonymousMappingConfig anonymousMappingConfig() {
        return new AnonymousMappingConfig();
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    @DependsOn({"anonymousMappingConfig"})
    @ConditionalOnMissingBean(SecurityFilterChain.class)
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        log.info("configure init");
        http
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(new RestfulAccessDeniedHandler())
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, SecurityClientConstant.OPTIONS_PATTERNS).permitAll()
                .antMatchers(HttpMethod.GET, SecurityClientConstant.ACTUATOR_IGNORE_RESOURCES).permitAll()
                .antMatchers(HttpMethod.GET, SecurityClientConstant.KNIFE4J_IGNORE_RESOURCES).permitAll()
                /**
                 * {@link com.lyz.security.auth.client.annotation.Anonymous}注解的mappings
                 */
                .antMatchers(AnonymousMappingConfig.getAnonymousMappings().toArray(new String[0])).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(
                        new JwtAuthenticationTokenFilter(SecurityClientConstant.DEFAULT_TOKEN_HEADER_KEY),
                        UsernamePasswordAuthenticationFilter.class)
                .headers().cacheControl()
                .and().frameOptions().sameOrigin();
        return http.build();
    }

    @DubboReference(group = SecurityClientConstant.DUBBO_AUTH_GROUP)
    private RemoteAuthService remoteAuthService;
    @DubboReference(group = SecurityClientConstant.DUBBO_AUTH_GROUP)
    private RemoteJwtParseService remoteJwtParseService;

    @Bean(SecurityClientConstant.AUTH_SERVICE_BEAN_NAME)
    public RemoteAuthService remoteAuthService() {
        return this.remoteAuthService;
    }

    @Bean(SecurityClientConstant.JWT_SERVICE_BEAN_NAME)
    public RemoteJwtParseService remoteJwtParseService() {
        return this.remoteJwtParseService;
    }
}
