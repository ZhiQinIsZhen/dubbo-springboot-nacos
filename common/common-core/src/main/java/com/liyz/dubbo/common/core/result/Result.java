package com.liyz.dubbo.common.core.result;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.common.remote.exception.IExceptionCodeService;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:消息返回体
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 20:58
 */
@Getter
@Setter
@JsonPropertyOrder({"code", "message", "data"})
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    private T data;

    public Result() {}

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(T data) {
        this(CommonExceptionCodeEnum.SUCCESS);
        this.data = data;
    }

    public Result(IExceptionCodeService codeEnum) {
        this(codeEnum.getCode(), codeEnum.getMessage());
    }

    public static <E> Result<E> success(E data) {
        return new Result<E>(data);
    }

    public static <E> Result<E> success() {
        return success(null);
    }

    public static <E> Result<E> error(IExceptionCodeService codeEnum) {
        return new Result<E>(codeEnum);
    }

    public static <E> Result<E> error(String code, String message) {
        return new Result<E>(code, message);
    }
}
