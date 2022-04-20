package com.liyz.dubbo.common.core.constant;

/**
 * 注释:通用常量类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 17:21
 */
public interface CommonConstant {

    /**
     * 邮箱正则表达式
     */
    String EMAIL_REG = "^([a-zA-Z0-9_\\-\\.\\+]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

    /**
     * 手机正则表达式
     */
    String PHONE_REG = "^1(3|4|5|7|8|9)\\d{9}$";

    /**
     * 默认分隔符
     */
    String DEFAULT_SPLIT = "_";

    /**
     * 方法分隔符
     */
    String METHOD_SPLIT = ".";

    /**
     * 请求id
     */
    String REQUEST_ID = "request_id";

    /**
     * 认证信息
     */
    String AUTH_INFO = "auth_info";

    /**
     * 父片id
     */
    String PARENT_SPAN_ID = "parent_span_id";

    /**
     * 默认连接符
     */
    String DEFAULT_JOINER = ":";

    /**
     * web设备
     */
    Integer DEVICE_WEB = 1;
    /**
     * 手机设备
     */
    Integer DEVICE_MOBILE = 2;
}
