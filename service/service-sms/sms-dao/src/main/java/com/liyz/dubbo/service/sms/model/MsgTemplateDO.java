package com.liyz.dubbo.service.sms.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:消息模板
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 16:16
 */
@Getter
@Setter
public class MsgTemplateDO implements Serializable {
    private static final long serialVersionUID = -4065280121743663867L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer code;

    private Integer type;

    private String name;

    private String content;

    private String locale;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
