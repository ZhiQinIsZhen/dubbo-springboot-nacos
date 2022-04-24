package com.liyz.dubbo.service.staff.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:系统角色 DO
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 15:13
 */
@Getter
@Setter
@TableName(value = "sys_role")
public class SysRoleDO implements Serializable {
    private static final long serialVersionUID = 6538330273834829639L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer isInactive;

    private String roleName;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
