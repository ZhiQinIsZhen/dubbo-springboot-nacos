package com.liyz.dubbo.common.limit.service.impl;

import com.liyz.dubbo.common.core.util.HttpRequestUtil;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.limit.service.AbstractLimitKeyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 注释:http servlet mapping限流
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 11:02
 */
@Service
public class MappingLimitKeyServiceImpl extends AbstractLimitKeyService {

    @Override
    protected LimitType getLimitType() {
        return LimitType.MAPPING;
    }

    @Override
    public String getKey(Limit limit) {
        HttpServletRequest httpServletRequest = HttpRequestUtil.getRequest();
        if (Objects.nonNull(httpServletRequest)) {
            return httpServletRequest.getServletPath();
        } else {
            return StringUtils.EMPTY;
        }
    }

    @Override
    public double getTotalCount(Limit limit) {
        return limit.count();
    }
}
