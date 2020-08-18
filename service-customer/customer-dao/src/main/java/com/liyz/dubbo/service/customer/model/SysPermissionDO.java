package com.liyz.dubbo.service.customer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 11:21
 */
@Getter
@Setter
@Table(name = "sys_permission")
public class SysPermissionDO implements Serializable {
    private static final long serialVersionUID = 2471090246732817951L;

    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "permission_url")
    private String permissionUrl;

    private String method;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "is_inactive")
    private Integer isInactive;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
