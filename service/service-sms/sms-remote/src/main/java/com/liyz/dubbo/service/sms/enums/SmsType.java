package com.liyz.dubbo.service.sms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注释:信息类型枚举类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 15:33
 */
@AllArgsConstructor
public enum SmsType {
    REGISTER(1, "注册"),
    LOGIN(2, "登陆"),
    RETRIEVE_PASSWORD(3, "找回密码"),
    CHANGE_PASSWORD(4, "修改登录密码"),
    GOOGLE_AUTHENTICATOR(5, "谷歌验证"),
    BIND_MOBILE(6, "绑定手机"),
    BIND_EMAIL(7, "绑定邮箱"),
    ;

    @Getter
    private int code;

    @Getter
    private String desc;

    public static SmsType getByCode(int code) {
        for (SmsType amsType : SmsType.values()) {
            if (amsType.code == code) {
                return amsType;
            }
        }
        return null;
    }
}
