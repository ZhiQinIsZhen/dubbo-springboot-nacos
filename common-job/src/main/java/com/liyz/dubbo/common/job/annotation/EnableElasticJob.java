package com.liyz.dubbo.common.job.annotation;

import java.lang.annotation.*;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/24 14:25
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableElasticJob {
}
