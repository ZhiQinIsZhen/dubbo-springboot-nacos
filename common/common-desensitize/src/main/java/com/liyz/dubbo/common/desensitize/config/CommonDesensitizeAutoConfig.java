package com.liyz.dubbo.common.desensitize.config;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * 注释:脱敏自动装配类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/21 13:26
 */
@Configuration
@ComponentScan(basePackages = {"com.liyz.dubbo.common.desensitize"})
@AutoConfigureOrder(value = Ordered.HIGHEST_PRECEDENCE)
public class CommonDesensitizeAutoConfig {
}
