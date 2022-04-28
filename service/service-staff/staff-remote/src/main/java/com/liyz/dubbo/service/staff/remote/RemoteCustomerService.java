package com.liyz.dubbo.service.staff.remote;

import com.liyz.dubbo.service.staff.bo.CustomerBO;
import com.liyz.dubbo.service.staff.bo.UserRegisterBO;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 注释:客户信息dubbo接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 15:15
 */
public interface RemoteCustomerService {

    /**
     * 注册
     *
     * @param userRegisterBO
     */
    void register(@Validated({UserRegisterBO.Register.class}) UserRegisterBO userRegisterBO);

    /**
     * 根据用户名获取客户信息
     *
     * @param username
     * @return
     */
    CustomerBO getByUsername(@NotBlank String username);
}
