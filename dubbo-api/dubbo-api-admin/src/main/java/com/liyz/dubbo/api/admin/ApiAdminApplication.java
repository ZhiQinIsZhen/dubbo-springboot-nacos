package com.liyz.dubbo.api.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Desc:启动类
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/12 16:48
 */
@EnableAsync
@SpringBootApplication
public class ApiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiAdminApplication.class, args);
    }
}
