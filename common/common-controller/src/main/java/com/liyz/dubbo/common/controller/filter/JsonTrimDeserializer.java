package com.liyz.dubbo.common.controller.filter;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.liyz.dubbo.common.controller.annotation.Trim;

import java.io.IOException;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2023/1/9 16:34
 */

public class JsonTrimDeserializer extends JsonDeserializer<String> implements ContextualDeserializer {

    private Trim annotation;

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String result = jsonParser.getText();
        if (Objects.nonNull(annotation) && Objects.nonNull(result)) {
            result = result.trim();
        }
        return result;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        this.annotation = beanProperty.getAnnotation(Trim.class);
        return this;
    }
}
