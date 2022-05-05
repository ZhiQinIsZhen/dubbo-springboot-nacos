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

    /**
     * 1.beginIndex=-1,endIndex=3,则表示最后3位脱敏
     * 2.beginIndex=3,endIndex=-1,则表示前3位脱敏
     * 3.beginIndex=1,endIndex=2,则表示第一位到第二位脱敏
     * 4.beginIndex=-1,endIndex=-1,则表示全部字符脱敏
     * @return
     */
    int beginIndex() default 0;

    int endIndex() default 0;
}
