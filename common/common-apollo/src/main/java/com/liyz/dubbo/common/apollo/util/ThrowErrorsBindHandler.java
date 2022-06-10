package com.liyz.dubbo.common.apollo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.AbstractBindHandler;
import org.springframework.boot.context.properties.bind.BindContext;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/10 11:18
 */
@Slf4j
public class ThrowErrorsBindHandler extends AbstractBindHandler {

    ThrowErrorsBindHandler(BindHandler parent) {
        super(parent);
    }

    @Override
    public Object onFailure(ConfigurationPropertyName name, Bindable<?> target, BindContext context, Exception error)
            throws Exception {
        log.info("binding error", error);
        throw error;
    }
}
