package com.liyz.dubbo.common.security.annotation;

import java.lang.annotation.*;

/**
 * 注释:免授权访问，但需要登陆
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/21 14:37
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonAuthority {
}
