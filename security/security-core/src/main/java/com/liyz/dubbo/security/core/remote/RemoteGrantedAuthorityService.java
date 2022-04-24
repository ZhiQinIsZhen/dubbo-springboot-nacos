package com.liyz.dubbo.security.core.remote;

import com.liyz.dubbo.common.core.auth.GrantedAuthority;

import java.util.List;

/**
 * 注释:角色权限接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 9:40
 */
public interface RemoteGrantedAuthorityService {

    /**
     * 根据角色id，或者用户组获取对应权限
     *
     * @param roleId
     * @param group
     * @return
     */
    List<GrantedAuthority> getByRoleId(Integer roleId, String group);

    /**
     * 根据角色ids，或者用户组获取对应权限
     *
     * @param roleIds
     * @param group
     * @return
     */
    List<GrantedAuthority> getByRoleIds(List<Integer> roleIds, String group);
}
