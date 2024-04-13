package com.liyz.dubbo.service.user.exception;


import com.liyz.dubbo.common.remote.exception.IExceptionService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 9:32
 */
public class RemoteUserServiceException extends RemoteServiceException {
    private static final long serialVersionUID = -5631823158974550846L;

    public RemoteUserServiceException() {
        super();
    }

    public RemoteUserServiceException(IExceptionService codeService) {
        super(codeService);
    }
}
