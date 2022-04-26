package com.liyz.dubbo.security.client.filter;

import com.google.common.collect.Lists;
import com.liyz.dubbo.security.client.core.ConfigAttributeImpl;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * 注释:权限过滤器原数据获取实现类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/26 9:41
 */
public class FilterInvocationSecurityMetadataSourceImpl extends DefaultFilterInvocationSecurityMetadataSource {

    public FilterInvocationSecurityMetadataSourceImpl(LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
        super(requestMap);
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        Collection<ConfigAttribute> collection = super.getAttributes(object);
        if (CollectionUtils.isEmpty(collection)) {
            collection = Lists.newArrayList(new ConfigAttributeImpl(AuthenticatedVoter.IS_AUTHENTICATED_FULLY));
        }
        return collection;
    }
}
