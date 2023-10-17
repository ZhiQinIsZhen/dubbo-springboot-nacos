package com.liyz.dubbo.common.lock.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/10/17 11:08
 */
public class LockException extends RemoteServiceException {
    private static final long serialVersionUID = 8746746093948718766L;

    public LockException() {
        super();
    }

    public LockException(IExceptionService codeService) {
        super(codeService);
    }
}
