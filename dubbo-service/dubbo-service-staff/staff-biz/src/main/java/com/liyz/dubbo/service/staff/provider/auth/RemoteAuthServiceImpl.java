package com.liyz.dubbo.service.staff.provider.auth;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
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
import com.liyz.dubbo.service.staff.model.*;
import com.liyz.dubbo.service.staff.model.base.StaffAuthBaseDO;
import com.liyz.dubbo.service.staff.service.*;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 11:10
 */
@Slf4j
@DubboService(tag = "staff")
public class RemoteAuthServiceImpl implements RemoteAuthService {

    @Resource
    private StaffInfoService staffInfoService;
    @Resource
    private StaffAuthMobileService staffAuthMobileService;
    @Resource
    private StaffAuthEmailService staffAuthEmailService;
    @Resource
    private StaffLoginLogService staffLoginLogService;
    @Resource
    private StaffLogoutLogService staffLogoutLogService;
    @Resource
    private StaffAuthorityService staffAuthorityService;
    @Resource
    private StaffRoleService staffRoleService;
    @Resource
    private SystemRoleAuthorityService systemRoleAuthorityService;
    @Resource
    private SystemAuthorityService systemAuthorityService;

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
                staffAuthEmailService.lambdaQuery().eq(StaffAuthEmailDO::getEmail, authUserRegister.getUsername()).exists() :
                staffAuthMobileService.lambdaQuery().eq(StaffAuthMobileDO::getMobile, authUserRegister.getUsername()).exists();
        if (userNameExist) {
            throw new RemoteAuthServiceException(isEmail ? AuthExceptionCodeEnum.EMAIL_EXIST : AuthExceptionCodeEnum.MOBILE_EXIST);
        }
        StaffInfoDO staffInfoDO = BeanUtil.copyProperties(authUserRegister, StaffInfoDO.class, (s, t) -> {
            if (isEmail) {
                t.setEmail(authUserRegister.getUsername());
            } else {
                t.setMobile(authUserRegister.getUsername());
            }
            t.setRegistryTime(DateUtil.currentDate());
        });
        staffInfoService.save(staffInfoDO);
        if (StringUtils.isNotBlank(staffInfoDO.getMobile())) {
            StaffAuthMobileDO mobileDO = StaffAuthMobileDO.builder().mobile(staffInfoDO.getMobile()).build();
            mobileDO.setStaffId(staffInfoDO.getStaffId());
            mobileDO.setPassword(authUserRegister.getPassword());
            staffAuthMobileService.save(mobileDO);
        }
        if (StringUtils.isNotBlank(staffInfoDO.getEmail())) {
            StaffAuthEmailDO emailDO = StaffAuthEmailDO.builder().email(staffInfoDO.getEmail()).build();
            emailDO.setStaffId(staffInfoDO.getStaffId());
            emailDO.setPassword(authUserRegister.getPassword());
            staffAuthEmailService.save(emailDO);
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
        Long staffId = this.getStaffId(username, authUser);
        if (Objects.isNull(staffId)) {
            return null;
        }
        StaffInfoDO staffInfoDO = staffInfoService.getById(staffId);
        authUser.setAuthId(staffId);
        authUser.setUsername(username);
        authUser.setSalt(staffInfoDO.getSalt());
        authUser.setDevice(device);
        Date lastLoginTime = staffLoginLogService.lastLoginTime(staffId, device);
        Date lastLogoutTime = staffLogoutLogService.lastLogoutTime(staffId, device);
        authUser.setCheckTime(ObjectUtils.max(lastLoginTime, lastLogoutTime));
        //查询角色信息
        List<StaffRoleDO> roles = staffRoleService.list(Wrappers.query(StaffRoleDO.builder().staffId(staffId).build()));
        authUser.setRoleIds(CollectionUtils.isEmpty(roles) ? null : roles.stream().map(StaffRoleDO::getRoleId).collect(Collectors.toList()));
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
        StaffLoginLogDO staffLoginLogDO = BeanUtil.copyProperties(authUserLogin, StaffLoginLogDO.class, (s, t) -> {
            t.setStaffId(s.getAuthId());
            t.setLoginTime(DateUtil.currentDate());
        });
        staffLoginLogService.save(staffLoginLogDO);
        //可能会有时间误差
        return staffLoginLogService.getById(staffLoginLogDO.getId()).getLoginTime();
    }

    /**
     * 登出
     *
     * @param authUserLogout 登出参数
     * @return True：登出成功；false：登出失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean logout(AuthUserLogoutBO authUserLogout) {
        StaffLogoutLogDO staffLogoutLogDO = BeanUtil.copyProperties(authUserLogout, StaffLogoutLogDO.class, (s, t) -> {
            t.setStaffId(s.getAuthId());
            t.setLogoutTime(DateUtil.currentDate());
        });
        return staffLogoutLogService.save(staffLogoutLogDO);
    }

    /**
     * 获取权限列表
     *
     * @param authUser 认证用户信息
     * @return 权限列表
     */
    @Override
    public List<AuthUserBO.AuthGrantedAuthorityBO> authorities(AuthUserBO authUser) {
        List<StaffAuthorityDO> list = staffAuthorityService.list(Wrappers.lambdaQuery(StaffAuthorityDO.class)
                .eq(StaffAuthorityDO::getStaffId, authUser.getAuthId())
                .le(StaffAuthorityDO::getAuthorityEndTime, DateUtil.currentDate()));
        List<SystemRoleAuthorityDO> roleAuthorityList = systemRoleAuthorityService.list(Wrappers.lambdaQuery(SystemRoleAuthorityDO.class)
                .in(SystemRoleAuthorityDO::getRoleId, authUser.getRoleIds()));
        Set<Integer> authorityIdSet = Sets.newHashSet();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> authorityIdSet.add(item.getAuthorityId()));
        }
        if (!CollectionUtils.isEmpty(roleAuthorityList)) {
            roleAuthorityList.forEach(item -> authorityIdSet.add(item.getAuthorityId()));
        }
        List<SystemAuthorityDO> authorityList = systemAuthorityService.list(Wrappers.lambdaQuery(SystemAuthorityDO.class)
                .in(SystemAuthorityDO::getAuthorityId, authorityIdSet));
        return BeanUtil.copyProperties(authorityList, AuthUserBO.AuthGrantedAuthorityBO.class);
    }

    /**
     * 根据username获取对应用户id
     *
     * @param username 用户名
     * @param authUser 认证用户信息
     * @return 用户ID
     */
    private Long getStaffId(String username, AuthUserBO authUser) {
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
        StaffAuthBaseDO staffAuthBaseDO = loginTypeService.getByUsername(username);
        if (Objects.isNull(staffAuthBaseDO)) {
            return null;
        }
        authUser.setPassword(staffAuthBaseDO.getPassword());
        return staffAuthBaseDO.getStaffId();
    }
}
