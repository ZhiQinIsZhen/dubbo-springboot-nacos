package com.liyz.dubbo.common.controller.limit.enums;

/**
 * 注释: 限流类型
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/10 14:02
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
