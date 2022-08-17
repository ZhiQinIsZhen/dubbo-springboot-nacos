package com.liyz.dubbo.security.encrypt.config;

import com.liyz.dubbo.security.encrypt.annotation.Encrypt;
import com.liyz.dubbo.security.encrypt.remote.RemoteAlgorithmService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 注释:解密advice
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/17 9:47
 */
@RestControllerAdvice(annotations = RestController.class)
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    @DubboReference(timeout = 10000)
    private RemoteAlgorithmService remoteAlgorithmService;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasMethodAnnotation(Encrypt.class) ? true : false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return new DecryptHttpInputMessage(inputMessage, parameter.getMethodAnnotation(Encrypt.class), remoteAlgorithmService);
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
