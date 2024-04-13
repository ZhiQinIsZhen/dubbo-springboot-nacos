package com.liyz.dubbo.common.limit.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注释:限流枚举类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 10:29
 */
@AllArgsConstructor
public enum LimitType {
    IP("IP地址"),
    IP_MAPPING("IP地址+API Mapping级"),
    TOTAL("全局总的次数"),
    MAPPING("API Mapping级"),
    USER_MAPPING("用户+API Mapping级"),
    CUSTOMIZATION("自定义"),
    ;

    @Getter
    private final String desc;
}
