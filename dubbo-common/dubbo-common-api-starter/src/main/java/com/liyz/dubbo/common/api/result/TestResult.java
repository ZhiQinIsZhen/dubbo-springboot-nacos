package com.liyz.dubbo.common.api.result;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.common.remote.exception.IExceptionService;
import io.swagger.annotations.ApiModelProperty;
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
public class TestResult<T, V> {

    @ApiModelProperty(value = "code码")
    private String code;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "结果")
    private V value;

    @ApiModelProperty(value = "数据体")
    private T data;



    public TestResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public TestResult(T data) {
        this(CommonExceptionCodeEnum.SUCCESS);
        this.data = data;
    }

    public TestResult(IExceptionService codeEnum) {
        this(codeEnum.getCode(), codeEnum.getMessage());
    }

    public static <E, G> TestResult<E, G> success(E data) {
        return new TestResult<>(data);
    }

    public static <E, G> TestResult<E, G> success() {
        return success(null);
    }

    public static <E, G> TestResult<E, G> error(IExceptionService codeEnum) {
        return new TestResult<>(codeEnum);
    }

    public static <E, G> TestResult<E, G> error(String code, String message) {
        return new TestResult<>(code, message);
    }
}
