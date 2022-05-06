package com.liyz.dubbo.security.client.impl;

import com.google.common.collect.Lists;
import com.liyz.dubbo.common.core.auth.AuthUser;
import com.liyz.dubbo.common.core.auth.GrantedAuthority;
import com.liyz.dubbo.common.core.constant.CommonConstant;
import com.liyz.dubbo.security.client.bo.AuthGrantedAuthority;
import com.liyz.dubbo.security.client.context.JwtContextHolder;
import com.liyz.dubbo.security.core.constant.SecurityEnum;
import com.liyz.dubbo.security.core.user.AuthUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 注释:自定义实现 userDetailService 接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 10:44
 */
@Slf4j
@Configuration
@ConditionalOnBean(value = {AuthenticationManager.class})
public class UserDetailsServiceImpl implements UserDetailsService {

    @Value("${jwt.user.authority}")
    private boolean authority;
    @Value("${jwt.audience.type}")
    private String audienceType;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        AuthUser authUser = getUserByUsername(username);
        UserDetails userDetails = getByAuthUser(authUser);
        return userDetails;
    }

    /**
     * 根据username获取认证用户信息
     *
     * @param username
     * @return
     */
    private AuthUser getUserByUsername(String username) {
        SecurityEnum.AudienceType audienceType = SecurityEnum.AudienceType.getByCode(this.audienceType);
        AuthUser authUser = JwtContextHolder.getJwtAuthCoreService().loadUserByUsername(username, CommonConstant.DEVICE_WEB, audienceType);
        if (Objects.isNull(authUser)) {
            throw new UsernameNotFoundException("No user found with token !");
        }
        if (authority) {
            List<GrantedAuthority> boList = JwtContextHolder.getGrantedAuthorityCoreService().getByRoleIds(authUser.getRoleIds(), this.audienceType);
            List<AuthGrantedAuthority> authorityList = new ArrayList<>(boList.size());
            boList.stream().forEach(grantedAuthorityBO -> {
                authorityList.add(new AuthGrantedAuthority(grantedAuthorityBO.getPermissionUrl(), grantedAuthorityBO.getMethod()));
            });
            authUser.setAuthorityList(boList);
        }
        return authUser;
    }

    /**
     * 转化
     *
     * @param authUser
     * @return
     */
    public static AuthUserDetails getByAuthUser(AuthUser authUser) {
        if (Objects.isNull(authUser)) {
            return null;
        }
        List<AuthGrantedAuthority> authorityList;
        if (!CollectionUtils.isEmpty(authUser.getAuthorityList())) {
            authorityList = new ArrayList<>(authUser.getAuthorityList().size());
            for (GrantedAuthority grantedAuthority : authUser.getAuthorityList()) {
                authorityList.add(new AuthGrantedAuthority(grantedAuthority.getPermissionUrl(), grantedAuthority.getMethod()));
            }
        } else {
            authorityList = Lists.newArrayList();
        }
        return new AuthUserDetails(
                authUser.getUserId(),
                authUser.getRoleIds(),
                authUser.getUserName(),
                authUser.getLoginPwd(),
                authUser.getMobile(),
                authUser.getEmail(),
                authUser.getLoginName(),
                authUser.getNickName(),
                authorityList,
                authUser.getLoginTime(),
                authUser.getGroup());
    }
}
