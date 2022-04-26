package com.liyz.dubbo.security.client.filter;

import com.google.common.collect.Lists;
import com.liyz.dubbo.security.client.context.AnonymousUrlContext;
import com.liyz.dubbo.security.client.core.ConfigAttributeImpl;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * 注释:授权认证拦截器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 10:47
 */
public class GrantedAuthoritySecurityInterceptor extends FilterSecurityInterceptor {

    public GrantedAuthoritySecurityInterceptor(AccessDecisionManager accessDecisionManager) {
        this.setAccessDecisionManager(accessDecisionManager);
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();
        AnonymousUrlContext.getAnonymousUrls().stream().forEach(anonymousPath -> {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(anonymousPath);
            requestMap.put(matcher, Lists.newArrayList(new ConfigAttributeImpl(AuthenticatedVoter.IS_AUTHENTICATED_ANONYMOUSLY)));
        });
        this.setSecurityMetadataSource(new FilterInvocationSecurityMetadataSourceImpl(requestMap));
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return super.obtainSecurityMetadataSource();
    }
}
