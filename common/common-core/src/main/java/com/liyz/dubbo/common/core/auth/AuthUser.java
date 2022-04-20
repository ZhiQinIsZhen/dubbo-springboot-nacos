package com.liyz.dubbo.common.core.auth;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 注释:认证信息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 21:08
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser implements Serializable {
    private static final long serialVersionUID = 1L;

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

    private List<GrantedAuthority> authorityList;
}
