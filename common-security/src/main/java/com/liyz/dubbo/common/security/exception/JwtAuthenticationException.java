package com.liyz.dubbo.common.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 注释:认证错误信息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/18 20:07
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
