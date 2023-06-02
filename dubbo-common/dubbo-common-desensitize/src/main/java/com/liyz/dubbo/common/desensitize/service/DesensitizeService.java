package com.liyz.dubbo.common.desensitize.service;

import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;

/**
 * Desc:反序列化脱敏接口
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/2 15:06
 */
public interface DesensitizeService {

    /**
     * 脱敏
     *
     * @param value 原字段
     * @param annotation 脱毛注解
     * @return 脱敏后字段
     */
    String desensitize(String value, Desensitization annotation);

    /**
     * 获取对应脱敏类型
     *
     * @return 脱敏类型
     */
    DesensitizationType getType();
}
