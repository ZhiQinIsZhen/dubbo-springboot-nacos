package com.liyz.dubbo.security.remote.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 注释:用户认证信息类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 16:28
 */
@Getter
@Setter
public class AuthUserBO implements Serializable {
    private static final long serialVersionUID = 8471542579527883699L;

    private Long userId;

    private String loginName;

    private String nickName;

    private String userName;

    private List<Integer> roleIds;

    private String mobile;

    private String email;

    private String loginPwd;

    private Date regTime;

    private Date webTokenTime;

    private Date appTokenTime;

    private List<GrantedAuthorityBO> authorityList;
}
