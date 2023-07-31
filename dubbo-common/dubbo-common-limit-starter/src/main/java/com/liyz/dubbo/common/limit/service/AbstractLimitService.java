package com.liyz.dubbo.common.limit.service;

import com.google.common.base.Joiner;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.context.LimitContext;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.limit.exception.LimitException;
import com.liyz.dubbo.common.limit.exception.LimitExceptionCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/9 15:21
 */
@Slf4j
public abstract class AbstractLimitService implements LimitService {

    public AbstractLimitService() {
        LimitContext.putService(getLimitType(), this);
    }

    @Override
    public String getKey(Limit limit) {
        return this.getKey(limit.key());
    }

    @Override
    public boolean limit(Limit limit) {
        HttpServletRequest httpServletRequest = this.getRequest();
        String mapping = httpServletRequest.getServletPath();
        Limit limitAware = LimitContext.getLimit(mapping, limit);
        if (Objects.isNull(limitAware)) {
            log.info("mapping : {}, LimitType : {}, name : {}, 无对应包装Limit", mapping, limit.type().name(), limit.type().getDesc());
            return false;
        }
        long totalCount;
        if ((totalCount = getTotalCount(limitAware)) <= 0) {
            throw new LimitException(LimitExceptionCodeEnum.LIMIT_REQUEST);
        }
        try {
            LimitContext.setCount(totalCount);
            return !LimitContext.tryAcquire(getKey(limitAware));
        } finally {
            LimitContext.removeCount();
        }
    }

    /**
     * 限流类型
     *
     * @return 限流类型
     */
    protected abstract LimitType getLimitType();

    /**
     * 拼接keys
     *
     * @param keys keys
     * @return key
     */
    protected String getKey(String... keys) {
        return Joiner.on("_").join(keys);
    }

    /**
     * 获取HttpServletRequest
     *
     * @return HttpServletRequest
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    /**
     * 获取远程真实IP地址
     *
     * @return IP地址
     */
    protected String getRemoteIpAddr() {
        HttpServletRequest request = this.getRequest();
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ipAddress)|| "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("ProxyAop-Client-IP");
        }
        if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-ProxyAop-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress.split(",")[0];
    }
}
