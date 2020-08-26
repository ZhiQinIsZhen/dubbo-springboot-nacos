package com.liyz.dubbo.service.sharding.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/26 17:51
 */
@Setter
@Getter
public class UserBO implements Serializable {
    private static final long serialVersionUID = -8151542560694134107L;

    private Long id;

    private String city;

    private String name;

    private String pwd;
}
