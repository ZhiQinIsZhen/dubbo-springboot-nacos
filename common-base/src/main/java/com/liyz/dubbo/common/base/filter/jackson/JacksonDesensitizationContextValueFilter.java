package com.liyz.dubbo.common.base.filter.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.liyz.dubbo.common.base.desen.annotation.Desensitization;
import com.liyz.dubbo.common.base.desen.service.DesensitizeService;

import java.io.IOException;

/**
 * 注释:自定义jackson脱敏过滤器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/6/2 16:22
 */
public class JacksonDesensitizationContextValueFilter extends JsonSerializer<String> implements ContextualSerializer, DesensitizeService {

    private Desensitization annotation;

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (annotation == null) {
            return;
        }
        jsonGenerator.writeString(desensitize(s, annotation));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        annotation = beanProperty.getAnnotation(Desensitization.class);
        return this;
    }
}
