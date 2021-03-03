package com.liyz.dubbo.common.base.request;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.threadlocal.InternalThreadLocal;

import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/3/3 14:37
 */
@Slf4j
public final class RequestIdContext {

    private static final InternalThreadLocal<RequestInfo> LOCAL_REQUEST = new InternalThreadLocal<>();

    protected RequestIdContext() {}

    /**
     * 获取RequestId
     *
     * @return
     */
    public static String getRequestId() {
        RequestInfo requestInfo = LOCAL_REQUEST.get();
        if (Objects.nonNull(requestInfo)) {
            return requestInfo.getRequestId();
        }
        return null;
    }

    /**
     * 设置RequestId
     *
     * @param requestId
     * @param method
     */
    public static void setRequestId(String requestId, String method) {
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setRequestId(requestId);
        requestInfo.setMethod(method);
        LOCAL_REQUEST.set(requestInfo);
    }

    /**
     * 移除RequestId
     */
    public static void removeRequestId() {
        RequestInfo requestInfo = LOCAL_REQUEST.get();
        if (Objects.nonNull(requestInfo)) {
            log.warn("requestId : {}, total request time : {} ms, method : {}",
                    requestInfo.getRequestId(),
                    System.currentTimeMillis() - requestInfo.getRequestTime(),
                    requestInfo.getMethod());
        }
    }
}
