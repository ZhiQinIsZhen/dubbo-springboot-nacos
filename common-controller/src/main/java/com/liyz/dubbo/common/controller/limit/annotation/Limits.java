package com.liyz.dubbo.common.controller.limit.annotation;

import java.lang.annotation.*;

/**
 * 注释: mapping限流注解
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/10 13:57
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limits {

    Limit[] value() default {@Limit};

    /**
     * 限流功能描述，方便日后维护
     *
     * @return
     */
    String description()  default "";


}
