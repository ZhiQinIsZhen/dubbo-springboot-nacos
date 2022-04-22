package com.liyz.dubbo.common.limit.service;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 注释:限流切面最底层接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 10:33
 */
public interface LimitService {

    /**
     * 获取限流信息
     *
     * @param key
     * @return
     */
    RateLimiter getCacheLimit(final String key);
}
