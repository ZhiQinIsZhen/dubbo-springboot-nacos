package com.liyz.dubbo.common.desensitize.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import com.liyz.dubbo.common.desensitize.filter.JacksonDesensitizationContextValueFilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释:自定义脱敏注解
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 17:24
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = JacksonDesensitizationContextValueFilter.class)
@JacksonAnnotationsInside
public @interface Desensitization {

    DesensitizationType value() default DesensitizationType.DEFAULT;

    int beginIndex() default 0;

    int endIndex() default 0;

    /**
     * 只有当 value == DesensitizationType.ENCRYPT_DECRYPT 时，才会起作用
     * 0：加密；1：解密
     *
     * @return
     */
    int type() default 0;
}
