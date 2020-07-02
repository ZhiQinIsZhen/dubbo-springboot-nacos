package com.liyz.dubbo.common.base.result;

import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import com.liyz.dubbo.common.remote.exception.service.ServiceCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 注释:消息返回体
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/5 15:59
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 8507520853225734536L;

    private String code;

    private String message;

    private T data;

    public Result() {}

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(T data) {
        this(CommonCodeEnum.success);
        this.data = data;
    }

    public Result(ServiceCodeEnum codeEnum) {
        this(codeEnum.getCode(), codeEnum.getMessage());
    }

    public static <E> Result<E> success(E data) {
        return new Result<E>(data);
    }

    public static <E> Result<E> success() {
        return success(null);
    }

    public static <E> Result<E> error(ServiceCodeEnum codeEnum) {
        return new Result<E>(codeEnum);
    }

    public static <E> Result<E> error(String code, String message) {
        return new Result<E>(code, message);
    }
}
