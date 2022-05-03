package com.liyz.dubbo.common.job.annotation;

import java.lang.annotation.*;

/**
 * 注释:elastic job 注解
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/3 14:23
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticJob {

    String cron();

    int shardingTotalCount() default 1;

    String shardingItemParameters() default "";

    boolean overwrite() default true;
}
