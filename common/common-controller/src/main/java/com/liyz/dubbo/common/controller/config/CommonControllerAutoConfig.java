package com.liyz.dubbo.common.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 注释:controller自动配置
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 14:05
 */
@Configuration
@ComponentScan(basePackages = {"com.liyz.dubbo.common.controller"})
public class CommonControllerAutoConfig {
}
