package com.liyz.dubbo.common.cache.task;

import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.liyz.dubbo.common.cache.core.RedisCaffeineCache;
import com.liyz.dubbo.common.cache.core.RedisCaffeineCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.ConcurrentMap;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/15 14:52
 */
@Slf4j
@Component
public class MonitorTask {

    @Autowired
    private RedisCaffeineCacheManager cacheManager;

    @Scheduled(initialDelay = 60000, fixedRate = 300000)
    public void monitor() {
        ConcurrentMap<String, Cache> cacheMap = cacheManager.getCacheMap();
        if (CollectionUtils.isEmpty(cacheMap)) {
            return;
        }
        cacheMap.forEach((key, cache) -> {
            RedisCaffeineCache redisCaffeineCache = (RedisCaffeineCache) cache;
            CacheStats cacheStats = redisCaffeineCache.getCaffeineCache().stats();
            log.info("cache name : {}, 命中次数 : {}", key, cacheStats.hitCount());
            log.info("cache name : {}, 缓存命中率 : {}", key, cacheStats.hitRate());
            log.info("cache name : {}, 未命中次数 : {}", key, cacheStats.missCount());
            log.info("cache name : {}, 未命中率 : {}", key, cacheStats.missRate());
            log.info("cache name : {}, 加载成功的次数 : {}", key, cacheStats.loadSuccessCount());
            log.info("cache name : {}, 加载失败的次数 : {}", key, cacheStats.loadFailureCount());
            log.info("cache name : {}, 总加载时间(ns) : {}", key, cacheStats.totalLoadTime());
            log.info("cache name : {}, 驱逐次数 : {}", key, cacheStats.evictionCount());
            log.info("cache name : {}, 驱逐的weight值总和 : {}", key, cacheStats.evictionWeight());
            log.info("cache name : {}, 单次load平均耗时 : {}", key, cacheStats.averageLoadPenalty());
        });

    }
}
