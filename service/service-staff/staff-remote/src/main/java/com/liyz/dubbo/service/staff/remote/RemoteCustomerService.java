package com.liyz.dubbo.service.staff.remote;

import com.liyz.dubbo.service.staff.bo.CustomerBO;

import javax.validation.constraints.NotBlank;

/**
 * 注释:客户信息dubbo接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 15:15
 */
public interface RemoteCustomerService {

    /**
     * 根据用户名获取客户信息
     *
     * @param username
     * @return
     */
    CustomerBO getByUsername(@NotBlank String username);
}
