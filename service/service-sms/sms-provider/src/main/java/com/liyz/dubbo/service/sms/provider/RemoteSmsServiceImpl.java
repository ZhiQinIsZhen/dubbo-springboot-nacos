package com.liyz.dubbo.service.sms.provider;

import com.liyz.dubbo.common.redisson.service.RedissonService;
import com.liyz.dubbo.common.util.GZipUtil;
import com.liyz.dubbo.service.sms.bo.ImageBO;
import com.liyz.dubbo.service.sms.bo.SmsInfoBO;
import com.liyz.dubbo.service.sms.constant.RedisKeyConstant;
import com.liyz.dubbo.service.sms.remote.RemoteSmsService;
import com.liyz.dubbo.service.sms.util.ImageCodeUtil;
import com.liyz.dubbo.service.sms.util.SmsUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
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

        return true;
    }

    /**
     * 生成图片验证码
     *
     * @return
     */
    @SneakyThrows
    @Override
    public ImageBO imageCode() {
        String imageCode = ImageCodeUtil.generateVerifyCode(4);
        log.info("************生成的图形验证码是：{}", imageCode);
        //生成加密token
        String str = imageCode + new Timestamp(System.currentTimeMillis());
        String token = DigestUtils.md5DigestAsHex(str.getBytes());
        String key = RedisKeyConstant.getImageTokenKey(token);
        redissonClient.getBucket(key, RedissonService.getDEFAULT_STRING_CODE()).set(imageCode, 5, TimeUnit.MINUTES);
        int w = 100, h = 39;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageCodeUtil.outputImage(w, h, out, imageCode);
        byte[] bytes = out.toByteArray();
        String imageBase64 = java.util.Base64.getEncoder().encodeToString(bytes);
        ImageBO imageBO = new ImageBO();
        imageBO.setImageToken(token);
        imageBO.setImageZip(GZipUtil.decompress(imageBase64));
        return imageBO;
    }
}
