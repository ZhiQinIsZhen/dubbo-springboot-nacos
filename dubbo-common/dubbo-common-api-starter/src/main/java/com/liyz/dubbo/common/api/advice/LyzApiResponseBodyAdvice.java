package com.liyz.dubbo.common.api.advice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.common.desensitize.filter.JacksonDesensitizationContextValueFilter;
import com.liyz.dubbo.common.util.DateUtil;
import com.liyz.dubbo.common.util.serializer.DoubleSerializer;
import com.liyz.dubbo.common.util.serializer.LyzBeanSerializerModifier;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/31 19:53
 */
@RestControllerAdvice
public class LyzApiResponseBodyAdvice implements ResponseBodyAdvice<Result> {

    private static final ObjectMapper LYZ_OBJECT_MAPPER = Jackson2ObjectMapperBuilder
            .json()
            .createXmlMapper(false)
            .dateFormat(new SimpleDateFormat(DateUtil.PATTERN_DATE_TIME))
            .timeZone(TimeZone.getTimeZone(DateUtil.TIME_ZONE_GMT8))
            .build()
            .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false)
            .registerModule(new SimpleModule()
                    .addSerializer(Long.class, ToStringSerializer.instance)
                    .addSerializer(Long.TYPE, ToStringSerializer.instance)
                    .addSerializer(Double.class, new DoubleSerializer())
                    .addSerializer(Double.TYPE, new DoubleSerializer())
                    .addSerializer(String.class, new JacksonDesensitizationContextValueFilter())
                    .setSerializerModifier(new LyzBeanSerializerModifier())
            );

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
            body.setData(readTree(data));
        }
        return body;
    }

    @SneakyThrows
    private static Object readTree(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }
        char head;
        if (obj.getClass() == String.class && ((head = ((String) obj).charAt(0)) == '[' || head == '{')) {
            return LYZ_OBJECT_MAPPER.readTree((String) obj);
        }
        try {
            return LYZ_OBJECT_MAPPER.readTree(LYZ_OBJECT_MAPPER.writeValueAsString(obj));
        } catch (Exception e) {
            return obj;
        }
    }
}
