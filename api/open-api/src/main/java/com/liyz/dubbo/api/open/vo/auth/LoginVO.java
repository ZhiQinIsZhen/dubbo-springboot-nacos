package com.liyz.dubbo.api.open.vo.auth;

import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/27 15:34
 */
@ApiModel("LoginVO")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO implements Serializable {
    private static final long serialVersionUID = 579047040982418620L;

    @ApiModelProperty(value = "用户id", example = "10001")
    private Long userId;

    @Desensitization(endIndex = 3)
    @ApiModelProperty(value = "用户名称", example = "user")
    private String loginName;

    @ApiModelProperty(value = "昵称", example = "啦啦啦")
    private String nickName;

    @ApiModelProperty(value = "用户角色", example = "用户角色")
    private List<Integer> roleIds;

    @Desensitization(DesensitizationType.REAL_NAME)
    @ApiModelProperty(value = "昵称", example = "张三")
    private String userName;

    @Desensitization(DesensitizationType.MOBILE)
    @ApiModelProperty(value = "手机号", example = "15988654789")
    private String mobile;

    @Desensitization(DesensitizationType.EMAIL)
    @ApiModelProperty(value = "邮箱", example = "example@google.com", allowEmptyValue = true)
    private String email;

    @ApiModelProperty(value = "鉴权token", example = "eyJhbGcUzUxMiJ9.eyJzdWIiOiIUVySWQiOjF9.7-Mwz-1j7fbaPLGpQ")
    private String token;

    @ApiModelProperty(value = "Token失效时间戳(ms)", example = "193219768968")
    private Long expirationDate;
}
