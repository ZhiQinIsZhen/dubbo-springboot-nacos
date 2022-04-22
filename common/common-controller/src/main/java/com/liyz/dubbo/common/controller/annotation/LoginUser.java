package com.liyz.dubbo.common.controller.annotation;

import java.lang.annotation.*;

/**
 * 注释:通过参数解析器注解
 * @deprecated 已丢弃，只是为了测试使用
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 14:30
 */
@Deprecated
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
}
