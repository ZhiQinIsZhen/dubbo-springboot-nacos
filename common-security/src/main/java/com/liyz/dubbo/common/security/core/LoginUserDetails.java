package com.liyz.dubbo.common.security.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

/**
 * 注释:登陆用户信息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 17:51
 */
@Getter
@Setter
public class LoginUserDetails extends User {

    /**
     * 用户id
     */
    private final Long id;

    /**
     * 用户角色
     */
    private final Integer roleId;

    /**
     * 用户邮箱
     */
    private final String email;

    /**
     * 用户手机
     */
    private final String mobile;

    /**
     * 最新的web登陆时间
     */
    private final Date lastWebPasswordResetDate;

    /**
     * 最新的app登陆时间
     */
    private final Date lastAppPasswordResetDate;


    public LoginUserDetails(Long id, Integer roleId, String username, String password, String mobile, String email,
                            Collection<? extends GrantedAuthority> authorities, Date lastWebPasswordResetDate,
                            Date lastAppPasswordResetDate) {
        super(username, password, authorities);
        this.id = id;
        this.roleId = roleId;
        this.mobile = mobile;
        this.email = email;
        this.lastWebPasswordResetDate = lastWebPasswordResetDate;
        this.lastAppPasswordResetDate = lastAppPasswordResetDate;
    }

    public LoginUserDetails(Long id, Integer roleId, String username, String password, String mobile, String email,
                            boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                            Collection<? extends GrantedAuthority> authorities, Date lastWebPasswordResetDate,
                            Date lastAppPasswordResetDate) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.roleId = roleId;
        this.mobile = mobile;
        this.email = email;
        this.lastWebPasswordResetDate = lastWebPasswordResetDate;
        this.lastAppPasswordResetDate = lastAppPasswordResetDate;
    }
}
