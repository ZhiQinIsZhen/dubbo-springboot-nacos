package com.liyz.dubbo.service.staff.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:角色信息类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 15:13
 */
@Getter
@Setter
public class RolePermissionBO implements Serializable {
    private static final long serialVersionUID = -4653799931628977469L;

    private Integer roleId;

    private Integer permissionId;

    private String permissionUrl;

    private String method;
}
