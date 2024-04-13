package com.liyz.dubbo.service.auth.bo;

import com.liyz.dubbo.service.auth.enums.Device;
import com.liyz.dubbo.service.auth.enums.LoginType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/14 14:14
 */
@Getter
@Setter
public class AuthUserLogoutBO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long authId;

    /**
     * 登录类型
     * @see com.liyz.dubbo.service.auth.enums.LoginType
     */
    private LoginType logoutType;

    /**
     * 登录设备
     * @see com.liyz.dubbo.service.auth.enums.Device
     */
    private Device device;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 客户端ID
     */
    private String clientId;
}
