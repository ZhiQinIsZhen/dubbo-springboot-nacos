package com.liyz.dubbo.common.remote.service;

import com.liyz.dubbo.common.remote.bo.GrantedAuthorityBO;

import java.util.List;

/**
 * 注释:授权url
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/18 13:52
 */
public interface RemoteGrantedAuthorityService {

    List<GrantedAuthorityBO> getByRoleId(Integer roleId);
}
