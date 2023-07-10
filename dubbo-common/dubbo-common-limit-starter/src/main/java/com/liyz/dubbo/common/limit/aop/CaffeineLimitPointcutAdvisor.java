package com.liyz.dubbo.common.limit.aop;

import com.liyz.dubbo.common.limit.annotation.Limits;
import org.aopalliance.aop.Advice;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/9 15:54
 */
public class CaffeineLimitPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {
    private static final long serialVersionUID = -8006366537655727003L;

    public CaffeineLimitPointcutAdvisor(Advice advice) {
        super(advice);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        if (AnnotatedElementUtils.hasAnnotation(method, Limits.class)) {
            return true;
        }
        if (Proxy.isProxyClass(targetClass)) {
            return false;
        }
        Method specificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        return specificMethod != method && AnnotatedElementUtils.hasAnnotation(specificMethod, Limits.class);
    }
}
