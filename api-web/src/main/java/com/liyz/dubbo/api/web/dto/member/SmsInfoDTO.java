package com.liyz.dubbo.api.web.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/12 10:14
 */
@Setter
@Getter
public class SmsInfoDTO implements Serializable {
    private static final long serialVersionUID = 6637386386714256823L;

    //1:登录，2:注册，3:找回密码，4：谷歌验证，5：绑定手机，6：绑定邮箱，7：修改登录密码
    @ApiModelProperty(value = "验证码类型", example = "2", required = true)
    @NotNull(groups = {Sms.class}, message = "验证码类型不能为空")
    private Integer smsType;

    @ApiModelProperty(value = "接收地址", example = "15988658888", required = true)
    @NotBlank(groups = {Sms.class}, message = "接收地址不能为空")
    private String address;

    @ApiModelProperty(value = "图形验证码", example = "NM82", required = true)
    @NotBlank(groups = {Sms.class}, message = "图形验证码不能为空")
    private String imageCode;

    @ApiModelProperty(value = "图像token", example = "SSJLJFKJKL", required = true)
    @NotBlank(groups = {Sms.class}, message = "图像token不能为空")
    private String imageToken;

    @ApiModelProperty(value = "语言类型", example = "zh_CN")
    private String locale = "zh_CN";

    public interface Sms {}
}
