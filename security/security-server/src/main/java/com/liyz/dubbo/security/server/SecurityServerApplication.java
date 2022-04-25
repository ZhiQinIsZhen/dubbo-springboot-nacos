package com.liyz.dubbo.security.server;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注释:启动类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 11:19
 */
@EnableDubbo
@SpringBootApplication
public class SecurityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServerApplication.class, args);
    }
}
