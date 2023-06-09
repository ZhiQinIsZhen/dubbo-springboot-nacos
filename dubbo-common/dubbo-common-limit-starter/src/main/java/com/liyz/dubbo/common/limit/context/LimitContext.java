package com.liyz.dubbo.common.limit.context;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.limit.service.LimitService;
import com.liyz.dubbo.common.limit.service.impl.CustomizationLimitServiceImpl;
import com.liyz.dubbo.common.limit.service.impl.IPLimitServiceImpl;
import com.liyz.dubbo.common.limit.service.impl.IPMappingLimitServiceImpl;
import com.liyz.dubbo.common.limit.service.impl.MappingLimitServiceImpl;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.MultiKeyMap;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/8 11:19
 */
@Slf4j
@UtilityClass
public class LimitContext {

    private static final LoadingCache<String, RateLimiter> CACHE = Caffeine.newBuilder()
            .maximumSize(1000)
            .initialCapacity(100)
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .removalListener((k, v, removalCause) -> log.info("key -> [{}], value -> [{}], 已被移除， case -> {}", k, v, removalCause))
            .build(key -> createRateLimiter());

    private static final Map<LimitType, LimitService> SERVICE_MAP = Maps.newEnumMap(LimitType.class);
    private static final MultiKeyMap<String, Limit> MULTI_KEY_MAP = new MultiKeyMap<>();
    private static final ThreadLocal<Double> PERMITS_PER_SECOND = new InheritableThreadLocal<>();

    static{
        new CustomizationLimitServiceImpl();
        new IPLimitServiceImpl();
        new IPMappingLimitServiceImpl();
        new MappingLimitServiceImpl();
    }

    /**
     * 获取限流信息
     *
     * @param key 缓存key
     * @return 对应的限流数据
     */
    public static RateLimiter getCacheLimit(final String key) {
        return CACHE.get(key);
    }

    /**
     * 获取对应的限流服务
     *
     * @param limitType 限流类型
     * @return 限流服务
     */
    public static LimitService getService(LimitType limitType) {
        LimitService limitService = SERVICE_MAP.get(limitType);
        if (Objects.isNull(limitService)) {
            log.warn("未配置该限流服务(限流类型:{})", limitType.getDesc());
        }
        return limitService;
    }

    /**
     * 存入对应的限流服务
     *
     * @param limitType 限流类型
     * @param service 限流服务
     */
    public static void putService(LimitType limitType, LimitService service) {
        SERVICE_MAP.put(limitType, service);
    }

    /**
     * 存放limit与mapping映射关系
     *
     * @param mapping request mapping
     * @param limit 限流limit
     */
    public static void putLimit(String mapping, Limit limit) {
        MULTI_KEY_MAP.put(mapping, limit.type().name(), limit);
    }

    /**
     * 获取limit与mapping映射关系
     *
     * <p>为什么这里需要从Map中重新获取，而不是取值参数 @param limit?
     * 是因为，为后续动态修改限流值做准备。如果是获取参数中的属性，则是不变的</p>
     *
     * @param mapping request mapping
     * @param limit 限流limit
     */
    public static Limit getLimit(String mapping, Limit limit) {
        return MULTI_KEY_MAP.get(mapping, limit.type().name());
    }

    /**
     * 获取限流阈值
     *
     * @return 限流阈值
     */
    public static Double getCount() {
        return PERMITS_PER_SECOND.get();
    }

    /**
     * 设置限流阈值
     *
     * @param count 限流阈值
     */
    public static void setCount(Double count) {
        PERMITS_PER_SECOND.set(count);
    }

    /**
     * 清除阈值上下文
     */
    public static void removeCount() {
        PERMITS_PER_SECOND.remove();
    }

    /**
     * 初始化限流器
     *
     * @return 限流器
     */
    private static RateLimiter createRateLimiter() {
        Double count = getCount();
        log.info("初始化限流器  count: {}", count);
        return RateLimiter.create(count);
    }
}
