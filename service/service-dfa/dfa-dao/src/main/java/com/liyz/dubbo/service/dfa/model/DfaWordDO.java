package com.liyz.dubbo.service.dfa.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/15 11:05
 */
@Getter
@Setter
@TableName(value = "dfa_word")
public class DfaWordDO implements Serializable {
    private static final long serialVersionUID = -3645096891418630272L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String sourceWord;

    private String targetWord;

    private Integer isInactive;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
