package com.liyz.dubbo.common.limit.service.impl;

import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.limit.service.AbstractLimitKeyService;
import org.springframework.stereotype.Service;

/**
 * 注释:总请求数限流
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 11:03
 */
@Service
public class TotalCountLimitKeyServiceImpl extends AbstractLimitKeyService {

    private static final String TOTAL_KEY = "request:limit:total";

    @Override
    protected LimitType getLimitType() {
        return LimitType.TOTAL;
    }

    @Override
    public String getKey(Limit limit) {
        return TOTAL_KEY;
    }

    @Override
    public double getTotalCount(Limit limit) {
        String value = System.getProperty("request.limit.totalCount", "1");
        return Double.valueOf(value);
    }
}
