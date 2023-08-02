package com.liyz.dubbo.common.api.advice;

import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/31 19:53
 */
@RestControllerAdvice
public class LyzApiResponseBodyAdvice implements ResponseBodyAdvice<Result> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getMethod().getReturnType() == Result.class) {
            return true;
        }
        return false;
    }

    @Override
    public Result beforeBodyWrite(Result body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Object data;
        if (body != null && (data = body.getData()) != null) {
            body.setData(JsonMapperUtil.readTree(data));
        }
        return body;
    }
}
