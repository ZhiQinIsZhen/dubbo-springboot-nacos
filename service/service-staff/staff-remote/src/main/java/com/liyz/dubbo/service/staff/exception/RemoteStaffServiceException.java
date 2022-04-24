package com.liyz.dubbo.service.staff.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionCodeService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;

/**
 * 注释:staff自定义业务异常
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 15:12
 */
public class RemoteStaffServiceException extends RemoteServiceException {

    public RemoteStaffServiceException() {
    }

    public RemoteStaffServiceException(String message) {
        super(message);
    }

    public RemoteStaffServiceException(String message, String code) {
        super(message, code);
    }

    public RemoteStaffServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteStaffServiceException(IExceptionCodeService codeService) {
        super(codeService.getMessage(), codeService.getCode());
    }
}
