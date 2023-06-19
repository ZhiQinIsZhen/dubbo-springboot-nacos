package com.liyz.dubbo.api.user.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
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
 * @date 2023/3/10 14:42
 */
@Getter
@Setter
public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = 3401036150852744531L;

    @ApiModelProperty(value = "客户ID")
    private Long userId;

    @ApiModelProperty(value = "真实名称")
    @Desensitization(DesensitizationType.REAL_NAME)
    private String realName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @Desensitization(DesensitizationType.MOBILE)
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @Desensitization(DesensitizationType.EMAIL)
    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date registryTime;
}
