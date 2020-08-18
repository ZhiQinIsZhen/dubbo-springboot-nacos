package com.liyz.dubbo.common.security.core;

import com.google.common.collect.Lists;
import com.liyz.dubbo.common.base.service.LoginInfoService;
import com.liyz.dubbo.common.remote.bo.GrantedAuthorityBO;
import com.liyz.dubbo.common.remote.bo.JwtUserBO;
import com.liyz.dubbo.common.remote.service.RemoteGrantedAuthorityService;
import com.liyz.dubbo.common.remote.service.RemoteJwtUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 注释:用户获取接口实现类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/18 13:35
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Value("${jwt.user.authority:false}")
    private boolean authority;

    @Autowired
    private LoginInfoService loginInfoService;

    @DubboReference(version = "1.0.0", group = "${jwt.user.group}")
    private RemoteJwtUserService remoteJwtUserService;
    @DubboReference(version = "1.0.0")
    private RemoteGrantedAuthorityService remoteGrantedAuthorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUserBO jwtUserBO = remoteJwtUserService.getByLoginName(username);
        if (Objects.isNull(jwtUserBO)) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        if (authority) {
            List<GrantedAuthorityBO> boList = remoteGrantedAuthorityService.getByRoleId(jwtUserBO.getRoleId());
            List<AuthGrantedAuthority> authorityList = new ArrayList<>(boList.size());
            boList.stream().forEach(grantedAuthorityBO -> {
                authorityList.add(new AuthGrantedAuthority(grantedAuthorityBO.getPermissionUrl(), grantedAuthorityBO.getMethod()));
            });
            jwtUserBO.setAuthorityList(boList);
        }
        loginInfoService.setUser(jwtUserBO);
        return getByJwtUser(jwtUserBO);
    }

    public static UserDetails getByJwtUser(JwtUserBO jwtUserBO) {
        if (Objects.isNull(jwtUserBO)) {
            return null;
        }
        List<AuthGrantedAuthority> authorityList;
        if (!CollectionUtils.isEmpty(jwtUserBO.getAuthorityList())) {
            authorityList = new ArrayList<>(jwtUserBO.getAuthorityList().size());
            for (GrantedAuthorityBO grantedAuthorityBO : jwtUserBO.getAuthorityList()) {
                authorityList.add(new AuthGrantedAuthority(grantedAuthorityBO.getPermissionUrl(), grantedAuthorityBO.getMethod()));
            }
        } else {
            authorityList = Lists.newArrayList();
        }
        return new LoginUserDetails(
                jwtUserBO.getUserId(),
                jwtUserBO.getRoleId(),
                jwtUserBO.getLoginName(),
                jwtUserBO.getLoginPwd(),
                jwtUserBO.getMobile(),
                jwtUserBO.getEmail(),
                authorityList,
                jwtUserBO.getWebTokenTime(),
                jwtUserBO.getAppTokenTime());
    }
}
