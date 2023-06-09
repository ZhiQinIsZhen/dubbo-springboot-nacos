package com.liyz.dubbo.common.limit.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionService;

/**
 * 注释:限流异常类枚举值
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 11:12
 */
public enum LimitExceptionCodeEnum implements IExceptionService {
    OUT_LIMIT_COUNT("12001", "超出最大访问限制"),
    LIMIT_REQUEST("12002", "限制访问"),
    NON_SUPPORT_LIMIT_TYPE("12003", "不支持当前限流类型"),
    ;

    private final String code;

    private final String message;

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
