package com.liyz.dubbo.security.encrypt.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/15 15:16
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {

    @AliasFor("checkTime")
    boolean value() default false;

    @AliasFor("value")
    boolean checkTime() default false;

    long time() default 2*60*1000;
}
