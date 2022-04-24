package com.liyz.dubbo.service.staff.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:客户信息类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 15:13
 */
@Getter
@Setter
public class CustomerBO implements Serializable {
    private static final long serialVersionUID = -1922995329456408376L;

    private Long customerId;

    private String customerName;

    private String nickName;

    private String password;

    private Integer roleId;

    private String mobile;

    private String email;
}
