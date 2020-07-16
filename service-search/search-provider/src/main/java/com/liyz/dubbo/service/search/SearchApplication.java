package com.liyz.dubbo.service.search;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注释:启动类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/16 15:02
 */
@EnableDubbo(scanBasePackages = {"com.liyz.dubbo.service.search.provider"})
@SpringBootApplication(scanBasePackages = {"com.liyz.dubbo.common", "com.liyz.dubbo.service.search"})
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }
}
