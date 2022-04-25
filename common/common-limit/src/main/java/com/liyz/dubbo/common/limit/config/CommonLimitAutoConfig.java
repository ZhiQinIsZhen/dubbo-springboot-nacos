package com.liyz.dubbo.common.limit.config;

import com.liyz.dubbo.common.limit.properties.LimitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * 注释:限流自动配置类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 10:26
 */
@ConditionalOnProperty(prefix = "request.limit", name = "enable", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties({LimitProperties.class})
@ComponentScan(basePackages = {"com.liyz.dubbo.common.limit.service.impl"})
@Configuration
public class CommonLimitAutoConfig implements Ordered {

    private LimitProperties limitProperties;

    public CommonLimitAutoConfig(LimitProperties limitProperties) {
        this.limitProperties = limitProperties;
        System.setProperty("request.limit.totalCount", String.valueOf(limitProperties.getTotalCount()));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
