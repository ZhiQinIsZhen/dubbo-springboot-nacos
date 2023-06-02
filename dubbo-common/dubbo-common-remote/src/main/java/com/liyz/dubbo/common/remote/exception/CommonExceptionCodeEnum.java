package com.lyz.auth.common.remote.exception;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/8 15:47
 */
public enum CommonExceptionCodeEnum implements IExceptionService{

    SUCCESS("0", "成功"),
    FAIL("1", "失败"),
    PARAMS_VALIDATED("10000", "参数校验失败"),
    UNKNOWN_EXCEPTION("10001", "未知异常"),
    REMOTE_SERVICE_FAIL("10005", "服务异常"),
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
