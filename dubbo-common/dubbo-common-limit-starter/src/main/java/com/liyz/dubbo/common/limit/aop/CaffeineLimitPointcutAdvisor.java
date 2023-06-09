package com.liyz.dubbo.common.limit.aop;

import com.liyz.dubbo.common.limit.annotation.Limits;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
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
public class CaffeineLimitPointcutAdvisor extends AbstractPointcutAdvisor {

    private final Advice advice;
    private final Pointcut pointcut;

    public CaffeineLimitPointcutAdvisor(MethodInterceptor methodInterceptor) {
        this.advice = methodInterceptor;
        AspectJExpressionPointcut expressionPointcut = new AspectJExpressionPointcut();
        expressionPointcut.setExpression("@annotation(com.liyz.dubbo.common.limit.annotation.Limits)");
        Pointcut cpc = new AnnotationMatchingPointcut(Limits.class, true);
        Pointcut mpc = new AnnotationMethodPoint();
        Pointcut cpu = (new ComposablePointcut(cpc)).union(mpc);
        this.pointcut = expressionPointcut;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    private static class AnnotationMethodPoint implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new AnnotationMethodMatcher();
        }

        private static class AnnotationMethodMatcher extends StaticMethodMatcher {

            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                if (this.matchesMethod(method)) {
                    return true;
                } else if (Proxy.isProxyClass(targetClass)) {
                    return false;
                } else {
                    Method specificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
                    return specificMethod != method && this.matchesMethod(specificMethod);
                }
            }

            /**
             * 判断方法上是否含有{@link Limits}注解
             * @param method 方法
             * @return true: 含有; false : 不含有
             */
            private boolean matchesMethod(Method method) {
                return AnnotatedElementUtils.hasAnnotation(method, Limits.class);
            }
        }
    }
}
