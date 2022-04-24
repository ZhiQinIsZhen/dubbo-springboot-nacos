package com.liyz.dubbo.service.staff.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:客户角色 DO
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 15:13
 */
@Getter
@Setter
@TableName("customer_role")
public class CustomerRoleDO implements Serializable {
    private static final long serialVersionUID = 3300045088591150389L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long customerId;

    private Integer roleId;

    private Integer isInactive;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
