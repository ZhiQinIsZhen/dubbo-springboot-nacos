package com.liyz.dubbo.common.api.advice;

import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.service.constant.CommonServiceConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Objects;

/**
 * Desc:global exception advice
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/9 9:28
 */
@Slf4j
@Order()
@RestControllerAdvice
public class GlobalControllerExceptionAdvice {

    @ExceptionHandler({Exception.class})
    public Result<String> exception(Exception exception) {
        log.error("未知异常", exception);
        return Result.error(CommonExceptionCodeEnum.REMOTE_SERVICE_FAIL);
    }

    @ExceptionHandler({BindException.class})
    public Result<String> bindException(BindException exception) {
        if (Objects.nonNull(exception) && exception.hasErrors()) {
            List<ObjectError> errors = exception.getAllErrors();
            for (ObjectError error : errors) {
                if (error.contains(TypeMismatchException.class) && error instanceof FieldError) {
                    return Result.error(CommonExceptionCodeEnum.PARAMS_VALIDATED.getCode(), ((FieldError) error).getField() + "类型转化失败");
                }
                return Result.error(CommonExceptionCodeEnum.PARAMS_VALIDATED.getCode(), error.getDefaultMessage());
            }
        }
        return Result.error(CommonExceptionCodeEnum.PARAMS_VALIDATED);
    }

    @ExceptionHandler({ValidationException.class})
    public Result<String> validationException(ValidationException exception) {
        String[] message = exception.getMessage().split(CommonServiceConstant.DEFAULT_JOINER);
        if (message.length >= 2) {
            return Result.error(CommonExceptionCodeEnum.PARAMS_VALIDATED.getCode(), message[1].trim());
        }
        return Result.error(CommonExceptionCodeEnum.PARAMS_VALIDATED);
    }

    @ExceptionHandler({RemoteServiceException.class})
    public Result<String> remoteServiceException(RemoteServiceException exception) {
        return Result.error(exception.getCode(), exception.getMessage());
    }
}
