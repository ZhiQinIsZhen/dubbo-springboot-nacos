package com.liyz.dubbo.service.customer.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 11:07
 */
@Getter
@Setter
public class CustomerBO implements Serializable {
    private static final long serialVersionUID = 7001124888527192890L;

    private Long customerId;

    private String customerName;

    private String nickName;

    private String password;

    private Integer roleId;

    private String mobile;

    private String email;
}
