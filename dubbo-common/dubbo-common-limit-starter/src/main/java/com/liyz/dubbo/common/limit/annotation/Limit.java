package com.liyz.dubbo.common.limit.annotation;

import com.liyz.dubbo.common.limit.enums.LimitType;
import java.lang.annotation.*;

/**
 * 注释:限流基础注解
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 10:28
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    /**
     * 默认为每秒只能访问多少次
     *
     * @return 每秒访问次数
     */
    double count() default 0.0;

    /**
     * 限流的key：只有在type=customize的时候，该值才会有效
     *
     * @return 限流key
     */
    String key() default "";

    /**
     * 限流的类型
     * 1.ip：针对ip进行限流，这个用法慎用，在客户群里比较大的时候，这个可能会带来性能的影响，会有很多不同的key存在缓存中（常见用法：用于发送短信，限制刻意刷短信）
     * 2.total：针对全局总次数，这个是业内比较常见的限流方式，这个mapping只能并发访问固定次数
     * 3.mapping：针对mapping级别的url来进行限流
     * 4.customize：自定义，配合key来进行限流
     *
     * @return 限流类型
     */
    LimitType type() default LimitType.MAPPING;
}
