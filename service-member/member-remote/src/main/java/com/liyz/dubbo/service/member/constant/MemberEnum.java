package com.liyz.dubbo.service.member.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/28 14:34
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemberEnum {

    @AllArgsConstructor
    public enum DeviceEnum {

        WEB("PC"),
        MOBILE("IOS/Android/Pad"),
        ALL("PC/IOS/AndRoid/Pad");

        @Getter
        private String device;
    }
}
