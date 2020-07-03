package com.liyz.dubbo.service.member.constant;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/11 15:34
 */
public interface MemberConstant {

    /**
     * 登陆日志类型
     */
    Integer REGISTER_TYPE = 0;
    Integer LOGIN_TYPE = 1;

    /**
     * 验证码类型
     * 1:登录，2:注册，3:找回密码，4：谷歌验证，5：绑定手机，6：绑定邮箱，7：修改登录密码
     */
    Integer SMS_LOGIN_TYPE = 1;
    Integer SMS_REGISTER_TYPE = 2;
    Integer SMS_FIND_PWDTYPE = 3;
    Integer SMS_GOOGLE_TYPE = 4;
    Integer SMS_BIND_MOBILE_TYPE = 5;
    Integer SMS_BIND_EMAIL_TYPE = 6;
    Integer SMS_UPDATE_PWD_TYPE = 7;

    String REGISTER_SUBJECT = "用户注册验证码";
    String ZH_CN = "zh_CN";
}
