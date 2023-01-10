package com.liyz.dubbo.service.dfa.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionCodeService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;

/**
 * 注释:dfa自定义业务异常
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 15:12
 */
public class RemoteDfaServiceException extends RemoteServiceException {

    public RemoteDfaServiceException() {
    }

    public RemoteDfaServiceException(String message) {
        super(message);
    }

    public RemoteDfaServiceException(String message, String code) {
        super(message, code);
    }

    public RemoteDfaServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteDfaServiceException(IExceptionCodeService codeService) {
        super(codeService.getMessage(), codeService.getCode());
    }
}
