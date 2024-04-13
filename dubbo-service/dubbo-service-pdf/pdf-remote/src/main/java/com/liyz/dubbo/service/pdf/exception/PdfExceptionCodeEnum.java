package com.liyz.dubbo.service.pdf.exception;

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
public enum PdfExceptionCodeEnum implements IExceptionService {
    FRONT_ERROR("40001", "创建字体失败"),
    SVG_ERROR("40002", "生成SVG失败"),
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
