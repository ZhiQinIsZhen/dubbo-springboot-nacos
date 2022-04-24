package com.liyz.dubbo.security.core.annotation;

import java.lang.annotation.*;

/**
 * 注释:匿名访问注解
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 17:48
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Anonymous {
}
