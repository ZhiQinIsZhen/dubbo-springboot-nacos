package com.liyz.dubbo.common.desensitize.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.util.DesensitizeUtil;

import java.io.IOException;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/5/24 15:54
 */
public class JacksonDesensitizationContextValueFilter extends JsonSerializer<String> implements ContextualSerializer {

    public JacksonDesensitizationContextValueFilter() {
    }

    public JacksonDesensitizationContextValueFilter(Desensitization annotation) {
        this.annotation = annotation;
    }

    private Desensitization annotation;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (annotation == null) {
            gen.writeString(value);
        } else {
            gen.writeString(DesensitizeUtil.getService(annotation.value()).desensitize(value, annotation));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        Desensitization desensitization = property.getAnnotation(Desensitization.class);
        return Objects.isNull(desensitization) ? this : new JacksonDesensitizationContextValueFilter(desensitization);
    }
}
