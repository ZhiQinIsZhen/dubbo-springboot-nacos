package com.liyz.dubbo.security.remote;

import com.liyz.dubbo.security.remote.bo.GrantedAuthorityBO;

import java.util.List;

/**
 * 注释:权限认证接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 16:33
 */
public interface RemoteGrantedAuthorityService {

    /**
     * 根据角色id查询权限列表
     *
     * @param roleId
     * @return
     */
    List<GrantedAuthorityBO> getByRoleId(Integer roleId);

    /**
     * 根据角色组查询权限列表
     *
     * @param roleIds
     * @return
     */
    List<GrantedAuthorityBO> getByRoleIds(List<Integer> roleIds);
}
