package com.liyz.dubbo.api.user.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 16:56
 */
@Getter
@Setter
public class UserLoginLogVO implements Serializable {
    private static final long serialVersionUID = 378737454967076747L;

    @ApiModelProperty(value = "客户ID")
    private Long userId;

    @ApiModelProperty(value = "登出方式(1:手机;2:邮箱)")
    private Integer loginType;

    @ApiModelProperty(value = "登出设备(1移动端:;2:网页端)")
    private Integer device;

    @ApiModelProperty(value = "登出时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;

    @ApiModelProperty(value = "IP地址")
    private String ip;
}
