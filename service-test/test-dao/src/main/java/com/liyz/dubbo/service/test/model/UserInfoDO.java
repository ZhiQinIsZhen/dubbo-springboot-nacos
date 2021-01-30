package com.liyz.dubbo.service.test.model;

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
 * @date 2019/8/28 17:49
 */
@Getter
@Setter
@TableName(value = "user_info")
public class UserInfoDO implements Serializable {
    private static final long serialVersionUID = 3644334869112453017L;

    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    private String loginName;

    private String nickName;

    private String userName;

    private String mobile;

    private String email;

    private String loginPwd;

    private Date regTime;

    private Date webTokenTime;

    private Date appTokenTime;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
