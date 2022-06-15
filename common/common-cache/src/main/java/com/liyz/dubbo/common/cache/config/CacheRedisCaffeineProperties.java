package com.liyz.dubbo.common.cache.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/14 16:51
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.cache.multi")
public class CacheRedisCaffeineProperties {

    /**
     * 缓存名称
     */
    private Set<String> cacheNames = new HashSet<>();

    /**
     * 缓存类型：redis、memory、all
     * redis：只使用redis作为缓存容器
     * memory：只使用内存作为缓存容器
     * all：都使用
     */
    private String type = "memory";

    /**
     * 是否动态根据cacheName创建Cache的实现，默认true
     */
    private boolean dynamic = true;

    /**
     * 是否存储空值，默认true，防止缓存穿透
     */
    private boolean cacheNullValues = true;

    private Redis redis = new Redis();

    private Caffeine caffeine = new Caffeine();

    private boolean monitor = true;

    @Getter
    @Setter
    public class Caffeine {

        /**
         * 访问后过期时间，单位毫秒
         */
        private long expireAfterAccess;

        /**
         * 写入后过期时间，单位毫秒
         */
        private long expireAfterWrite;

        /**
         * 写入后刷新时间，单位毫秒
         */
        private long refreshAfterWrite;

        /**
         * 初始化大小
         */
        private int initialCapacity = 100;

        /**
         * 最大缓存对象个数，超过此数量时之前放入的缓存将失效
         */
        private long maximumSize = 1000;
    }

    @Getter
    @Setter
    public class Redis {

        /**
         * key的前缀
         */
        private String keyPrefix;

        /**
         * 全局过期时间，单位毫秒，默认不过期
         */
        private long defaultExpiration = 0;

        /**
         * 每个cacheName的过期时间，单位毫秒，优先级比defaultExpiration高
         */
        private Map<String, Long> expires = new HashMap<>();

        /**
         * 缓存更新时通知其他节点的topic名称
         */
        private String topic = "cache:redis:caffeine:topic";
    }
}
