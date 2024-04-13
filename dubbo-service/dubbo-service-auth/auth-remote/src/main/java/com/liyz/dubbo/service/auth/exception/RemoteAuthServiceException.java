package com.liyz.dubbo.service.auth.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/9 14:18
 */
public class RemoteAuthServiceException extends RemoteServiceException {
    private static final long serialVersionUID = -373141891138519211L;

    public RemoteAuthServiceException() {
        super();
    }

    public RemoteAuthServiceException(IExceptionService codeService) {
        super(codeService);
    }
}
