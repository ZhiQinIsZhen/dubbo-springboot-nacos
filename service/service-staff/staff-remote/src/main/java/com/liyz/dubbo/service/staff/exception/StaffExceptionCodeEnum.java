package com.liyz.dubbo.service.staff.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionCodeService;
import lombok.AllArgsConstructor;

/**
 * 注释:staff异常枚举类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 15:08
 */
@AllArgsConstructor
public enum StaffExceptionCodeEnum implements IExceptionCodeService {
    ACCOUNT_EXIST("40001", "该账号已注册"),
    REGISTER_ERROR("40002", "注册失败"),
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