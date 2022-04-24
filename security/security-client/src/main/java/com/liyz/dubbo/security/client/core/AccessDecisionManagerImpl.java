package com.liyz.dubbo.security.client.core;

import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.security.client.bo.AuthGrantedAuthority;
import com.liyz.dubbo.security.client.context.AnonymousUrlContext;
import com.liyz.dubbo.security.core.constant.SecurityConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 注释:允许授权判断
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 10:07
 */
public class AccessDecisionManagerImpl implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (!authentication.isAuthenticated() || SecurityConstant.ANONYMOUS_USER.equals(authentication.getPrincipal())) {
            throw new BadCredentialsException(CommonExceptionCodeEnum.AUTHORIZATION_FAIL.getMessage());
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        //如果身份是管理员或者是一些不需要验证的url，直接通过
        if (SecurityConstant.BACKSTAGE_ROLE_ADMIN.equals(authentication.getPrincipal())) {
            return;
        }
        //免授权urls
        List<String> list = AnonymousUrlContext.getNonAuthorityUrls();
        if (!CollectionUtils.isEmpty(list)) {
            for (String resource : list) {
                if (matchUrl(resource, request)) {
                    return;
                }
            }
        }
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            if (ga instanceof AuthGrantedAuthority) {
                AuthGrantedAuthority authGrantedAuthority = (AuthGrantedAuthority) ga;
                String url = authGrantedAuthority.getUrl();
                String method = authGrantedAuthority.getMethod();
                if (matchUrl(url, request)) {
                    if (SecurityConstant.ALL_METHOD.equals(method) || method.equals(request.getMethod())) {
                        return;
                    }
                }
            }
        }
        throw new AccessDeniedException(CommonExceptionCodeEnum.NO_RIGHT.getMessage());
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    private boolean matchUrl(String url, HttpServletRequest request) {
        if (!StringUtils.isBlank(url)) {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
            if (matcher.matches(request)) {
                return true;
            }
        }
        return false;
    }
}
