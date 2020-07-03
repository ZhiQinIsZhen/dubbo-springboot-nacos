package com.liyz.dubbo.service.member.remote;

import com.github.pagehelper.PageInfo;
import com.liyz.dubbo.service.member.bo.UserInfoBO;
import com.liyz.dubbo.service.member.bo.UserRegisterBO;
import com.liyz.dubbo.service.member.constant.MemberEnum;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/28 14:29
 */
public interface RemoteUserInfoService {

    UserInfoBO register(UserRegisterBO userRegisterBO);

    UserInfoBO getByUserId(@NotNull Long userId);

    PageInfo<UserInfoBO> pageList(Integer page, Integer size);

    UserInfoBO getByCondition(@NotNull UserInfoBO userInfoBO);

    Date loginTime(Long userId, String ip, MemberEnum.DeviceEnum deviceEnum);

    Date kickDownLine(@NotNull Long userId, @NotNull MemberEnum.DeviceEnum deviceEnum);
}
