package com.liyz.dubbo.common.base.filter.log;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 注释:自定义log脱敏过滤器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/6/2 16:57
 */
public class LogDesensitizationContextValueFilter extends MessageConverter {

    @Override
    public String convert(ILoggingEvent event) {
        // 获取原始日志
        String oriLogMsg = event.getFormattedMessage();
        // todo 这里进行处理

        return oriLogMsg;
    }
}
