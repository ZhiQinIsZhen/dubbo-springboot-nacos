package com.liyz.dubbo.common.limit.service;

import com.google.common.base.Joiner;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.context.LimitContext;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.limit.exception.LimitException;
import com.liyz.dubbo.common.limit.exception.LimitExceptionCodeEnum;
import com.liyz.dubbo.common.service.constant.CommonServiceConstant;
import com.liyz.dubbo.common.service.util.HttpServletContext;
import lombok.extern.slf4j.Slf4j;

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
        HttpServletRequest httpServletRequest = HttpServletContext.getRequest();
        if (Objects.nonNull(httpServletRequest)) {
            String mapping = httpServletRequest.getServletPath();
            Limit limitAware = LimitContext.getLimit(mapping, limit);
            if (Objects.isNull(limitAware)) {
                log.info("mapping : {}, LimitType : {}, name : {}, 无对应包装Limit", mapping, limit.type().name(), limit.type().getDesc());
                return false;
            }
            Double totalCount;
            String key;
            if ((totalCount = getTotalCount(limitAware)) <= 0) {
                throw new LimitException(LimitExceptionCodeEnum.LIMIT_REQUEST);
            }
            try {
                LimitContext.setCount(totalCount);
                if (!LimitContext.getCacheLimit(key = getKey(limitAware)).tryAcquire()) {
                    log.warn("key:{} --> 触发了限流，每秒只能允许 {} 次访问", key, totalCount);
                    return true;
                }
            } finally {
                LimitContext.removeCount();
            }
        }
        return false;
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
}
