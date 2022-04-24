package com.liyz.dubbo.security.client.config;

import lombok.Getter;
import org.springframework.security.access.ConfigAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * 注释:获取可访问的 attribute 值
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 10:26
 */
public class ConfigAttributeImpl implements ConfigAttribute {

    @Getter
    private final HttpServletRequest httpServletRequest;

    public ConfigAttributeImpl(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public String getAttribute() {
        return null;
    }
}
