package com.liyz.dubbo.security.remote.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:权限类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 16:28
 */
@Getter
@Setter
public class GrantedAuthorityBO implements Serializable {
    private static final long serialVersionUID = 5978152723117021413L;

    private Integer roleId;

    private Integer permissionId;

    private String permissionUrl;

    private String method;
}
