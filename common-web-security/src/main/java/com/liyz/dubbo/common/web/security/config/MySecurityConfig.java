package com.liyz.dubbo.common.web.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyz.dubbo.common.web.security.core.JwtAuthenticationEntryPoint;
import com.liyz.dubbo.common.web.security.core.RestfulAccessDeniedHandler;
import com.liyz.dubbo.common.web.security.core.filter.JwtAuthenticationTokenFilter;
import com.liyz.dubbo.common.web.security.util.AnonymousUrlsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * 注释:Security配置器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/7 18:23
 */
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<String> list = AnonymousUrlsUtil.anonymousUrls();
        String[] strings = list.toArray(new String[list.size()]);
        log.info("免鉴权api:{}", mapper.writeValueAsString(strings));
        http
                //由于使用的是JWT，我们这里不需要csrf，并配置entryPoint
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler())
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()).and()
                //基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //预请求不拦截
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //允许访问所有swagger的静态资源与接口
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/instances/**",
                        "/actuator/**",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                //配置可以匿名访问的api
                .antMatchers(strings).permitAll()
                //其余都需要鉴权认证
                .anyRequest().authenticated().and()
                //添加jwt过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 禁用缓存
                .headers().cacheControl().and()
                //spring security上使用ifame时候允许跨域
                .frameOptions().sameOrigin();
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }
}
