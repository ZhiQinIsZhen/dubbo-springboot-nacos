package com.liyz.dubbo.service.member.bo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/26 18:52
 */
@Getter
@Setter
public class UserInfoBO implements Serializable {
    private static final long serialVersionUID = -8073120134954381610L;

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
}
