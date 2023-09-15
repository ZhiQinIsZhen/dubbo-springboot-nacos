package com.liyz.dubbo.common.desensitize.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;

/**
 * 注释:限流异常
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 11:11
 */
public class DesensitizeException extends RemoteServiceException {
    private static final long serialVersionUID = -8506210752717476534L;

    public DesensitizeException() {
        super();
    }

    public DesensitizeException(IExceptionService codeService) {
        super(codeService);
    }
}
