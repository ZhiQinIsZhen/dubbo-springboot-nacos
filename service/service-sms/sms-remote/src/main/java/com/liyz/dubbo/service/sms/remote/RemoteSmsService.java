package com.liyz.dubbo.service.sms.remote;

import com.liyz.dubbo.service.sms.bo.ImageBO;
import com.liyz.dubbo.service.sms.bo.SmsInfoBO;
import com.liyz.dubbo.service.sms.enums.SmsType;
import org.springframework.validation.annotation.Validated;

/**
 * 注释:rpc sms service
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 15:44
 */
public interface RemoteSmsService {

    /**
     * 发送消息
     *
     * @deprecated 这里只是做测试，希望不要用rpc来发送信息，通过mq最好
     *
     * @param smsInfoBO
     * @return
     */
    @Deprecated
    boolean send(@Validated({SmsInfoBO.Sms.class}) SmsInfoBO smsInfoBO);

    /**
     * 验证
     *
     * @param smsType
     * @param address
     * @param verificationCode
     * @return
     */
    boolean validateSmsCode(SmsType smsType, String address, String verificationCode);

    /**
     * 生成图片验证码
     *
     * @return
     */
    ImageBO imageCode();

    /**
     * 验证验证码
     *
     * @param token
     * @param imageCode
     * @return
     */
    boolean validateImageCode(String token, String imageCode);
}
