package com.liyz.dubbo.security.client.user.impl;

import com.liyz.dubbo.common.service.constant.CommonServiceConstant;
import com.liyz.dubbo.security.client.context.AuthContext;
import com.liyz.dubbo.security.client.user.AuthUserDetails;
import com.liyz.dubbo.service.auth.bo.AuthUserBO;
import com.liyz.dubbo.service.auth.enums.Device;
import com.liyz.dubbo.service.auth.exception.AuthExceptionCodeEnum;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/14 15:45
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        int index = username.indexOf(CommonServiceConstant.DEFAULT_JOINER);
        if (index == -1) {
            throw new UsernameNotFoundException(AuthExceptionCodeEnum.AUTHORIZATION_FAIL.getMessage());
        }
        AuthUserBO authUserBO = AuthContext.AuthService.loadByUsername(username.substring(index + 1),
                Device.getByType(Integer.parseInt(username.substring(0, index))));
        return AuthUserDetails.build(authUserBO);
    }
}
