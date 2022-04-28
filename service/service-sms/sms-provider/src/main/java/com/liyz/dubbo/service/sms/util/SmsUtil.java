package com.liyz.dubbo.service.sms.util;

import com.liyz.dubbo.common.core.constant.CommonConstant;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;
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

    // 使用到Arial字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符(暂时不去除)
    public static final String VERIFY_CODES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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

    /**
     * 使用系统默认字符源生成验证码
     *
     * @param verifySize 验证码长度
     * @return
     */
    public static String generateVerifyCode(int verifySize) {
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    /**
     * 使用指定源生成验证码
     *
     * @param verifySize 验证码长度
     * @param sources    验证码字符源
     * @return
     */
    public static String generateVerifyCode(int verifySize, String sources) {
        if (sources == null || sources.length() == 0) {
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }
}
