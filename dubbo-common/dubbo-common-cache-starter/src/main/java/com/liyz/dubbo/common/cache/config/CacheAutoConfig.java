package com.liyz.dubbo.common.cache.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/19 14:23
 */
@Slf4j
@EnableCaching
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class CacheAutoConfig implements ApplicationListener<ContextRefreshedEvent> {

    private static final String DEFAULT_JOINER = ":";

    public CacheAutoConfig() {
        log.info("module dubbo-common-cache-starter init");
    }

    @Bean
    RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        return RedisCacheConfiguration.defaultCacheConfig()
                .computePrefixWith(cacheName -> cacheName + DEFAULT_JOINER)
                .prefixCacheNameWith(redisProperties.getKeyPrefix())
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(redisProperties.getTimeToLive() != null ? redisProperties.getTimeToLive() : Duration.ofMinutes(5));


    }

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient, CacheProperties cacheProperties) {
        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(new RedissonConnectionFactory(redissonClient))
                .cacheDefaults(redisCacheConfiguration(cacheProperties))
                .transactionAware()
                .build();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
