package com.liyz.dubbo.api.admin.vo.staff;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 16:57
 */
@Getter
@Setter
public class StaffLogoutLogVO implements Serializable {
    private static final long serialVersionUID = 787994069214271291L;

    @ApiModelProperty(value = "员工ID")
    private Long staffId;

    @ApiModelProperty(value = "登录方式(1:手机;2:邮箱)")
    private Integer type;

    @ApiModelProperty(value = "登录设备(1移动端:;2:网页端)")
    private Integer device;

    @ApiModelProperty(value = "登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logoutTime;

    @ApiModelProperty(value = "IP地址")
    private String ip;
}
