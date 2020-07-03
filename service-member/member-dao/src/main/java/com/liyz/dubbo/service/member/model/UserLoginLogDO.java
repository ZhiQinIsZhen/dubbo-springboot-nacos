package com.liyz.dubbo.service.member.model;

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
 * @date 2020/3/11 15:23
 */
@Setter
@Getter
@Table(name = "user_login_log")
public class UserLoginLogDO implements Serializable {
    private static final long serialVersionUID = 3837850817556747927L;

    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private Integer type;

    private String ip;

    private String device;

    @Column(name = "create_time")
    private Date createTime;
}
