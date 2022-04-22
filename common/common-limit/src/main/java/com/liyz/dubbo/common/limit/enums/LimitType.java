package com.liyz.dubbo.common.limit.enums;

/**
 * 注释:限流枚举类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 10:29
 */
public enum LimitType {

    /**
     * ip地址
     */
    IP,
    /**
     * 全局总的次数
     */
    TOTAL,
    /**
     * api mapping级
     */
    MAPPING,
    /**
     * 自定义
     */
    CUSTOMIZE,
    ;
}
