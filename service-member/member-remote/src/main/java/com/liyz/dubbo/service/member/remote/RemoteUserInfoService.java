package com.liyz.dubbo.service.member.remote;

import com.liyz.dubbo.common.remote.page.Page;
import com.liyz.dubbo.service.member.bo.UserInfoBO;
import com.liyz.dubbo.service.member.bo.UserRegisterBO;
import com.liyz.dubbo.service.member.constant.MemberEnum;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/28 14:29
 */
//@Validated
public interface RemoteUserInfoService {

    UserInfoBO register(UserRegisterBO userRegisterBO);

    UserInfoBO getByUserId(@NotBlank(message = "用户ID不能为空") Long userId);

    Page<UserInfoBO> pageList(Integer page, Integer size);

    UserInfoBO getByCondition(@NotNull(message = "参数不能为空") UserInfoBO userInfoBO);

    Date loginTime(Long userId, String ip, MemberEnum.DeviceEnum deviceEnum);

    Date kickDownLine(Long userId, MemberEnum.DeviceEnum deviceEnum);
}
