package com.liyz.dubbo.security.client.advice;

import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 注释:security client端异常统一处理advice
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 9:55
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(annotations = {RestController.class, Controller.class, Service.class})
public class AuthExceptionHandleAdvice {

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public Result badCredentialsException(Exception exception) {
        return Result.error(CommonExceptionCodeEnum.LOGIN_FAIL);
    }
}
