package com.liyz.dubbo.service.staff.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:登陆
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/5 17:48
 */
@Getter
@Setter
@TableName(value = "sta_login")
public class StaLoginDO implements Serializable {
    private static final long serialVersionUID = 5293315219748906254L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long customerId;

    private Integer device;

    private Date loginTime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
