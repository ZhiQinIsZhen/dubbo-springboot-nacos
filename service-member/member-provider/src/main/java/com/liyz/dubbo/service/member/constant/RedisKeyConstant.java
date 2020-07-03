package com.liyz.dubbo.service.member.constant;

import com.liyz.dubbo.common.base.constant.CommonConstant;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/12 10:52
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RedisKeyConstant {

    /**
     * 项目名
     */
    private static final String PROJECT_NAME = "member:";

    /**
     * sms验证码
     */
    private static final String SMS_CODE = PROJECT_NAME + "sms:code:";

    /**
     * sms验证码发送次数
     */
    private static final String SMS_Times = PROJECT_NAME + "sms:times:";

    private static final String IMAGE_TOKEN = PROJECT_NAME + "image:token:";

    private static final String USER_ID_INFO = PROJECT_NAME + "user:id:";

    private static final String LOGIN_NAME_INFO = PROJECT_NAME + "user:login_name:";

    /**
     * 获取sms验证码 key
     *
     * @param keys
     * @return
     */
    public static String getSmsCodeKey(String... keys) {
        return joinKey(SMS_CODE, keys);
    }

    public static String getSmsTimesKey(String key) {
        return joinKey(SMS_Times, key);
    }

    public static String getImageTokenKey(String key) {
        return joinKey(IMAGE_TOKEN, key);
    }

    public static String getUserIdInfoKey(String key) {
        return joinKey(USER_ID_INFO, key);
    }

    public static String getLoginNameInfoKey(String key) {
        return joinKey(LOGIN_NAME_INFO, key);
    }

    /**
     * 拼接redis key
     *
     * @param prefix key前缀
     * @param keys    key
     * @return
     */
    private static String joinKey(String prefix, String... keys) {
        if (StringUtils.isBlank(prefix) || keys == null || keys.length == 0) {
            throw new IllegalArgumentException(CommonCodeEnum.ParameterError.getMessage());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        for (String str : keys) {
            sb.append(str).append(CommonConstant.DEFAULT_SPLIT);
        }
        return sb.substring(0, sb.length() - 1);
    }
}
