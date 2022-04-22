package com.liyz.dubbo.common.limit.service.impl;

import com.liyz.dubbo.common.core.util.HttpRequestUtil;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.limit.service.AbstractLimitKeyService;
import org.springframework.stereotype.Service;

/**
 * 注释:ip限流
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 10:43
 */
@Service
public class IPLimitKeyServiceImpl extends AbstractLimitKeyService {

    @Override
    protected LimitType getLimitType() {
        return LimitType.IP;
    }

    @Override
    public String getKey(Limit limit) {
        return HttpRequestUtil.getIpAddress();
    }

    @Override
    public double getTotalCount(Limit limit) {
        return limit.count();
    }
}
