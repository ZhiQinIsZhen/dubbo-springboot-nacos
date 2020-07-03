package com.liyz.dubbo.common.controller.advice;

import com.liyz.dubbo.common.base.result.Result;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;

/**
 * 注释:异常统一处理
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/15 13:41
 */
@Slf4j
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class ControllerExceptionHandleAdvice {

    @ExceptionHandler({Exception.class})
    public Result exception(Exception exception) {
        log.error("未知异常", exception);
        return Result.error(CommonCodeEnum.UnknownException);
    }

    @ExceptionHandler({RpcException.class})
    public Result rpcException(RpcException exception) {
        log.error("远程服务调用异常->rpc", exception);
        return Result.error(CommonCodeEnum.RemoteServiceFail);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public Result validationException(Exception exception) {
        BindingResult result;
        if (exception instanceof MethodArgumentNotValidException) {
            result = ((MethodArgumentNotValidException) exception).getBindingResult();
        } else {
            result = ((BindException) exception).getBindingResult();
        }
        if (Objects.nonNull(result) && result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                log.warn("参数校验 {} ：{}", error.getCodes()[0], error.getDefaultMessage());
                return Result.error(CommonCodeEnum.validated.getCode(), error.getDefaultMessage());
            }
        }
        log.error("参数校验出错了");
        return Result.error(CommonCodeEnum.validated);
    }

    @ExceptionHandler({RemoteServiceException.class})
    public Result remoteServiceException(RemoteServiceException exception) {
        log.error("远程服务调用异常->remote", exception);
        return Result.error(exception.getCode(), exception.getMessage());
    }

}
