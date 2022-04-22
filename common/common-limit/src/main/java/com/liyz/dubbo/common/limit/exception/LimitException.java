package com.liyz.dubbo.common.limit.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionCodeService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;

/**
 * 注释:限流异常
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 11:11
 */
public class LimitException extends RemoteServiceException {
    private static final long serialVersionUID = -8506210752717476534L;

    public LimitException() {
    }

    public LimitException(String message) {
        super(message);
    }

    public LimitException(String message, String code) {
        super(message, code);
    }

    public LimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public LimitException(IExceptionCodeService codeService) {
        super(codeService);
    }
}
