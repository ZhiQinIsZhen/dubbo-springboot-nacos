package com.liyz.dubbo.service.dfa.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionCodeService;
import lombok.AllArgsConstructor;

/**
 * 注释:dfa异常枚举类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 15:08
 */
@AllArgsConstructor
public enum DfaExceptionCodeEnum implements IExceptionCodeService {
    HAS_END_CHAR("70001", "初始化字库中含有#特殊字符"),
    ;

    private String code;

    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
