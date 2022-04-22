package com.liyz.dubbo.common.limit.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionCodeService;

/**
 * 注释:限流异常类枚举值
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 11:12
 */
public enum LimitExceptionCodeEnum implements IExceptionCodeService {
    OUT_LIMIT_COUNT("30001", "超出最大访问限制"),
    LIMIT_REQUEST("30002", "限制访问"),
    NON_SUPPORT_LIMIT_TYPE("30003", "不支持当前限流类型"),
    ;

    private String code;

    private String message;

    LimitExceptionCodeEnum(String code, String message) {
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
