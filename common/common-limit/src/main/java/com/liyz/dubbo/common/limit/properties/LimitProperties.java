package com.liyz.dubbo.common.limit.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 注释:限流properties
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 10:31
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "request.limit")
public class LimitProperties {

    private boolean enable = false;

    private double totalCount = 1.0;

    private String model = "caffeine";
}
