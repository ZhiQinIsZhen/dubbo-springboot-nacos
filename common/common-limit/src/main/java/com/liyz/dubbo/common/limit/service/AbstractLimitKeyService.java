package com.liyz.dubbo.common.limit.service;

import com.google.common.collect.Maps;
import com.liyz.dubbo.common.limit.enums.LimitType;

import java.util.Map;

/**
 * 注释:限流类型抽象类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 10:51
 */
public abstract class AbstractLimitKeyService implements LimitKeyService {

    private static Map<LimitType, LimitKeyService> map = Maps.newHashMap();

    public static LimitKeyService getService(LimitType limitType) {
        return map.get(limitType);
    }

    public AbstractLimitKeyService() {
        map.put(getLimitType(), this);
    }

    protected abstract LimitType getLimitType();
}
