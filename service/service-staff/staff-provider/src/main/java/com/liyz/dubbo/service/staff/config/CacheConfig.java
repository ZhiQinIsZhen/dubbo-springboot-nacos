package com.liyz.dubbo.service.staff.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/13 14:40
 */
@Slf4j
@Configuration
public class CacheConfig {

    @Bean("MyKeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                String key = method.getName() + ":" + Arrays.asList(objects);
                log.info("key:{}", key);
                return key;
            }
        };
    }

}
