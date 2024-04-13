package com.liyz.dubbo.security.client.user;

import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.service.auth.bo.AuthUserBO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/14 15:39
 */
@Getter
@Setter
public class AuthUserDetails extends User {
    private static final long serialVersionUID = 1L;

    private final AuthUserBO authUser;

    public AuthUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, AuthUserBO authUser) {
        super(username, password, authorities);
        this.authUser = authUser;
    }

    public static AuthUserDetails build(AuthUserBO authUserBO) {
        return new AuthUserDetails(authUserBO.getUsername(), authUserBO.getPassword(),
                BeanUtil.copyProperties(authUserBO.getAuthorities(), AuthGrantedAuthority.class), authUserBO);
    }
}
