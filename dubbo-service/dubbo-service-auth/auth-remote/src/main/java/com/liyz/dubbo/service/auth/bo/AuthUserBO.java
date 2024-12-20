package com.liyz.dubbo.service.auth.bo;

import com.liyz.dubbo.service.auth.enums.Device;
import com.liyz.dubbo.service.auth.enums.LoginType;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/13 20:40
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserBO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long authId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 登录类型
     * @see com.liyz.dubbo.service.auth.enums.LoginType
     */
    private LoginType loginType;

    /**
     * 登录设备
     * @see com.liyz.dubbo.service.auth.enums.Device
     */
    private Device device;

    /**
     * 登录验证key
     */
    private String loginKey;

    /**
     * 用户角色
     */
    private List<Integer> roleIds;

    /**
     * 权限列表
     */
    private List<AuthGrantedAuthorityBO> authorities = new ArrayList<>();

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * token
     */
    private String token;


    @Getter
    @Setter
    public static class AuthGrantedAuthorityBO implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 客户端ID
         */
        private String clientId;

        /**
         * 权限码
         */
        private String authorityCode;
    }
}
