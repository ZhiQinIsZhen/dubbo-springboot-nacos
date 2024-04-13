package com.liyz.dubbo.common.service.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 9:32
 */
public class CommonServiceException extends RemoteServiceException {
    private static final long serialVersionUID = 1837626257703473095L;

    public CommonServiceException() {
        super();
    }

    public CommonServiceException(IExceptionService codeService) {
        super(codeService);
    }
}
