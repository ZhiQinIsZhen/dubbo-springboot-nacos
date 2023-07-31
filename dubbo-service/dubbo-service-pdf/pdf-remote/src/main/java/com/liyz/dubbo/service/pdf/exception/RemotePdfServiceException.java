package com.liyz.dubbo.service.pdf.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 9:32
 */
public class RemotePdfServiceException extends RemoteServiceException {
    private static final long serialVersionUID = 1L;

    public RemotePdfServiceException() {
        super();
    }

    public RemotePdfServiceException(IExceptionService codeService) {
        super(codeService);
    }
}
