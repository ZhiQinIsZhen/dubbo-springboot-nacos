package com.liyz.dubbo.security.core.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 注释:security枚举类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 9:31
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityEnum {

    @AllArgsConstructor
    public enum AudienceType {
        Member("member", "用户"),
        Staff("staff", "员工"),
        ;

        @Getter
        private String code;

        @Getter
        private String desc;

        public static AudienceType getByCode(String code) {
            for (AudienceType audienceType : AudienceType.values()) {
                if (audienceType.code.equals(code)) {
                    return audienceType;
                }
            }
            return null;
        }
    }
}
