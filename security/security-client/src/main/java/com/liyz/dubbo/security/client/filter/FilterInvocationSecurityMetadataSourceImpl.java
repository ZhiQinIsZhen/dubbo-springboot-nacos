package com.liyz.dubbo.security.client.filter;

import com.liyz.dubbo.security.client.core.ConfigAttributeImpl;
import com.liyz.dubbo.security.client.context.AnonymousUrlContext;
import com.liyz.dubbo.security.core.constant.SecurityConstant;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 注释:资源过滤器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 10:51
 */
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        if (!matchUrl(request)) {
            Set<ConfigAttribute> allAttributes = new HashSet<>();
            ConfigAttribute configAttribute = new ConfigAttributeImpl(request);
            allAttributes.add(configAttribute);
            return allAttributes;
        } else {
            return null;
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 如果这里返回true，则不去执行AccessDecisionManagerImpl.decide方法
     *
     * @param request
     * @return
     */
    private boolean matchUrl(HttpServletRequest request) {
        //常量urls
        for (String resource : SecurityConstant.SECURITY_IGNORE_RESOURCES) {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(resource);
            if (matcher.matches(request)) {
                return true;
            }
        }
        //免登陆urls
        List<String> list = AnonymousUrlContext.getAnonymousUrls();
        if (!CollectionUtils.isEmpty(list)) {
            for (String resource : list) {
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(resource);
                if (matcher.matches(request)) {
                    return true;
                }
            }
        }
        return false;
    }
}
