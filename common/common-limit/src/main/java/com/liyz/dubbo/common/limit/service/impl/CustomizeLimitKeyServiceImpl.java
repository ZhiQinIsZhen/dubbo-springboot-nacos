package com.liyz.dubbo.common.limit.service.impl;

import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.limit.service.AbstractLimitKeyService;
import org.springframework.stereotype.Service;

/**
 * 注释:自定义限流
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 11:06
 */
@Service
public class CustomizeLimitKeyServiceImpl extends AbstractLimitKeyService {

    @Override
    protected LimitType getLimitType() {
        return LimitType.CUSTOMIZE;
    }

    @Override
    public String getKey(Limit limit) {
        return limit.key();
    }

    @Override
    public double getTotalCount(Limit limit) {
        return limit.count();
    }
}
