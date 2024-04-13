package com.liyz.dubbo.service.search;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Desc:启动类
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 14:16
 */
@EnableDubbo
@SpringBootApplication
public class SearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }
}
