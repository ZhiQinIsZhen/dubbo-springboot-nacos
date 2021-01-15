package com.liyz.dubbo.service.member.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Lists;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/1/15 10:12
 */
@Configuration
public class CaffeineConfig extends CachingConfigurerSupport {

    @Override
    @Bean(name = "cacheManager")
    public CacheManager cacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager();
        manager.setCaffeine(
                Caffeine
                        .newBuilder()
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .initialCapacity(100)
                        .maximumSize(1000)
        );
        return manager;
    }

    @Bean(name = "userCacheManager")
    public CacheManager userCacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager();
        manager.setAllowNullValues(false);
        manager.setCaffeine(
                Caffeine
                        .newBuilder()
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .initialCapacity(100)
                        .maximumSize(1000)
        );
        manager.setCacheNames(Lists.newArrayList("userCache"));
        return manager;
    }
}
