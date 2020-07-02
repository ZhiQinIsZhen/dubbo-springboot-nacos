package com.liyz.dubbo.common.remote.exception;


import com.liyz.dubbo.common.remote.exception.service.ServiceCodeEnum;

/**
 * 注释:自定义业务异常类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/29 14:04
 */
public class RemoteServiceException extends RuntimeException {
    private static final long serialVersionUID = 8892582001244967922L;
    /**
     * 统一异常编码
     */
    private String code;

    public RemoteServiceException() {
    }

    public RemoteServiceException(String message) {
        super(message);
    }

    public RemoteServiceException(String message, String code) {
        super(message);
        this.code = code;
    }

    public RemoteServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteServiceException(ServiceCodeEnum serviceCodeEnum) {
        super(serviceCodeEnum.getMessage());
        this.code = serviceCodeEnum.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
