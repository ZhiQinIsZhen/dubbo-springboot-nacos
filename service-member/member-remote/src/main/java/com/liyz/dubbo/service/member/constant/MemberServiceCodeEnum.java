package com.liyz.dubbo.service.member.constant;

import com.liyz.dubbo.common.remote.exception.service.ServiceCodeEnum;
import lombok.AllArgsConstructor;

/**
 * 注释: member错误码  以20***开头
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/7 15:01
 */
@AllArgsConstructor
public enum MemberServiceCodeEnum implements ServiceCodeEnum {

    MobileExist("20001", "手机号已存在"),
    EmailExist("20002", "邮箱号已存在"),
    MobileEmailNonMatch("20003", "用户名格式不正确"),
    RegisterFail("20004", "注册失败"),
    AddressNonMatch("20005", "联系地址格式不正确"),
    SmsMobileSendAfter("20006", "短信已发送,请1分钟后重试"),
    SmsEmailSendAfter("20007", "邮件已发送,请1分钟后重试"),
    SmsLimit("20008", "信息发送已达上限"),
    UserNonExist("20009", "该用户不存在"),
    ;

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
