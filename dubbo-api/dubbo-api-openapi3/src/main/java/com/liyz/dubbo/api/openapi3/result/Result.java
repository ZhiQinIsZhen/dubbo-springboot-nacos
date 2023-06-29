package com.liyz.dubbo.api.openapi3.result;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/9 9:42
 */
@Getter
@Setter
@JsonPropertyOrder({"code", "message", "data"})
public class Result<T> {

    @Parameter(description = "code码")
    private String code;

    @Parameter(description = "消息")
    private String message;

    @Parameter(description = "数据体")
    private T data;

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(T data) {
        this("0", "success");
        this.data = data;
    }

    public static <E> Result<E> success(E data) {
        return new Result<>(data);
    }

    public static <E> Result<E> success() {
        return success(null);
    }

    public static <E> Result<E> error(String code, String message) {
        return new Result<>(code, message);
    }
}
