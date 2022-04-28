package com.liyz.dubbo.service.sms.bo;

import com.liyz.dubbo.service.sms.enums.SmsType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 注释:信息类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 15:31
 */
@Getter
@Setter
public class SmsInfoBO implements Serializable {
    private static final long serialVersionUID = 2149895880760523863L;

    /**
     * @see SmsType
     */
    @NotNull(groups = {Sms.class}, message = "验证码类型不能为空")
    private SmsType smsType;

    @NotBlank(groups = {Sms.class}, message = "接收地址不能为空")
    private String address;

    @NotBlank(groups = {Sms.class}, message = "图形验证码不能为空")
    private String imageCode;

    @NotBlank(groups = {Sms.class}, message = "图像token不能为空")
    private String imageToken;

    //国际化的
    private String locale;

    private String ip;

    public interface Sms {}
}
