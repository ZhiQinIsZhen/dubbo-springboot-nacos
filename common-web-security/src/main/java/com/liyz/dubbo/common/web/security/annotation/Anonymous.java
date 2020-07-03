package com.liyz.dubbo.common.web.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释: 免鉴权注解
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/27 10:23
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Anonymous {
}
