package com.liyz.dubbo.service.sms.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionCodeService;
import lombok.AllArgsConstructor;

/**
 * 注释:sms错误码枚举类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/28 14:39
 */
@AllArgsConstructor
public enum SmsExceptionCodeEnum implements IExceptionCodeService {
    IMAGE_ERROR("50001", "图片验证码错误"),
    MOBILE_ERROR("50002", "短信验证码错误"),
    EMAIL_ERROR("50003", "邮箱验证码错误"),
    ;

    private String code;

    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
