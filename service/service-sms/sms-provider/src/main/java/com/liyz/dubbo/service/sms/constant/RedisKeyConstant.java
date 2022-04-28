package com.liyz.dubbo.service.sms.constant;

import com.liyz.dubbo.common.core.constant.CommonConstant;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

/**
 * 注释:redis key 常量
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/28 13:59
 */
@UtilityClass
public class RedisKeyConstant {

    private static final String PROJECT_PREFIX = "sms";

    private static final String IMAGE_TOKEN = PROJECT_PREFIX + "image:token";

    /**
     * 获取image token
     *
     * @param key
     * @return
     */
    public static String getImageTokenKey(String key) {
        return joinKey(IMAGE_TOKEN, key);
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
            throw new IllegalArgumentException(CommonExceptionCodeEnum.PARAMS_ERROR.getMessage());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        for (String str : keys) {
            sb.append(CommonConstant.DEFAULT_SPLIT).append(str);
        }
        return sb.toString();
    }
}
