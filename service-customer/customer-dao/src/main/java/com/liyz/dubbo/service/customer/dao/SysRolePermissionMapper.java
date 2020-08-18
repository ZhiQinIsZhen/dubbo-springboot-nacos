package com.liyz.dubbo.service.customer.dao;

import com.liyz.dubbo.common.dao.mapper.Mapper;
import com.liyz.dubbo.service.customer.model.SysRolePermissionDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 11:32
 */
public interface SysRolePermissionMapper extends Mapper<SysRolePermissionDO> {

    List<SysRolePermissionDO> getByRoleId(@Param("roleId") Integer roleId);
}
