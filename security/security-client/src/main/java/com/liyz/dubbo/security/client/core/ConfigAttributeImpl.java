package com.liyz.dubbo.security.client.core;

import org.springframework.security.access.ConfigAttribute;

/**
 * 注释:获取可访问的 attribute 值
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 10:26
 */
public class ConfigAttributeImpl implements ConfigAttribute {

    private String attribute;

    public ConfigAttributeImpl(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getAttribute() {
        return attribute;
    }
}
