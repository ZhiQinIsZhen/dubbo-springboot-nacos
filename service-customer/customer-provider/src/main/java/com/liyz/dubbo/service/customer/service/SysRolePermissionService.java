package com.liyz.dubbo.service.customer.service;

import com.liyz.dubbo.common.dao.abs.AbstractService;
import com.liyz.dubbo.service.customer.dao.SysRolePermissionMapper;
import com.liyz.dubbo.service.customer.model.SysPermissionDO;
import com.liyz.dubbo.service.customer.model.SysRolePermissionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 11:32
 */
@Service
public class SysRolePermissionService extends AbstractService<SysRolePermissionDO> {

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    public List<SysPermissionDO> getByRoleId(Integer roleId) {
        return sysRolePermissionMapper.getByRoleId(roleId);
    }
}
