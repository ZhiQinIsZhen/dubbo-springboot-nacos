package com.liyz.dubbo.service.member.remote;

import com.liyz.dubbo.service.member.bo.ImageBO;
import com.liyz.dubbo.service.member.bo.SmsInfoBO;

import java.io.IOException;

/**
 * 注释:验证码接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/3 12:31
 */
public interface RemoteSmsService {

    boolean message(SmsInfoBO smsInfoBO);

    boolean validateSmsCode(Integer smsType, String address, String verificationCode);

    ImageBO imageCode() throws IOException;

    boolean validateImageCode(String token, String imageCode);
}
