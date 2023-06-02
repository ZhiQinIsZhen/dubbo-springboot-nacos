package com.liyz.dubbo.common.api.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.liyz.dubbo.common.api.annotation.Trim;

import java.io.IOException;
import java.util.Objects;

/**
 * Desc:{@link Trim}
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/9 10:32
 */
public class JsonTrimDeserializer extends JsonDeserializer<String> implements ContextualDeserializer {

    public JsonTrimDeserializer() {
    }

    public JsonTrimDeserializer(Trim annotation) {
        this.annotation = annotation;
    }

    private Trim annotation;

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return Objects.nonNull(annotation) && Objects.nonNull(jsonParser.getText()) ? jsonParser.getText().trim() : jsonParser.getText();
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {
        Trim trim = beanProperty.getAnnotation(Trim.class);
        return Objects.isNull(trim) ? this : new JsonTrimDeserializer(trim);
    }
}
