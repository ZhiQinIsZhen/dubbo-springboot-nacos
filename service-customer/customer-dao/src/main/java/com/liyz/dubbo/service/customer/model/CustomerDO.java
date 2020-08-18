package com.liyz.dubbo.service.customer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 11:07
 */
@Getter
@Setter
@Table(name = "customer")
public class CustomerDO implements Serializable {
    private static final long serialVersionUID = 7001124888527192890L;

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "nick_name")
    private String nickName;

    private String password;

    @Column(name = "role_id")
    private Integer roleId;

    private String mobile;

    private String email;

    @Column(name = "is_inactive")
    private Integer isInactive;

    @Column(name = "web_token_time")
    private Date webTokenTime;

    @Column(name = "app_token_time")
    private Date appTokenTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
