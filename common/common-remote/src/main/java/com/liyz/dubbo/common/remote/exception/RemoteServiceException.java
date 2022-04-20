package com.liyz.dubbo.common.remote.exception;

/**
 * 注释:自定义业务异常
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 14:08
 */
public class RemoteServiceException extends RuntimeException{
    private static final long serialVersionUID = -3338288508571700269L;

    /**
     * 获取异常code
     */
    private String code;

    public RemoteServiceException() {
    }

    public RemoteServiceException(String message) {
        super(message);
        this.code = getCode(message);
    }

    public RemoteServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public RemoteServiceException(String message, Throwable cause) {
        super(message, cause);
        this.code = getCode(message);
    }

    public RemoteServiceException(IExceptionCodeService codeService) {
        super(codeService.getMessage());
        this.code = codeService.getCode();
    }

    private String getCode(String message) {
        if (message != null && CommonExceptionCodeEnum.SUCCESS.getMessage().equals(message.trim())) {
            return CommonExceptionCodeEnum.SUCCESS.getCode();
        }
        return CommonExceptionCodeEnum.FAIL.getCode();
    }
}
