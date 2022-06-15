package com.liyz.dubbo.common.cache.core;

import lombok.extern.slf4j.Slf4j;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/15 10:43
 */
@Slf4j
public class RedisCacheManagerCustomizer implements CacheManagerCustomizer<RedissonSpringCacheManager> {

    @Override
    public void customize(RedissonSpringCacheManager cacheManager) {
        log.warn("警告 警告 ------------------------------------------------");
    }
}
