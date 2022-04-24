package com.liyz.dubbo.security.core.annotation;

import java.lang.annotation.*;

/**
 * 注释:免授权访问，但需要登陆
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 17:49
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonAuthority {
}
