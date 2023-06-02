package com.liyz.dubbo.common.desensitize.annotation;

import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;

import java.lang.annotation.*;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/5/24 15:33
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Desensitization {

    DesensitizationType value() default DesensitizationType.DEFAULT;

    /**
     * <p>1.beginIndex=-1,endIndex=3,则表示最后3位脱敏
     *
     * <p>2.beginIndex=3,endIndex=-1,则表示前3位脱敏
     *
     * <p>3.beginIndex=1,endIndex=2,则表示第一位到第二位脱敏
     *
     * <p>4.beginIndex=-1,endIndex=-1,则表示全部字符脱敏
     */
    int beginIndex() default 0;

    int endIndex() default 0;
}
