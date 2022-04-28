package com.liyz.dubbo.service.sms.provider;

import com.liyz.dubbo.common.redisson.service.RedissonService;
import com.liyz.dubbo.service.sms.bo.ImageBO;
import com.liyz.dubbo.service.sms.bo.SmsInfoBO;
import com.liyz.dubbo.service.sms.constant.RedisKeyConstant;
import com.liyz.dubbo.service.sms.enums.SmsType;
import com.liyz.dubbo.service.sms.remote.RemoteSmsService;
import com.liyz.dubbo.service.sms.util.SmsUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 注释:sms impl
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 15:48
 */
@Slf4j
@DubboService
public class RemoteSmsServiceImpl implements RemoteSmsService {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 发送消息
     *
     * @param smsInfoBO
     * @return
     */
    @Override
    public boolean send(SmsInfoBO smsInfoBO) {
        int type = SmsUtil.checkMobileEmail(smsInfoBO.getAddress());
        //todo 这里就不做实现了，大家可以根据自己公司的需求 增加短信、邮箱、钉钉等消息
        return true;
    }

    /**
     * 验证
     *
     * @param smsType
     * @param address
     * @param verificationCode
     * @return
     */
    @Override
    public boolean validateSmsCode(SmsType smsType, String address, String verificationCode) {
        if (Objects.isNull(smsType) || StringUtils.isBlank(address) || StringUtils.isBlank(verificationCode)) {
            return false;
        }
        String smsCodeKey = RedisKeyConstant.getSmsCodeKey(smsType.toString(), address);
        Object result = redissonClient.getBucket(smsCodeKey, RedissonService.getDEFAULT_STRING_CODE()).get();
        if (verificationCode.equals(result == null ? null : result.toString())) {
            return true;
        }
        return false;
    }

    /**
     * 生成图片验证码
     *
     * @return
     */
    @SneakyThrows
    @Override
    public ImageBO imageCode() {
        String imageCode = SmsUtil.generateVerifyCode(4);
        //生成加密token
        String str = imageCode + new Timestamp(System.currentTimeMillis());
        String token = DigestUtils.md5DigestAsHex(str.getBytes());
        log.info("************token:{},生成的图形验证码是：{}", token, imageCode);
        String key = RedisKeyConstant.getImageTokenKey(token);
        redissonClient.getBucket(key, RedissonService.getDEFAULT_STRING_CODE()).set(imageCode, 5, TimeUnit.MINUTES);
        ImageBO imageBO = new ImageBO();
        imageBO.setImageToken(token);
        imageBO.setImageCode(imageCode);
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
        Object result = redissonClient.getBucket(key, RedissonService.getDEFAULT_STRING_CODE()).get();
        if (imageCode.equals(result == null ? null : result.toString())) {
            redissonClient.getBucket(key).delete();
            return true;
        }
        return false;
    }
}
