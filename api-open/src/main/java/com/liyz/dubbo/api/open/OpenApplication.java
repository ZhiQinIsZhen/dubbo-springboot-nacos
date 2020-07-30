package com.liyz.dubbo.api.open;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注释:启动类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/25 0:26
 */
@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.liyz.dubbo.common", "com.liyz.dubbo.api.open"})
public class OpenApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenApplication.class, args);
    }
}
