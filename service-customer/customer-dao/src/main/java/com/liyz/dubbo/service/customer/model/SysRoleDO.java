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
 * @date 2020/8/17 10:55
 */
@Getter
@Setter
@Table(name = "sys_role")
public class SysRoleDO implements Serializable {
    private static final long serialVersionUID = 6538330273834829639L;

    @Id
    private Integer id;

    @Column(name = "is_inactive")
    private Integer isInactive;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
