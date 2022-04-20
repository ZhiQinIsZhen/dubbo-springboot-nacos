package com.liyz.dubbo.common.core.desen;

/**
 * 注释:反序列化脱敏接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 19:30
 */
public interface IDesensitizeService {

    /**
     * 脱敏
     *
     * @param value
     * @param annotation
     * @return
     */
    String desensitize(String value, Desensitization annotation);
}
