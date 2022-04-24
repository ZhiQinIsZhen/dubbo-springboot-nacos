package com.liyz.dubbo.service.staff.service;

import com.liyz.dubbo.service.staff.model.SysPermissionDO;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/5/7 11:03
 */
public interface ISysRolePermissionService {

    List<SysPermissionDO> getByRoleId(Integer roleId);

    List<SysPermissionDO> getByRoleIds(List<Integer> roleIds);
}
