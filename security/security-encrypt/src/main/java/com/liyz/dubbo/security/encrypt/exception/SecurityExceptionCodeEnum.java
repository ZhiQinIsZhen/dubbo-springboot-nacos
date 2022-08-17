package com.liyz.dubbo.security.encrypt.exception;

import com.liyz.dubbo.common.remote.exception.IExceptionCodeService;
import lombok.AllArgsConstructor;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/15 16:21
 */
@AllArgsConstructor
public enum SecurityExceptionCodeEnum implements IExceptionCodeService {

    ENCRYPT_ERROR("60001", "加密失败"),
    DECRYPT_ERROR("60002", "解密失败"),
    ALGORITHM_SAME_ERROR("60003", "加解密算法重复"),
    CREATE_KEY_ERROR("60004", "秘钥创建失败"),
    DATA_TIME_OUT("60005", "数据有效期超时"),
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
