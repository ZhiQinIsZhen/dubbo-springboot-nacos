package com.liyz.dubbo.common.limit.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Desc:限流properties
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/5/24 16:25
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "request.limit")
public class LimitProperties {

    private boolean enable = false;

    private double totalCount = 1.0;

    private String model = "caffeine";
}
