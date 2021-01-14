package com.liyz.dubbo.common.controller.limit.aspect;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import com.liyz.dubbo.common.base.util.HttpRequestUtil;
import com.liyz.dubbo.common.controller.limit.annotation.Limit;
import com.liyz.dubbo.common.controller.limit.annotation.Limits;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 注释:限流切面类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/1/14 16:12
 */
@Slf4j
@Aspect
@Order(1)
@Configuration
@ConditionalOnExpression("${spring.limit}")
public class LimitCaffeineAspect {

    private static ThreadLocal<Double> permitsPerSecond = new ThreadLocal<>();

    private static LoadingCache<String, RateLimiter> caches = Caffeine.newBuilder()
            .maximumSize(100000)
            .initialCapacity(1000)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build(key -> RateLimiter.create(permitsPerSecond.get()));

    @Value("${spring.limit.total.count}")
    private double totalCount;

    /**
     * 切点
     */
    @Pointcut("@annotation(com.liyz.dubbo.common.controller.limit.annotation.Limits)")
    public void aspect() {}

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
    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Limits limits = method.getAnnotation(Limits.class);
        Limit[] limitArray = limits.value();
        Boolean flag = Boolean.TRUE;
        if (limitArray != null && limitArray.length > 0) {
            String key = null;
            double count = 0.0;
            //遍历
            for (Limit limit : limitArray) {
                count = limit.count();
                switch (limit.type()) {
                    case IP:
                        key = HttpRequestUtil.getIpAddress();
                        break;
                    case TOTAL:
                        key = "total";
                        count = totalCount;
                        break;
                    case MAPPING:
                        HttpServletRequest httpServletRequest = HttpRequestUtil.getRequest();
                        if (Objects.nonNull(httpServletRequest)) {
                            key = httpServletRequest.getServletPath();
                        } else {
                            key = "";
                        }
                        break;
                    case CUSTOMIZE:
                        key = limit.key();
                        break;
                    default:
                        break;
                }
                if (count > 0) {
                    try {
                        permitsPerSecond.set(count);
                        RateLimiter rateLimiter = caches.get(key);
                        flag = rateLimiter.tryAcquire();
                    } catch (Exception e) {
                        log.error("限流出错了,key:{}", key, e);
                    }
                    if (!flag) {
                        log.error("key:{} --> 触发了限流，每秒只能允许 {} 次访问", key, count);
                        throw new RemoteServiceException(CommonCodeEnum.LimitCount);
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
}
