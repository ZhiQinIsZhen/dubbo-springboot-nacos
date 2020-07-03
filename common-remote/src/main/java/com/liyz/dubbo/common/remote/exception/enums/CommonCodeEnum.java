package com.liyz.dubbo.common.remote.exception.enums;

import com.liyz.dubbo.common.remote.exception.service.ServiceCodeEnum;

/**
 * 注释: 返回信息状态码
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/5 16:05
 */
public enum CommonCodeEnum implements ServiceCodeEnum {

    success("0", "成功"),
    FORBIDDEN("401", "暂无权限"),
    validated("10000", "参数校验失败"),
    UnknownException("10001", "未知异常"),
    AuthorizationFail("10002", "认证失败"),
    AuthorizationTimeOut("10003", "认证超时"),
    LoginFail("10004", "用户名或者密码错误"),
    RemoteServiceFail("10005", "服务异常"),
    NoData("10006", "暂无数据"),
    ParameterError("10008", "参数异常"),
    LimitCount("1009", "超出最大访问限制"),
    ;

    private String code;

    private String message;

    CommonCodeEnum(String code, String message) {
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
