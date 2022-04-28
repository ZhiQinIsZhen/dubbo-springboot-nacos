package com.liyz.dubbo.api.open.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 13:51
 */
@ApiModel("UserRegisterDTO")
@Getter
@Setter
public class UserRegisterDTO implements Serializable {
    private static final long serialVersionUID = -8327287671771941961L;

    @ApiModelProperty(value = "账号", example = "登录/注册", required = true)
    @NotBlank(groups = {Register.class}, message = "登陆名不能为空")
    private String loginName;

    @ApiModelProperty(value = "密码，8-20位数字或字母组成", example = "登录/注册", required = true)
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$", groups = {Register.class}, message = "请输入8到20位数字和字母组合")
    private String loginPwd;

    @ApiModelProperty(value = "验证码", example = "677349", required = true)
    @NotBlank(groups = {Register.class}, message = "验证码不能为空")
    private String verificationCode;

    @ApiModelProperty(value = "昵称", example = "啦啦啦")
    @NotBlank(groups = {Register.class}, message = "昵称不能为空")
    private String nickName;

    @ApiModelProperty(value = "昵称", example = "张三")
    @NotBlank(groups = {Register.class}, message = "姓名不能为空")
    private String userName;

    public interface Register{}
}
