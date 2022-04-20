package com.liyz.dubbo.common.core.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:权限信息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 21:09
 */
@Getter
@Setter
public class GrantedAuthority implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer permissionId;

    private String permissionUrl;

    private String method;
}
