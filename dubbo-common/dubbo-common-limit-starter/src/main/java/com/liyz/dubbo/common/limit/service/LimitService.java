package com.liyz.dubbo.common.limit.service;

import com.liyz.dubbo.common.limit.annotation.Limit;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/8 19:55
 */
public interface LimitService {

    /**
     * 获取限流key
     *
     * @param limit 限流注解
     * @return 返回对应的key
     */
    String getKey(Limit limit);

    /**
     * 获取限流的总值
     *
     * @param limit 限流注解
     * @return 限流阈值
     */
    default double getTotalCount(Limit limit) {
        return limit.count();
    }

    /**
     * 限流判断
     *
     * @param limit 限流注解
     * @return 是否达到阈值 true:限流; false:未限流
     */
     boolean limit(Limit limit);
}
