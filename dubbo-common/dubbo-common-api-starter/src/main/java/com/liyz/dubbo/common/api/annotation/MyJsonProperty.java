package com.liyz.dubbo.common.api.annotation;

import java.lang.annotation.*;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/27 16:43
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MyJsonProperty {

    String value() default "";
}
