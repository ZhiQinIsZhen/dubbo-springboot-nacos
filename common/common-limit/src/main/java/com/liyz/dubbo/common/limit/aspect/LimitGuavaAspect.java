package com.liyz.dubbo.common.limit.aspect;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.common.limit.config.CommonLimitAutoConfig;
import com.liyz.dubbo.common.limit.service.AbstractLimitService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 11:21
 */
@Slf4j
@Aspect
@Configuration
@ConditionalOnBean(CommonLimitAutoConfig.class)
@AutoConfigureAfter(CommonLimitAutoConfig.class)
@ConditionalOnProperty(prefix = "request.limit", name = "model", havingValue = "guava")
public class LimitGuavaAspect extends AbstractLimitService implements Ordered {

    private static LoadingCache<String, RateLimiter> caches = CacheBuilder.newBuilder()
            .maximumSize(100000)
            .initialCapacity(1000)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<String, RateLimiter>() {
                @Override
                public RateLimiter load(String key){
                    return RateLimiter.create(getCount());
                }
            });

    public LimitGuavaAspect() {
        log.info("bean LimitGuavaAspect init ...");
    }

    /**
     * 这里可以有几个方式的优化，可以根据一些情况
     * 1.由于我们的{@link Limits} key有默认值，如果大家都不填写，可能这个有默认值的key的限流则会变为该应用的总并发限流，
     *   可以修改其默认限流的获取key的方式，可用通过 class 上的mapping值+ method上的mapping值，但是则全局限流就要重新做
     * 2.由于我这边限流的统计缓存全部放在内存中的，虽然有5分钟的失效时间，但是在高并发的场景下，建议大家不要用ip级的限流
     *   ，如果确实想做，可以单独起一个限流服务，专门放这些数据，同时也可以用这些数据做一些统计，可以统计出哪个客户没有通过
     *   h5页面访问api，可以结合布隆过滤器进行黑名单处理
     * 3.由于这里没有控制该注解只能加在有 {@link RequestMapping} {@link GetMapping}
     *   等注解上面的限制，所以这个注解同时可以加在一般的方法上甚至是私有方法上，大家可以做一个限制或者判断，有助于良好的
     *   开发习惯
     *
     * 注：对于1的建议，可以通过 method.getDeclaringClass().getName() 拿到该类的信息，再通过反射的方式来处理，如有必要，可以这么处理
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("aspect() && @annotation(limits)")
    public Object around(ProceedingJoinPoint joinPoint, Limits limits) throws Throwable {
        limit(limits);
        return joinPoint.proceed();
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

    @Override
    public RateLimiter getCacheLimit(String key) {
        return caches.getUnchecked(key);
    }
}
