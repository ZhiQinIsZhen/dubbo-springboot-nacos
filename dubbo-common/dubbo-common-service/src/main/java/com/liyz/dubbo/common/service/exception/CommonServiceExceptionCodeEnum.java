package com.liyz.dubbo.common.service.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionService;
import lombok.AllArgsConstructor;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 9:31
 */
@AllArgsConstructor
public enum CommonServiceExceptionCodeEnum implements IExceptionService {
    DEEP_COPY_ERROR("11001", "对象深拷贝错误"),
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
