package com.liyz.dubbo.service.user.provider.auth;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.common.util.DateUtil;
import com.liyz.dubbo.common.util.PatternUtil;
import com.liyz.dubbo.service.auth.bo.AuthUserBO;
import com.liyz.dubbo.service.auth.bo.AuthUserLoginBO;
import com.liyz.dubbo.service.auth.bo.AuthUserLogoutBO;
import com.liyz.dubbo.service.auth.bo.AuthUserRegisterBO;
import com.liyz.dubbo.service.auth.enums.Device;
import com.liyz.dubbo.service.auth.enums.LoginType;
import com.liyz.dubbo.service.auth.exception.AuthExceptionCodeEnum;
import com.liyz.dubbo.service.auth.exception.RemoteAuthServiceException;
import com.liyz.dubbo.service.auth.remote.RemoteAuthService;
import com.liyz.dubbo.service.user.model.*;
import com.liyz.dubbo.service.user.model.base.UserAuthBaseDO;
import com.liyz.dubbo.service.user.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        boolean isEmail = PatternUtil.matchEmail(authUserRegister.getUsername());
        //判断该用户名是否存在
        boolean userNameExist = isEmail ?
                userAuthEmailService.lambdaQuery().eq(UserAuthEmailDO::getEmail, authUserRegister.getUsername()).exists() :
                userAuthMobileService.lambdaQuery().eq(UserAuthMobileDO::getMobile, authUserRegister.getUsername()).exists();
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
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @param device 登录设备
     * @return 登录用户信息
     */
    @Override
    public AuthUserBO loadByUsername(String username, Device device) {
        AuthUserBO authUser = AuthUserBO.builder()
                .username(username)
                .loginType(LoginType.getByType(PatternUtil.checkMobileEmail(username)))
                .authorities(Lists.newArrayList())
                .build();
        Long userId = this.getUserId(username, authUser);
        if (Objects.isNull(userId)) {
            return null;
        }
        UserInfoDO staffInfoDO = userInfoService.getById(userId);
        authUser.setAuthId(userId);
        authUser.setUsername(username);
        authUser.setSalt(staffInfoDO.getSalt());
        authUser.setDevice(device);
        Date lastLoginTime = userLoginLogService.lastLoginTime(userId, device);
        Date lastLogoutTime = userLogoutLogService.lastLogoutTime(userId, device);
        authUser.setCheckTime(ObjectUtils.max(lastLoginTime, lastLogoutTime));
        return authUser;
    }

    /**
     * 登录
     *
     * @param authUserLogin 登录参数
     * @return 当前登录时间
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Date login(AuthUserLoginBO authUserLogin) {
        UserLoginLogDO userLoginLogDO = BeanUtil.copyProperties(authUserLogin, UserLoginLogDO.class, (s, t) -> {
            t.setUserId(s.getAuthId());
            t.setLoginTime(DateUtil.currentDate());
            t.setLoginType(s.getLoginType().getType());
            t.setDevice(s.getDevice().getType());
        });
        userLoginLogService.save(userLoginLogDO);
        //可能会有时间误差
        return userLoginLogService.getById(userLoginLogDO.getId()).getLoginTime();
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
            log.warn("username is not email or mobile");
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
