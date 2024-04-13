package com.liyz.dubbo.common.limit.annotation;

import java.lang.annotation.*;

/**
 * 注释:限流注解
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 10:30
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limits {

    Limit[] value() default {@Limit};

    /**
     * 限流功能描述，方便日后维护
     *
     * @return 描述
     */
    String description()  default "";
}
