package com.liyz.dubbo.security.encrypt.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionCodeService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/15 16:20
 */
public class RemoteSecurityServiceException extends RemoteServiceException {

    public RemoteSecurityServiceException() {
    }

    public RemoteSecurityServiceException(String message) {
        super(message);
    }

    public RemoteSecurityServiceException(String message, String code) {
        super(message, code);
    }

    public RemoteSecurityServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteSecurityServiceException(IExceptionCodeService codeService) {
        super(codeService.getMessage(), codeService.getCode());
    }
}
