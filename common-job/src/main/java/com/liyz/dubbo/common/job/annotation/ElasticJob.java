package com.liyz.dubbo.common.job.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 注释:elastic job注解
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/24 14:00
 */
@Component
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticJob {

    String jobName();

    String cron();

    int shardingTotalCount() default 1;

    String shardingItemParameters() default "";

    String jobParameter() default "";

    boolean monitorExecution() default true;

    boolean failover() default false;

    boolean misfire() default false;

    int maxTimeDiffSeconds() default -1;

    int reconcileIntervalMinutes() default 1;

    String jobErrorHandlerType() default "";

    String jobExecutorServiceHandlerType() default "";

    String jobShardingStrategyType() default "";

    String description() default "";

    boolean disabled() default false;

    boolean overwrite() default true;
}
