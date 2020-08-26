package com.liyz.dubbo.service.sharding.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/26 17:48
 */
@Setter
@Getter
public class UserDO implements Serializable {
    private static final long serialVersionUID = 8325793148492087183L;

    private Long id;

    private String city;

    private String name;

    private String pwd;
}
