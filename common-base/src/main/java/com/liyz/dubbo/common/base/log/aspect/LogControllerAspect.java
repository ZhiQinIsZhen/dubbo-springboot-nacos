package com.liyz.dubbo.common.base.log.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/4 20:38
 */
@Slf4j
@ConditionalOnExpression("${spring.controller.log:false}")
@Aspect
@Configuration
@Order(1)
public class LogControllerAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void aspect() {}

    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String ip = request.getRemoteHost();
        String method = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
        log.info("ip {}, method {}", ip, method);
        return joinPoint.proceed();
    }
}
