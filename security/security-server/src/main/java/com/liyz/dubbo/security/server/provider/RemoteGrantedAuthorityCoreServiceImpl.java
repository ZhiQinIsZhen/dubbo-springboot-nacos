package com.liyz.dubbo.security.server.provider;

import com.google.common.collect.Lists;
import com.liyz.dubbo.common.core.auth.GrantedAuthority;
import com.liyz.dubbo.common.core.util.CommonCloneUtil;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.security.core.constant.SecurityConstant;
import com.liyz.dubbo.security.core.remote.RemoteGrantedAuthorityCoreService;
import com.liyz.dubbo.security.remote.RemoteGrantedAuthorityService;
import com.liyz.dubbo.security.remote.bo.GrantedAuthorityBO;
import com.liyz.dubbo.security.server.util.DubboGenericServiceUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 注释:权限核心实现类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 13:58
 */
@DubboService
public class RemoteGrantedAuthorityCoreServiceImpl implements RemoteGrantedAuthorityCoreService {

    private static final Object lock = new Object();

    @Override
    public List<GrantedAuthority> getByRoleId(Integer roleId, String group) {
        List<GrantedAuthorityBO> boList = getAuthority("getByRoleId", roleId, group);
        return CommonCloneUtil.ListClone(boList, GrantedAuthority.class);
    }

    @Override
    public List<GrantedAuthority> getByRoleIds(List<Integer> roleIds, String group) {
        List<GrantedAuthorityBO> boList = getAuthority("getByRoleIds", roleIds, group);
        return CommonCloneUtil.ListClone(boList, GrantedAuthority.class);
    }

    /**
     * 获取权限
     *
     * @param methodName
     * @param o
     * @return
     */
    private List<GrantedAuthorityBO> getAuthority(String methodName, Object o, String group) {
        String[] parameterTypes;
        if (o instanceof List) {
            parameterTypes = new String[] {List.class.getName()};
        } else {
            parameterTypes = new String[] {Integer.class.getName()};
        }
        List<Map<String, Object>> mapList = (List<Map<String, Object>>) DubboGenericServiceUtil
                .getByClassName(RemoteGrantedAuthorityService.class, SecurityConstant.DEFAULT_VERSION, group)
                .$invoke(methodName, parameterTypes, new Object[] {o});
        List<GrantedAuthorityBO> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(mapList)) {
            mapList.stream().forEach(item -> {
                GrantedAuthorityBO authorityBO = null;
                try {
                    authorityBO = CommonCloneUtil.MapToBean(item, GrantedAuthorityBO.class);
                } catch (Exception e) {
                    throw new RemoteServiceException(CommonExceptionCodeEnum.GRANTED_AUTHORITY_TRANS);
                }
                list.add(authorityBO);
            });
        }
        return list;
    }
}
