package com.liyz.dubbo.common.limit.aop;

import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.common.limit.context.LimitContext;
import com.liyz.dubbo.common.limit.exception.LimitException;
import com.liyz.dubbo.common.limit.exception.LimitExceptionCodeEnum;
import com.liyz.dubbo.common.limit.service.LimitService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/6 11:29
 */
public class LimitAnnotationInterceptor implements MethodInterceptor {

    /**
     * <p>如果注解{@link Limit}有重复的，框架中不会帮你去重，虽然去重是举手之劳，
     * 所以在使用该注解时，请避免重复</p>
     *
     * @param invocation the method invocation joinpoint
     * @return result
     * @throws Throwable
     */
    @Nullable
    @Override
    public Object invoke(@NotNull MethodInvocation invocation) throws Throwable {
        Class<?> userClass = ClassUtils.getUserClass(invocation.getThis().getClass());
        Method specificMethod = ClassUtils.getMostSpecificMethod(invocation.getMethod(), userClass);
        Limits limits = specificMethod.getAnnotation(Limits.class);
        Limit[] limitArray = limits.value();
        Arrays.stream(limitArray).forEach(limit -> {
            LimitService limitService = LimitContext.getService(limit.type());
            if (Objects.nonNull(limitService) && limitService.limit(limit)) {
                throw new LimitException(LimitExceptionCodeEnum.OUT_LIMIT_COUNT);
            }
        });
        return invocation.proceed();
    }


}
