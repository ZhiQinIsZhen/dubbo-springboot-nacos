package com.liyz.dubbo.common.api.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/3 9:31
 */
@Slf4j
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String authUserMethod = "getAuthUser";
    private static volatile Object contextManager;
    private static Class<?> contextManagerClass;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Objects.nonNull(getContextManager()) && parameter.getParameterType().getTypeName().equals("com.liyz.dubbo.service.auth.bo.AuthUserBO");
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return contextManagerClass.getMethod(authUserMethod).invoke(contextManager);
    }

    /**
     * 获取实例对象
     *
     * @return 实例对象
     */
    private static Object getContextManager() {
        if (contextManager != null) {
            return contextManager;
        }
        synchronized (authUserMethod) {
            if (contextManager != null) {
                return contextManager;
            }
            try {
                contextManagerClass = Class.forName("com.liyz.dubbo.security.client.context.AuthContext");
                contextManager = contextManagerClass.newInstance();
            } catch (Exception e) {
                log.error("加载AuthContext类失败!!!!!!!!!!!!!!!!");
            }
            return contextManager;
        }
    }
}
