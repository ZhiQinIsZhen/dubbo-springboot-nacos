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
 * @date 2019/8/28 17:49
 */
@Getter
@Setter
@Table(name = "user_info")
public class UserInfoDO implements Serializable {
    private static final long serialVersionUID = 3644334869112453017L;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login_name")
    private String loginName;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "login_pwd")
    private String loginPwd;

    @Column(name = "reg_time")
    private Date regTime;

    @Column(name = "web_token_time")
    private Date webTokenTime;

    @Column(name = "app_token_time")
    private Date appTokenTime;
}
