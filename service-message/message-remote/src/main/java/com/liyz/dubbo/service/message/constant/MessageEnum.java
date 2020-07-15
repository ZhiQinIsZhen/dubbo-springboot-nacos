package com.liyz.dubbo.service.message.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 注释:消息常量
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/14 18:02
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageEnum {

    @AllArgsConstructor
    public enum MessageType {
        MOBILE(0, "短信"),
        EMAIL(1, "邮件"),
        ;

        @Getter
        public int type;
        @Getter
        private String desc;

        public static MessageType getByCode(int type) {
            for (MessageType messageType : MessageType.values()) {
                if (messageType.type == type) {
                    return messageType;
                }
            }
            return null;
        }
    }
}
