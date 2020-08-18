package com.liyz.dubbo.service.customer.dao;

import com.liyz.dubbo.common.dao.mapper.Mapper;
import com.liyz.dubbo.service.customer.model.SysPermissionDO;
import com.liyz.dubbo.service.customer.model.SysRolePermissionDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 11:32
 */
public interface SysRolePermissionMapper extends Mapper<SysRolePermissionDO> {

    @Select("SELECT b.permission_url permissionUrl,b.method\n" +
            "from sys_role_permission a\n" +
            "left join sys_permission b\n" +
            "on a.permission_id = b.permission_id\n" +
            "where role_id = #{roleId}\n" +
            "and a.is_inactive = 0\n" +
            "and b.is_inactive = 0")
    List<SysPermissionDO> getByRoleId(@Param("roleId") Integer roleId);
}
