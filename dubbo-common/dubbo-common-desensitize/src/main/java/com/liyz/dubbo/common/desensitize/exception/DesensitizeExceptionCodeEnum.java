package com.liyz.dubbo.common.desensitize.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionService;

/**
 * 注释:限流异常类枚举值 (12开头的错误码)
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 11:12
 */
public enum DesensitizeExceptionCodeEnum implements IExceptionService {
    DEC_KEY_LENGTH_ERROR("13001", "DEC加密Key长度不能小于8位"),
    DEC_IV_LENGTH_ERROR("13002", "DEC加密IV长度必须等于8位"),
    AEC_KEY_LENGTH_ERROR("13003", "AEC加密Key长度必须为16位或24位或32位"),
    AEC_IV_LENGTH_ERROR("13004", "AEC加密IV长度必须等于16位"),
    ;

    private final String code;

    private final String message;

    DesensitizeExceptionCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
