package com.liyz.dubbo.service.customer.provider;

import com.google.common.collect.Lists;
import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.remote.bo.GrantedAuthorityBO;
import com.liyz.dubbo.common.remote.service.RemoteGrantedAuthorityService;
import com.liyz.dubbo.service.customer.service.SysRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 14:25
 */
@Slf4j
@DubboService(version = "1.0.0")
public class RemoteRolePermissionImpl implements RemoteGrantedAuthorityService {

    @Autowired
    SysRolePermissionService sysRolePermissionService;

    @Override
    public List<GrantedAuthorityBO> getByRoleId(Integer roleId) {
        if (Objects.isNull(roleId)) {
            return Lists.newArrayList();
        }
        return CommonConverterUtil.ListTransform(sysRolePermissionService.getByRoleId(roleId), GrantedAuthorityBO.class);
    }
}
