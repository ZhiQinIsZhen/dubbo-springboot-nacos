package com.liyz.dubbo.common.cache.core;

import com.github.benmanes.caffeine.cache.Cache;
import com.liyz.dubbo.common.cache.config.CacheRedisCaffeineProperties;
import com.liyz.dubbo.common.cache.message.CacheMessage;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.cache.support.AbstractValueAdaptingCache;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/14 17:01
 */
@Slf4j
public class RedisCaffeineCache extends AbstractValueAdaptingCache {

    private static final String REDIS_TYPE = "redis";
    private static final String MEMORY_TYPE = "memory";
    private static final String ALL_TYPE = "all";

    /**
     * 缓存名称
     */
    private String name;
    /**
     * 缓存类型
     */
    private String type;
    /**
     * 客户端
     */
    private RedissonClient redissonClient;
    /**
     * 缓存容器
     */
    @Getter
    private Cache<Object, Object> caffeineCache;
    /**
     * 前缀
     */
    private String cachePrefix;
    /**
     * 默认的过期时间
     */
    private long defaultExpiration = 0;
    /**
     * 对应的过期时间map
     */
    private Map<String, Long> expires;
    /**
     * 缓存更新时通知其他节点的topic名称
     */
    private String topic = "cache:redis:caffeine:topic";

    public RedisCaffeineCache(String name, RedissonClient redissonClient, Cache<Object, Object> caffeineCache,
                              CacheRedisCaffeineProperties properties) {
        super(properties.isCacheNullValues());
        this.name = name;
        this.type = properties.getType();
        this.redissonClient = redissonClient;
        this.caffeineCache = caffeineCache;
        this.cachePrefix = properties.getRedis().getKeyPrefix();
        this.defaultExpiration = properties.getRedis().getDefaultExpiration();
        this.expires = properties.getRedis().getExpires();
        this.topic = properties.getRedis().getTopic();
    }

    @Override
    protected Object lookup(Object key) {
        switch (type) {
            case REDIS_TYPE:
                return lookupRedis(key);
            case MEMORY_TYPE:
                return lookupMemory(key);
            case ALL_TYPE:
                return lookupAll(key);
            default:
                throw new RemoteServiceException(CommonExceptionCodeEnum.CACHE_TYPE_ERROR);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    /**
     * 获取缓存
     *
     * @param key
     * @param valueLoader
     * @param <T>
     * @return
     */
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        Object value = lookup(key);
        if (Objects.nonNull(value)) {
            return (T) value;
        }
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            value = lookup(key);
            if (Objects.nonNull(value)) {
                return (T) value;
            }
            value = valueLoader.call();
            Object storeValue = toStoreValue(value);
            put(key, storeValue);
            return (T) value;
        } catch (Exception e) {
            if (e instanceof RemoteServiceException) {
                throw (RemoteServiceException) e;
            }
            log.error("RedisCaffeineCache.get error", e);
            throw new RemoteServiceException(CommonExceptionCodeEnum.REMOTE_SERVICE_FAIL);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 存储缓存
     *
     * @param key
     * @param value
     */
    @Override
    public void put(Object key, Object value) {
        if (!isAllowNullValues() && Objects.isNull(value)) {
            this.evict(key);
            return;
        }
        switch (type) {
            case REDIS_TYPE:
                putRedis(key, value);
                break;
            case MEMORY_TYPE:
                putMemory(key, value);
                break;
            case ALL_TYPE:
                putAll(key, value);
                break;
            default:
                throw new RemoteServiceException(CommonExceptionCodeEnum.CACHE_TYPE_ERROR);
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return super.putIfAbsent(key, value);
    }

    /**
     * 失效缓存
     * 一定要先清除redis，再清除本地缓存，防止极端情况出现
     *
     * @param key
     */
    @Override
    public void evict(Object key) {
        redissonClient.getBucket(getKey(key), JsonJacksonCodec.INSTANCE).delete();
        push(new CacheMessage(this.name, key));
        clearLocal(key);
    }

    /**
     * 清除缓存
     */
    @Override
    public void clear() {
        //todo 清楚redis中的数据

        push(new CacheMessage(this.name, null));
        clearLocal(null);
    }

    /**
     * 获取缓存key
     *
     * @param key
     * @return
     */
    private String getKey(Object key) {
        return this.name.concat(":").concat(StringUtils.isEmpty(cachePrefix)
                ? key.toString() : cachePrefix.concat(":").concat(key.toString()));
    }

    private Object lookupRedis(Object key) {
        String cacheKey = getKey(key);
        Object value = redissonClient.getBucket(cacheKey, JsonJacksonCodec.INSTANCE).get();
        if (Objects.nonNull(value)) {
            if (log.isDebugEnabled()) {
                log.debug("get cache from redis, the key is : {}", key.toString());
            }
        }
        return value;
    }

    private Object lookupMemory(Object key) {
        Object value = caffeineCache.getIfPresent(key);
        if (Objects.nonNull(value)) {
            if (log.isDebugEnabled()) {
                log.debug("get cache from caffeine, the key is : {}", key.toString());
            }
        }
        return value;
    }

    private Object lookupAll(Object key) {
        Object value = lookupMemory(key);
        if (Objects.nonNull(value)) {
            return value;
        }
        value = lookupRedis(key);
        if (Objects.nonNull(value)) {
            if (log.isDebugEnabled()) {
                log.debug("get cache from redis and put in caffeine, the key is : {}", key.toString());
            }
            caffeineCache.put(key, value);
        }
        return value;
    }

    private void putRedis(Object key, Object value) {
        long expire = getExpire();
        if(expire > 0) {
            redissonClient.getBucket(getKey(key), JsonJacksonCodec.INSTANCE).set(toStoreValue(value), expire, TimeUnit.MILLISECONDS);
        } else {
            redissonClient.getBucket(getKey(key), JsonJacksonCodec.INSTANCE).set(toStoreValue(value));
        }
        push(new CacheMessage(this.name, key));
    }

    private void putMemory(Object key, Object value) {
        caffeineCache.put(key, value);
    }

    private void putAll(Object key, Object value) {
        putRedis(key, value);
        putMemory(key, value);
    }

    /**
     * 获取过期时间
     *
     * @return
     */
    private long getExpire() {
        long expire = defaultExpiration;
        Long cacheNameExpire = expires.get(this.name);
        return Objects.isNull(cacheNameExpire) ? expire : cacheNameExpire.longValue();
    }

    /**
     * 推送消息
     *
     * @param message
     */
    private void push(CacheMessage message) {
        redissonClient.getTopic(topic, JsonJacksonCodec.INSTANCE).publish(message);
    }

    /**
     * 清理本地缓存
     *
     * @param key
     */
    public void clearLocal(Object key) {
        if (log.isDebugEnabled()) {
            log.debug("clear local cache, the key is : {}", key);
        }
        if(Objects.isNull(key)) {
            caffeineCache.invalidateAll();
        } else {
            caffeineCache.invalidate(key);
        }
    }
}
