package com.liyz.dubbo.common.controller.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 注释:controller自动配置
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 14:05
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"com.liyz.dubbo.common.controller"})
public class CommonControllerAutoConfig {

    @Autowired
    private ErrorProperties errorProperties;

    public CommonControllerAutoConfig() {

    }
}
