package com.liyz.dubbo.common.controller.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * WebMvcConfigurerAdapter的方式进行跨域配置的问题：
 * spring security的过滤器链早于WebMvcConfigurerAdapter，导致跨域配置失效
 * 这里自定义跨域filter，并设置其优先级，早于security的过滤器执行
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/16 14:07
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyCorsFilterConfig extends CorsFilter {

    public MyCorsFilterConfig() {
        super(configurationSource());
    }

    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedOrigin("*");
        //corsConfig.setExposedHeaders(exposedHeaders);
        // corsConfig.setMaxAge(36000L);
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        log.info("init corsConfig success");
        return source;
    }
}
