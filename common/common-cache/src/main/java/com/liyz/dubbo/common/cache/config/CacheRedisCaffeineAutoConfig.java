package com.liyz.dubbo.common.cache.config;

import com.liyz.dubbo.common.cache.core.RedisCacheManagerCustomizer;
import com.liyz.dubbo.common.cache.core.RedisCaffeineCacheManager;
import com.liyz.dubbo.common.cache.listener.RedisTopicListener;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;

/**
 * 注释:缓存auto config
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/14 16:30
 */
@EnableAsync(proxyTargetClass = true)
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties({CacheRedisCaffeineProperties.class})
public class CacheRedisCaffeineAutoConfig {

    @Autowired
    private CacheRedisCaffeineProperties cacheRedisCaffeineProperties;

    @Bean
    public RedisCaffeineCacheManager cacheManager(RedissonClient redissonClient) {
        return new RedisCaffeineCacheManager(cacheRedisCaffeineProperties, redissonClient);
    }

    @Bean
    public RedisCacheManagerCustomizer redisCacheManagerCustomizer() {
        return new RedisCacheManagerCustomizer();
    }

    @Bean
    public RedisTopicListener redisTopicListener() {
        return new RedisTopicListener();
    }

    /**
     * 默认的key值生成器
     *
     * @return
     */
    @Bean("defaultKeyGenerator")
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            String key = method.getName() + ":" + Arrays.asList(objects);
            return key;
        };
    }
}
