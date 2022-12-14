package com.liyz.dubbo.common.limit.service;

import com.google.common.collect.Maps;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.limit.exception.LimitException;
import com.liyz.dubbo.common.limit.exception.LimitExceptionCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Pointcut;

import java.util.*;

/**
 * 注释:限流抽象类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 10:34
 */
@Slf4j
public abstract class AbstractLimitService implements LimitService {

    private static ThreadLocal<Double> permitsPerSecond = new InheritableThreadLocal<>();

    private static Map<LimitType, LimitKeyService> map = Maps.newHashMap();

    /**
     * 切点
     */
    @Pointcut("@annotation(com.liyz.dubbo.common.limit.annotation.Limits)")
    public void aspect() {}

    public static Double getCount() {
        return permitsPerSecond.get();
    }

    public static void setCount(Double count) {
        permitsPerSecond.set(count);
    }

    public static void removeCount() {
        permitsPerSecond.remove();
    }

    /**
     * 限流判断
     *
     * @param limits
     */
    protected void limit(Limits limits) {
        Limit[] limitArray = limits.value();
        if (limitArray == null || limitArray.length == 0) {
            return;
        }
        Set<LimitType> set = new HashSet<>();
        Arrays.stream(limitArray).forEach(limit -> {
            if (Objects.nonNull(limit) && !set.contains(limit.type()) && (LimitType.TOTAL != limit.type() || limit.count() >= 0)) {
                LimitKeyService limitKeyService = AbstractLimitKeyService.getService(limit.type());
                String key;
                if (Objects.isNull(limitKeyService) || StringUtils.isBlank(key = limitKeyService.getKey(limit))) {
                    throw new LimitException(LimitExceptionCodeEnum.NON_SUPPORT_LIMIT_TYPE);
                }
                double count = limitKeyService.getTotalCount(limit);
                if (count <= 0) {
                    throw new LimitException(LimitExceptionCodeEnum.LIMIT_REQUEST);
                }
                try {
                    permitsPerSecond.set(count);
                    if (!getCacheLimit(key).tryAcquire()) {
                        log.error("key:{} --> 触发了限流，每秒只能允许 {} 次访问", key, count);
                        throw new LimitException(LimitExceptionCodeEnum.OUT_LIMIT_COUNT);
                    }
                } finally {
                    permitsPerSecond.remove();
                }
                set.add(limit.type());
            }
        });
    }
}
