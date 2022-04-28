package com.liyz.dubbo.service.staff.bo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 注释:注册信息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 14:54
 */
@Getter
@Setter
public class UserRegisterBO implements Serializable {
    private static final long serialVersionUID = -7013822916047200977L;

    @NotBlank(groups = {Register.class}, message = "登陆名不能为空")
    private String loginName;

    @NotBlank(groups = {Register.class}, message = "密码不能为空")
    private String loginPwd;

    @NotBlank(groups = {Register.class}, message = "验证码不能为空")
    private String verificationCode;

    @NotBlank(groups = {Register.class}, message = "昵称不能为空")
    private String nickName;

    @NotBlank(groups = {Register.class}, message = "姓名不能为空")
    private String userName;

    @NotNull(groups = {Register.class}, message = "注册设备不能为空")
    private Integer device;

    @NotBlank( message = "注册IP不能为空")
    private String ip;

    public interface Register{}
}
