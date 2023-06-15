package com.liyz.dubbo.service.staff.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 9:32
 */
public class RemoteStaffServiceException extends RemoteServiceException {
    private static final long serialVersionUID = 1L;

    public RemoteStaffServiceException() {
        super();
    }

    public RemoteStaffServiceException(IExceptionService codeService) {
        super(codeService);
    }
}
