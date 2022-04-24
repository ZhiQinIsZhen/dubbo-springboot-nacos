package com.liyz.dubbo.security.core.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:token claim扩展类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 9:33
 */
@Getter
@Setter
public class ClaimDetail implements Serializable {
    private static final long serialVersionUID = 8365725594819426743L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 登陆设备类型
     */
    private Integer device;

    private String audience;

    /**
     * token创建时间
     */
    private Date creation;

    /**
     * token失效时间
     */
    private Date expiration;
}
