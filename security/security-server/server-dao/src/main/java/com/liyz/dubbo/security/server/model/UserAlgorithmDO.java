package com.liyz.dubbo.security.server.model;

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
 * @date 2022/8/16 17:43
 */
@Getter
@Setter
@TableName(value = "user_algorithm")
public class UserAlgorithmDO implements Serializable {
    private static final long serialVersionUID = 96587427567140931L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String algorithm;

    private Integer type;

    private String privateKey;

    private String publicKey;

    private String algorithmKey;

    private String iv;

    private Integer isInactive;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
