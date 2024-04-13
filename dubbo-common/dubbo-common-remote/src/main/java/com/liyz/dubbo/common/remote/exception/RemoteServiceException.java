package com.liyz.dubbo.common.remote.exception;

import lombok.Getter;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/8 15:43
 */
public class RemoteServiceException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public RemoteServiceException() {
        this(CommonExceptionCodeEnum.FAIL);
    }

    public RemoteServiceException(IExceptionService codeService) {
        super(codeService.getMessage());
        this.code = codeService.getCode();
    }

    /**
     * 异常code
     */
    @Getter
    private final String code;
}
