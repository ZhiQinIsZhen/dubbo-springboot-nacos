package com.liyz.dubbo.common.desensitize.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.strategy.AbstractDesensitizeService;

import java.io.IOException;

/**
 * 注释:自定义jackson脱敏过滤器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 17:26
 */
public class JacksonDesensitizationContextValueFilter extends JsonSerializer<String> implements ContextualSerializer {

    private Desensitization annotation;

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (annotation == null) {
            return;
        }
        jsonGenerator.writeString(AbstractDesensitizeService.getDesensitizeServiceByType(annotation.value()).desensitize(s, annotation));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        this.annotation = beanProperty.getAnnotation(Desensitization.class);
        return this;
    }
}
