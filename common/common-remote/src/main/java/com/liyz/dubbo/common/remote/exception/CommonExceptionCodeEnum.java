package com.liyz.dubbo.common.remote.exception;

/**
 * 注释:通用异常枚举类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 13:45
 */
public enum CommonExceptionCodeEnum implements IExceptionCodeService{

    SUCCESS("0", "成功"),
    FAIL("1", "失败"),
    FORBIDDEN("401", "登陆后进行操作"),
    NO_RIGHT("403", "暂无权限"),
    PARAMS_VALIDATED("10000", "参数校验失败"),
    UNKNOWN_EXCEPTION("10001", "未知异常"),
    AUTHORIZATION_FAIL("10002", "认证失败"),
    AUTHORIZATION_TIMEOUT("10003", "认证超时"),
    LOGIN_FAIL("10004", "用户名或者密码错误"),
    REMOTE_SERVICE_FAIL("10005", "服务异常"),
    NO_DATA("10006", "暂无数据"),
    PARAMS_ERROR("10008", "参数异常"),
    IMAGE_CODE_ERROR("10010", "图片验证码不正确"),
    MOBILE_CODE_ERROR("10011", "短信验证码不正确"),
    EMAIL_CODE_ERROR("10012", "邮件验证码不正确"),
    OLD_FILE_NOT_EXIST("10013", "原文件不存在"),
    OTHERS_LOGIN("10014", "该账户已在其他地方登陆"),
    ;

    private String code;

    private String message;

    CommonExceptionCodeEnum(String code, String message) {
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
