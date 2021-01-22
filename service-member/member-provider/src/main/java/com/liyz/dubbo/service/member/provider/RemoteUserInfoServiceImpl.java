package com.liyz.dubbo.service.member.provider;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liyz.dubbo.common.base.log.annotation.Logs;
import com.liyz.dubbo.common.base.util.CommonCloneUtil;
import com.liyz.dubbo.common.base.util.DateUtil;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import com.liyz.dubbo.common.remote.page.Page;
import com.liyz.dubbo.service.member.bo.UserInfoBO;
import com.liyz.dubbo.service.member.bo.UserRegisterBO;
import com.liyz.dubbo.service.member.config.MemberSnowflakeConfig;
import com.liyz.dubbo.service.member.constant.MemberConstant;
import com.liyz.dubbo.service.member.constant.MemberEnum;
import com.liyz.dubbo.service.member.constant.MemberServiceCodeEnum;
import com.liyz.dubbo.service.member.model.UserInfoDO;
import com.liyz.dubbo.service.member.remote.RemoteSmsService;
import com.liyz.dubbo.service.member.remote.RemoteUserInfoService;
import com.liyz.dubbo.service.member.service.UserInfoService;
import com.liyz.dubbo.service.member.service.UserLoginLogService;
import com.liyz.dubbo.service.member.util.MemberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/3 12:23
 */
@Slf4j
@DubboService(version = "1.0.0")
public class RemoteUserInfoServiceImpl implements RemoteUserInfoService {

    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserLoginLogService userLoginLogService;
    @Autowired
    RemoteSmsService remoteSmsService;
    @Autowired
    MemberSnowflakeConfig memberSnowflakeConfig;

    /**
     * 用户注册
     *
     * @param userRegisterBO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserInfoBO register(UserRegisterBO userRegisterBO) {
        int type = MemberUtil.checkMobileEmail(userRegisterBO.getLoginName(), MemberServiceCodeEnum.MobileEmailNonMatch);
        UserInfoDO param = new UserInfoDO();
        param.setLoginName(userRegisterBO.getLoginName());
        int count = userInfoService.selectCount(param);
        if (count > 0) {
            throw new RemoteServiceException(type == 1 ? MemberServiceCodeEnum.MobileExist : MemberServiceCodeEnum.EmailExist);
        }
        //校验验证码
        if (!remoteSmsService.validateSmsCode(MemberConstant.SMS_REGISTER_TYPE, userRegisterBO.getLoginName(),
                userRegisterBO.getVerificationCode())) {
            throw new RemoteServiceException(type == 1 ? CommonCodeEnum.MobileCodeError : CommonCodeEnum.EmailCodeError);
        }
        param = CommonCloneUtil.objectClone(userRegisterBO, UserInfoDO.class);
        param.setUserId(memberSnowflakeConfig.getId());
        param.setEmail(type == 2 ? param.getLoginName() : "812672598@qq.com");
        param.setMobile(type == 1 ? param.getLoginName() : "15988654731");
        param.setRegTime(new Date());
        count = userInfoService.save(param);
        if (count == 0) {
            throw new RemoteServiceException(MemberServiceCodeEnum.RegisterFail);
        }
        userLoginLogService.save(param.getUserId(), userRegisterBO.getIp(), MemberConstant.REGISTER_TYPE,
                userRegisterBO.getDeviceEnum().getDevice());
        return CommonCloneUtil.objectClone(param, UserInfoBO.class);
    }

    /**
     * 通过用户id查询用户信息
     *
     * @param userId
     * @return
     */
    @Logs
    @Override
    public UserInfoBO getByUserId(@NotNull Long userId) {
        UserInfoDO userInfoDO = userInfoService.getById(userId);
        if (Objects.isNull(userInfoDO)) {
            throw new RemoteServiceException(CommonCodeEnum.NoData);
        }
        return CommonCloneUtil.objectClone(userInfoDO, UserInfoBO.class);
    }

    /**
     * 分页查询用户信息
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<UserInfoBO> pageList(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<UserInfoDO> doList = userInfoService.listAll();
        PageInfo<UserInfoDO> doPageInfo = new PageInfo<>(doList);
        Page<UserInfoBO> boPage = CommonCloneUtil.pageInfoToPage(doPageInfo, UserInfoBO.class);
        return boPage;
    }

    /**
     * 根据查询条件查询出一条用户信息
     *
     * @param userInfoBO
     * @return
     */
    @Override
    public UserInfoBO getByCondition(@NotNull UserInfoBO userInfoBO) {
        UserInfoDO userInfoDO = null;
        try {
            userInfoDO = userInfoService.getOne(CommonCloneUtil.objectClone(userInfoBO, UserInfoDO.class));
        } catch (Exception e) {
            log.error("出错啦", e);
        }
        return CommonCloneUtil.objectClone(userInfoDO, UserInfoBO.class);
    }

    /**
     * 获取登陆时间-登陆时调用
     *
     * @param userId
     * @param ip
     * @param deviceEnum
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Date loginTime(Long userId, String ip, MemberEnum.DeviceEnum deviceEnum) {
        Date loginTime = kickDownLine(userId, deviceEnum);
        userLoginLogService.save(userId, ip, MemberConstant.LOGIN_TYPE, deviceEnum.getDevice());
        return loginTime;
    }

    /**
     * 踢下线
     *
     * @param userId
     * @param deviceEnum
     * @return
     */
    @Override
    public Date kickDownLine(@NotNull Long userId, @NotNull MemberEnum.DeviceEnum deviceEnum) {
        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO.setUserId(userId);
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        LocalDateTime localDateTime = DateUtil.minusTime(nowLocalDateTime, 10, ChronoUnit.SECONDS);
        Date tokenTime = DateUtil.convertLocalDateTimeToDate(localDateTime);
        if (MemberEnum.DeviceEnum.WEB == deviceEnum) {
            userInfoBO.setWebTokenTime(tokenTime);
        } else if (MemberEnum.DeviceEnum.MOBILE == deviceEnum) {
            userInfoBO.setAppTokenTime(tokenTime);
        } else {
            userInfoBO.setWebTokenTime(tokenTime);
            userInfoBO.setAppTokenTime(tokenTime);
        }
        userInfoService.updateById(CommonCloneUtil.objectClone(userInfoBO, UserInfoDO.class));
        return DateUtil.convertLocalDateTimeToDate(nowLocalDateTime);
    }
}
