package com.liyz.dubbo.service.user.provider.auth;

import com.google.common.collect.Lists;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.common.util.DateUtil;
import com.liyz.dubbo.common.util.PatternUtil;
import com.liyz.dubbo.service.auth.bo.AuthUserBO;
import com.liyz.dubbo.service.auth.bo.AuthUserLoginBO;
import com.liyz.dubbo.service.auth.bo.AuthUserLogoutBO;
import com.liyz.dubbo.service.auth.bo.AuthUserRegisterBO;
import com.liyz.dubbo.service.auth.enums.LoginType;
import com.liyz.dubbo.service.auth.exception.AuthExceptionCodeEnum;
import com.liyz.dubbo.service.auth.exception.RemoteAuthServiceException;
import com.liyz.dubbo.service.auth.remote.RemoteAuthService;
import com.liyz.dubbo.service.user.model.*;
import com.liyz.dubbo.service.user.model.base.UserAuthBaseDO;
import com.liyz.dubbo.service.user.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 11:10
 */
@Slf4j
@DubboService(tag = "user")
public class RemoteAuthenticationServiceImpl implements RemoteAuthService {

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserAuthMobileService userAuthMobileService;
    @Resource
    private UserAuthEmailService userAuthEmailService;
    @Resource
    private UserLoginLogService userLoginLogService;
    @Resource
    private UserLogoutLogService userLogoutLogService;

    /**
     * 用户注册
     *
     * @param authUserRegister 注册参数
     * @return True：注册成功；false：注册失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean registry(AuthUserRegisterBO authUserRegister) {
        String username = authUserRegister.getUsername();
        boolean isEmail = PatternUtil.matchEmail(username);
        //判断该用户名是否存在
        boolean userNameExist = isEmail ? Objects.nonNull(userAuthEmailService.getByUsername(username)) : Objects.nonNull(userAuthMobileService.getByUsername(username));
        if (userNameExist) {
            throw new RemoteAuthServiceException(isEmail ? AuthExceptionCodeEnum.EMAIL_EXIST : AuthExceptionCodeEnum.MOBILE_EXIST);
        }
        UserInfoDO userInfoDO = BeanUtil.copyProperties(authUserRegister, UserInfoDO.class, (s, t) -> {
            if (isEmail) {
                t.setEmail(authUserRegister.getUsername());
            } else {
                t.setMobile(authUserRegister.getUsername());
            }
            t.setRegistryTime(DateUtil.currentDate());
        });
        userInfoService.save(userInfoDO);
        if (StringUtils.isNotBlank(userInfoDO.getMobile())) {
            UserAuthMobileDO mobileDO = UserAuthMobileDO.builder().mobile(userInfoDO.getMobile()).build();
            mobileDO.setUserId(userInfoDO.getUserId());
            mobileDO.setPassword(authUserRegister.getPassword());
            userAuthMobileService.save(mobileDO);
        }
        if (StringUtils.isNotBlank(userInfoDO.getEmail())) {
            UserAuthEmailDO emailDO = UserAuthEmailDO.builder().email(userInfoDO.getEmail()).build();
            emailDO.setUserId(userInfoDO.getUserId());
            emailDO.setPassword(authUserRegister.getPassword());
            userAuthEmailService.save(emailDO);
        }
        return Boolean.TRUE;
    }

    /**
     * 登录
     *
     * @param authUserLogin 登录参数
     * @return 当前登录用户信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthUserBO login(AuthUserLoginBO authUserLogin) {
        AuthUserBO authUser = AuthUserBO.builder()
                .clientId(authUserLogin.getClientId())
                .username(authUserLogin.getUsername())
                .loginType(authUserLogin.getLoginType())
                .device(authUserLogin.getDevice())
                .authorities(Lists.newArrayList())
                .build();
        Long userId = this.getUserId(authUserLogin.getUsername(), authUser);
        if (Objects.isNull(userId)) {
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.USER_NOT_EXIST);
        }
        UserInfoDO userInfoDO = userInfoService.getById(userId);
        if (Objects.isNull(userInfoDO)) {
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.USER_NOT_EXIST);
        }
        authUser.setAuthId(userId);
        authUser.setSalt(userInfoDO.getSalt());
        authUser.setAuthorities(new ArrayList<>());
        UserLoginLogDO userLoginLogDO = BeanUtil.copyProperties(authUser, UserLoginLogDO.class, (s, t) -> {
            t.setUserId(userId);
            t.setLoginTime(DateUtil.currentDate());
            t.setLoginType(s.getLoginType().getType());
            t.setDevice(s.getDevice().getType());
        });
        userLoginLogService.save(userLoginLogDO);
        return authUser;
    }

    /**
     * 登出
     *
     * @param authUserLogout 登出参数
     * @return True：登出成功；false：登出失败
     */
    @Override
    public Boolean logout(AuthUserLogoutBO authUserLogout) {
        UserLogoutLogDO userLogoutLogDO = BeanUtil.copyProperties(authUserLogout, UserLogoutLogDO.class, (s, t) -> {
            t.setUserId(s.getAuthId());
            t.setDevice(s.getDevice().getType());
            t.setLogoutTime(DateUtil.currentDate());
        });
        return userLogoutLogService.save(userLogoutLogDO);
    }

    /**
     * 根据username获取对应用户id
     *
     * @param username 用户名
     * @param authUser 认证用户
     * @return 用户ID
     */
    private Long getUserId(String username, AuthUserBO authUser) {
        LoginType loginType = LoginType.getByType(PatternUtil.checkMobileEmail(username));
        if (Objects.isNull(loginType)) {
            log.warn("username is not email or mobile : {}", username);
            return null;
        }
        authUser.setLoginType(loginType);
        LoginTypeService loginTypeService = LoginTypeService.LOGIN_TYPE_MAP.get(loginType);
        if (Objects.isNull(loginTypeService)) {
            log.warn("{} can not find LoginTypeService", loginType.name());
            return null;
        }
        UserAuthBaseDO userAuthBaseDO = loginTypeService.getByUsername(username);
        if (Objects.isNull(userAuthBaseDO)) {
            return null;
        }
        authUser.setPassword(userAuthBaseDO.getPassword());
        return userAuthBaseDO.getUserId();
    }
}
