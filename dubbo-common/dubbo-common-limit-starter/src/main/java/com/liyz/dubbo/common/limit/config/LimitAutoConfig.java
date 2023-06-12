package com.liyz.dubbo.common.limit.config;

import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.common.limit.aop.CaffeineLimitPointcutAdvisor;
import com.liyz.dubbo.common.limit.aop.LimitAnnotationInterceptor;
import com.liyz.dubbo.common.limit.context.LimitContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/6 11:27
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "request.limit", name = "enable", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties({LimitProperties.class})
public class LimitAutoConfig implements ApplicationListener<ContextRefreshedEvent> {

    private final LimitProperties properties;

    public LimitAutoConfig(LimitProperties properties) {
        this.properties = properties;
        log.info("module dubbo-common-limit-starter init");
    }

    @Bean
    public LimitAnnotationInterceptor limitAnnotationInterceptor() {
        return new LimitAnnotationInterceptor();
    }

    @Bean
    public CaffeineLimitPointcutAdvisor caffeineLimitPointcutAdvisor() {
        return new CaffeineLimitPointcutAdvisor(limitAnnotationInterceptor());
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        RequestMappingHandlerMapping handlerMapping = event.getApplicationContext().getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        if (Objects.isNull(handlerMapping)) {
            return;
        }
        handlerMapping.getHandlerMethods().forEach((k, v) -> {
            if (v.hasMethodAnnotation(Limits.class)) {
                Limits limits = v.getMethodAnnotation(Limits.class);
                Limit[] limitArray = limits.value();
                if (Objects.nonNull(limitArray) && limitArray.length > 0) {
                    Arrays.stream(limitArray).forEach(limit -> k.getPatternsCondition().getPatterns().stream().forEach(mapping -> LimitContext.putLimit(mapping, limit)));
                }
            }
        });
    }
}
