package com.liyz.dubbo.security.client.user.impl;

import com.google.common.base.Joiner;
import com.liyz.dubbo.common.api.util.HttpServletContext;
import com.liyz.dubbo.common.service.constant.CommonServiceConstant;
import com.liyz.dubbo.common.util.PatternUtil;
import com.liyz.dubbo.security.client.constant.SecurityClientConstant;
import com.liyz.dubbo.security.client.context.AuthContext;
import com.liyz.dubbo.security.client.context.DeviceContext;
import com.liyz.dubbo.security.client.user.AuthUserDetails;
import com.liyz.dubbo.service.auth.bo.AuthUserBO;
import com.liyz.dubbo.service.auth.bo.AuthUserLoginBO;
import com.liyz.dubbo.service.auth.enums.Device;
import com.liyz.dubbo.service.auth.enums.LoginType;
import com.liyz.dubbo.service.auth.exception.AuthExceptionCodeEnum;
import com.liyz.dubbo.service.auth.remote.RemoteAuthService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/14 15:45
 */
public class UserDetailsServiceImpl implements UserDetailsService, EnvironmentAware {

    private static String clientId;

    private final RemoteAuthService remoteAuthService;

    public UserDetailsServiceImpl(RemoteAuthService remoteAuthService) {
        this.remoteAuthService = remoteAuthService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserLoginBO authUserLoginBO = AuthUserLoginBO
                .builder()
                .username(username)
                .clientId(clientId)
                .loginType(LoginType.getByType(PatternUtil.checkMobileEmail(username)))
                .device(DeviceContext.getDevice(HttpServletContext.getRequest()))
                .ip(HttpServletContext.getIpAddress())
                .build();
        AuthUserBO authUserBO = remoteAuthService.login(authUserLoginBO);
        if (Objects.isNull(authUserBO)) {
            throw new UsernameNotFoundException(AuthExceptionCodeEnum.AUTHORIZATION_FAIL.getMessage());
        }
        RpcContext.getClientAttachment().setAttachment(CommonServiceConstant.ATTACHMENT_LOGIN_USER, authUserBO.getAuthId().toString());
        return AuthUserDetails.build(authUserBO);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.clientId = environment.getProperty(SecurityClientConstant.DUBBO_APPLICATION_NAME_PROPERTY);
    }
}
