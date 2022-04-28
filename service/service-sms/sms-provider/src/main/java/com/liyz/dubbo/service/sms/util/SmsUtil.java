package com.liyz.dubbo.service.sms.util;

import com.liyz.dubbo.common.core.constant.CommonConstant;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注释:工具类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 15:53
 */
@UtilityClass
public class SmsUtil {

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * 匹配手机
     *
     * @param mobile
     * @return
     */
    public static boolean matchMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        Pattern p = Pattern.compile(CommonConstant.EMAIL_REG);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * 匹配邮箱
     *
     * @param email
     * @return
     */
    public static boolean matchEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        Pattern p = Pattern.compile(CommonConstant.EMAIL_REG);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 校验地址是否是邮件或者手机号码格式，如果不是，则抛出异常
     *
     * @param address
     * @return 1：手机号码；2：邮件；-1：无法判断
     */
    public static int checkMobileEmail(String address) {
        int type = -1;
        if (matchMobile(address)) {
            type = 1;
        } else if (matchEmail(address)){
            type = 2;
        }
        return type;
    }

    /**
     * 获取随机数字
     *
     * @param length 随机数长度
     * @return
     */
    public static String randomInteger(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
