package com.liyz.dubbo.service.auth.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionService;
import lombok.AllArgsConstructor;

/**
 * Desc:2开头的错误码
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/9 14:15
 */
@AllArgsConstructor
public enum AuthExceptionCodeEnum implements IExceptionService {
    FORBIDDEN("401", "登录后进行操作"),
    NO_RIGHT("403", "暂无权限"),
    NOT_FOUND("404", "NOT_FOUND"),
    LOGIN_FAIL("20001", "用户名或者密码错误"),
    AUTHORIZATION_FAIL("20002", "认证失败"),
    AUTHORIZATION_TIMEOUT("20003", "认证过期"),
    REGISTRY_ERROR("20004", "注册错误"),
    LACK_SOURCE_ID("20005", "注册错误: 缺少资源客户端ID"),
    NON_SET_SOURCE_ID("20006", "注册错误: 资源服务未配置该资源客户端ID"),
    LOGIN_ERROR("20007", "登录错误"),
    LOGIN_ERROR_SOURCE_ID("20008", "登录错误: 缺少资源客户端ID"),
    OTHERS_LOGIN("20009", "该账号已在其他地方登录"),
    MOBILE_EXIST("20010", "该手机号码已注册"),
    EMAIL_EXIST("20011", "该邮箱地址已注册"),
    USER_NOT_EXIST("20012", "当前用户不存在"),
    ;

    private final String code;

    private final String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
