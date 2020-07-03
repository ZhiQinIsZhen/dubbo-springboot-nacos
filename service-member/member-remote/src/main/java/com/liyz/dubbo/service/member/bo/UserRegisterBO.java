package com.liyz.dubbo.service.member.bo;

import com.liyz.dubbo.service.member.constant.MemberEnum;
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
 * @date 2019/9/7 22:38
 */
@Getter
@Setter
public class UserRegisterBO implements Serializable {
    private static final long serialVersionUID = 4010688986098940232L;

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

    @NotNull(groups = {Register.class}, message = "设备类型不能为空")
    private MemberEnum.DeviceEnum deviceEnum;

    private String ip;

    public interface Register{}
}
