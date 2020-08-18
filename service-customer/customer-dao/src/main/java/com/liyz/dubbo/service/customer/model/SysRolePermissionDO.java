package com.liyz.dubbo.service.customer.model;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 11:28
 */
@Getter
@Setter
@Table(name = "sys_role_permission")
public class SysRolePermissionDO implements Serializable {
    private static final long serialVersionUID = 5933193179821183888L;

    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "permission_id")
    private Integer permissionId;

    @Transient
    private String permissionName;

    @Transient
    private String permissionUrl;

    @Transient
    private String method;

    @Column(name = "is_inactive")
    private Integer isInactive;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
