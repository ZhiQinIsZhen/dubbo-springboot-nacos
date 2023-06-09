package com.liyz.dubbo.common.limit.service.impl;

import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.limit.service.AbstractLimitService;
import com.liyz.dubbo.common.service.util.HttpServletContext;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/9 16:00
 */
public class IPMappingLimitServiceImpl extends AbstractLimitService {

    @Override
    public String getKey(Limit limit) {
        return getKey(HttpServletContext.getIpAddress(), HttpServletContext.getRequest().getServletPath());
    }

    @Override
    protected LimitType getLimitType() {
        return LimitType.IP;
    }
}
