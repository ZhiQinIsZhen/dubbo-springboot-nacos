package com.liyz.dubbo.security.client.advice;

import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.service.auth.exception.AuthExceptionCodeEnum;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Desc:auth exception advice
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/9 14:20
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class AuthExceptionHandleAdvice {

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public Result<String> badCredentialsException(Exception exception) {
        return Result.error(AuthExceptionCodeEnum.LOGIN_FAIL);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public Result<String> accessDeniedException(Exception exception) {
        return Result.error(AuthExceptionCodeEnum.NO_RIGHT);
    }
}
