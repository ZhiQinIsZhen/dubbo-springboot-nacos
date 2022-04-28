package com.liyz.dubbo.security.core.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 注释:security user扩展类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 9:32
 */
@Getter
@Setter
public class AuthUserDetails extends User {
    /**
     * 用户id
     */
    private final Long id;

    /**
     * 用户角色
     */
    private final List<Integer> roleIds;

    /**
     * 用户邮箱
     */
    private final String email;

    /**
     * 用户手机
     */
    private final String mobile;

    private final String loginName;

    private final String nikeName;

    /**
     * 最新的web登陆时间
     */
    private final Date lastWebPasswordResetDate;

    /**
     * 最新的app登陆时间
     */
    private final Date lastAppPasswordResetDate;

    /**
     * 认证组
     */
    private final String group;

    public AuthUserDetails(Long id, List<Integer> roleIds, String username, String password, String mobile, String email, String loginName,
                           String nikeName, Collection<? extends GrantedAuthority> authorities, Date lastWebPasswordResetDate,
                           Date lastAppPasswordResetDate, String group) {
        super(username, password, authorities);
        this.id = id;
        this.roleIds = roleIds;
        this.mobile = mobile;
        this.email = email;
        this.loginName = loginName;
        this.nikeName = nikeName;
        this.lastWebPasswordResetDate = lastWebPasswordResetDate;
        this.lastAppPasswordResetDate = lastAppPasswordResetDate;
        this.group = group;
    }

    public AuthUserDetails(Long id, List<Integer> roleIds, String username, String password, String mobile, String email, String loginName,
                           boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                           String nikeName, Collection<? extends GrantedAuthority> authorities, Date lastWebPasswordResetDate,
                           Date lastAppPasswordResetDate, String group) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.roleIds = roleIds;
        this.mobile = mobile;
        this.email = email;
        this.loginName = loginName;
        this.nikeName = nikeName;
        this.lastWebPasswordResetDate = lastWebPasswordResetDate;
        this.lastAppPasswordResetDate = lastAppPasswordResetDate;
        this.group = group;
    }
}
