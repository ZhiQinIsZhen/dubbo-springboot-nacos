package com.liyz.dubbo.common.web.security.core;

import com.liyz.dubbo.common.base.service.LoginInfoService;
import com.liyz.dubbo.common.remote.bo.JwtUserBO;
import com.liyz.dubbo.common.remote.service.RemoteJwtUserService;
import com.liyz.dubbo.common.web.security.util.JwtAuthenticationUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

/**
 * 注释:用户获取接口实现类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/7 17:50
 */
@AllArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private RemoteJwtUserService remoteJwtUserService;
    private LoginInfoService loginInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUserBO jwtUserBO = remoteJwtUserService.getByLoginName(username);
        if (Objects.isNull(jwtUserBO)) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        loginInfoService.setUser(jwtUserBO);
        return JwtAuthenticationUtil.create(jwtUserBO);
    }
}
