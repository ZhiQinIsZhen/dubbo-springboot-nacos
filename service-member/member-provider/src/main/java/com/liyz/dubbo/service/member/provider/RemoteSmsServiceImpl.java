package com.liyz.dubbo.service.member.provider;

import com.liyz.dubbo.common.base.util.JsonMapperUtil;
import com.liyz.dubbo.common.redisson.RedissonService;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import com.liyz.dubbo.service.member.bo.EmailMessageBO;
import com.liyz.dubbo.service.member.bo.ImageBO;
import com.liyz.dubbo.service.member.bo.SmsInfoBO;
import com.liyz.dubbo.service.member.constant.MemberConstant;
import com.liyz.dubbo.service.member.constant.MemberServiceCodeEnum;
import com.liyz.dubbo.service.member.constant.RedisKeyConstant;
import com.liyz.dubbo.service.member.remote.RemoteSmsService;
import com.liyz.dubbo.service.member.service.KafkaService;
import com.liyz.dubbo.service.member.service.UserInfoService;
import com.liyz.dubbo.service.member.util.ImageCodeUtil;
import com.liyz.dubbo.service.member.util.MemberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 注释:验证码
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/3 12:33
 */
@Slf4j
@DubboService(version = "1.0.0")
public class RemoteSmsServiceImpl implements RemoteSmsService {

    @Autowired
    RedissonService redissonService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    KafkaService kafkaService;

    /**
     * 发送信息
     *
     * @param smsInfoBO
     * @return
     */
    @Override
    public boolean message(SmsInfoBO smsInfoBO) {
        int type = MemberUtil.checkMobileEmail(smsInfoBO.getAddress(), MemberServiceCodeEnum.AddressNonMatch);
        //验证图形验证码
        if (!validateImageCode(smsInfoBO.getImageToken(), smsInfoBO.getImageCode())) {
            throw new RemoteServiceException(CommonCodeEnum.ImageCodeError);
        }
        int count = userInfoService.loginNameCount(smsInfoBO.getAddress());
        if (count == 1 && (MemberConstant.SMS_REGISTER_TYPE == smsInfoBO.getSmsType()
                || MemberConstant.SMS_BIND_EMAIL_TYPE == smsInfoBO.getSmsType()
                || MemberConstant.SMS_BIND_MOBILE_TYPE == smsInfoBO.getSmsType())) {
            throw new RemoteServiceException(type == 1 ? MemberServiceCodeEnum.MobileExist : MemberServiceCodeEnum.EmailExist);
        }
        if (count == 0 && (MemberConstant.SMS_LOGIN_TYPE == smsInfoBO.getSmsType()
                || MemberConstant.SMS_FIND_PWDTYPE == smsInfoBO.getSmsType()
                || MemberConstant.SMS_GOOGLE_TYPE == smsInfoBO.getSmsType()
                || MemberConstant.SMS_UPDATE_PWD_TYPE == smsInfoBO.getSmsType())) {
            throw new RemoteServiceException(MemberServiceCodeEnum.UserNonExist);
        }
        String smsCodeKey = RedisKeyConstant.getSmsCodeKey(smsInfoBO.getSmsType().toString(), smsInfoBO.getAddress());
        String smsCode = redissonService.getValue(smsCodeKey);
        if (StringUtils.isNotBlank(smsCode)) {
            long remainTimeToLive = redissonService.remainTimeToLive(smsCodeKey);
            if (remainTimeToLive > 9 * 60 * 1000) {
                throw new RemoteServiceException(type == 1 ? MemberServiceCodeEnum.SmsEmailSendAfter : MemberServiceCodeEnum.SmsEmailSendAfter);
            }
        }
        //确认24h次数
        String timesKey = RedisKeyConstant.getSmsTimesKey(smsInfoBO.getAddress());
        long times = redissonService.getAndIncrement(timesKey);
        if (times == 1) {
            redissonService.expire(timesKey, 1, TimeUnit.DAYS);
        }
        if (times > 10) {
            throw new RemoteServiceException(MemberServiceCodeEnum.SmsLimit);
        }
        //生成验证码
        smsCode = MemberUtil.randomInteger(6);
        redissonService.setValueExpire(smsCodeKey, smsCode, 10L, TimeUnit.MINUTES);
        log.info("*********sms send******user:{},smsCode:{}", smsInfoBO.getAddress(), smsCode);
        if (type == 2) {
            EmailMessageBO emailMessageBO = new EmailMessageBO();
            emailMessageBO.setCode(smsInfoBO.getSmsType());
            emailMessageBO.setAddress(smsInfoBO.getAddress());
            emailMessageBO.setSubject(MemberConstant.REGISTER_SUBJECT);
            emailMessageBO.setLocale(StringUtils.isBlank(smsInfoBO.getLocale()) ? MemberConstant.ZH_CN : smsInfoBO.getLocale());
            emailMessageBO.setParams(Arrays.asList(smsCode));
            //kafka发送消息
            kafkaService.sendSms(JsonMapperUtil.toJSONString(emailMessageBO));
        }
        return true;
    }

    /**
     * 验证短信/邮件验证码是否正确
     *
     * @param smsType
     * @param address
     * @param verificationCode
     * @return
     */
    @Override
    public boolean validateSmsCode(Integer smsType, String address, String verificationCode) {
        if (Objects.isNull(smsType) || StringUtils.isBlank(address) || StringUtils.isBlank(verificationCode)) {
            return false;
        }
        String smsCodeKey = RedisKeyConstant.getSmsCodeKey(smsType.toString(), address);
        if (verificationCode.equals(redissonService.getValue(smsCodeKey))) {
            return true;
        }
        return false;
    }

    /**
     * 生成验证码
     *
     * @return
     * @throws IOException
     */
    @Override
    public ImageBO imageCode() throws IOException {
        String imageCode = ImageCodeUtil.generateVerifyCode(4);
        log.info("************生成的图形验证码是：{}", imageCode);
        //生成加密token
        String str = imageCode + new Timestamp(System.currentTimeMillis());
        String token = DigestUtils.md5DigestAsHex(str.getBytes());
        String key = RedisKeyConstant.getImageTokenKey(token);
        redissonService.setValueExpire(key, imageCode, 5, TimeUnit.MINUTES);
        int w = 100, h = 39;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageCodeUtil.outputImage(w, h, out, imageCode);
        byte[] bytes = out.toByteArray();
        String imageBase64 = java.util.Base64.getEncoder().encodeToString(bytes);
        ImageBO imageBO = new ImageBO();
        imageBO.setImageToken(token);
        imageBO.setImageBase64(imageBase64);
        return imageBO;
    }

    /**
     * 验证验证码
     *
     * @param token
     * @param imageCode
     * @return
     */
    @Override
    public boolean validateImageCode(String token, String imageCode) {
        if (StringUtils.isBlank(token) || StringUtils.isBlank(imageCode)) {
            return false;
        }
        String key = RedisKeyConstant.getImageTokenKey(token);
        if (imageCode.equals(redissonService.getValue(key))) {
            return true;
        }
        return false;
    }
}
