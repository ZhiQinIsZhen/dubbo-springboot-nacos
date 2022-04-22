package com.liyz.dubbo.common.limit.service;

import com.liyz.dubbo.common.limit.annotation.Limit;

/**
 * 注释:限流类型最底层接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 10:48
 */
public interface LimitKeyService {

    /**
     * 获取限流key
     *
     * @param limit
     * @return
     */
    String getKey(Limit limit);

    /**
     * 获取限流的总值
     *
     * @param limit
     * @return
     */
    double getTotalCount(Limit limit);
}
