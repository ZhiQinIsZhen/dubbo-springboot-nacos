package com.liyz.dubbo.service.sms.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionCodeService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;

/**
 * 注释:sms自定义业务异常
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 15:12
 */
public class RemoteSmsServiceException extends RemoteServiceException {

    public RemoteSmsServiceException() {
    }

    public RemoteSmsServiceException(String message) {
        super(message);
    }

    public RemoteSmsServiceException(String message, String code) {
        super(message, code);
    }

    public RemoteSmsServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteSmsServiceException(IExceptionCodeService codeService) {
        super(codeService.getMessage(), codeService.getCode());
    }
}
