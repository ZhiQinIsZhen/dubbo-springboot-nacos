package com.liyz.dubbo.service.staff.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyz.dubbo.service.staff.dao.SysRolePermissionMapper;
import com.liyz.dubbo.service.staff.model.SysPermissionDO;
import com.liyz.dubbo.service.staff.model.SysRolePermissionDO;
import com.liyz.dubbo.service.staff.service.ISysRolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/5/7 11:08
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermissionDO> implements ISysRolePermissionService {

    @Override
    public List<SysPermissionDO> getByRoleId(Integer roleId) {
        return getBaseMapper().getByRoleId(roleId);
    }

    @Override
    public List<SysPermissionDO> getByRoleIds(List<Integer> roleIds) {
        return getBaseMapper().getByRoleIds(roleIds);
    }
}
