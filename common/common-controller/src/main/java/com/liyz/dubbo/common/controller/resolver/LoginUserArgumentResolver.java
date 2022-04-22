package com.liyz.dubbo.common.controller.resolver;

import com.liyz.dubbo.common.controller.annotation.LoginUser;
import com.liyz.dubbo.common.core.util.AuthContext;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 注释:登陆用户参数解析器
 * @deprecated 已丢弃
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 14:32
 */
@Deprecated
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return AuthContext.getAuthUser();
    }
}
