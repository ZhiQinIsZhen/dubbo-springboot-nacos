package com.liyz.dubbo.common.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/5/23 15:46
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiVersion {

    String[] group();
}
