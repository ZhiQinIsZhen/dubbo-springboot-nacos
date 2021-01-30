package com.liyz.dubbo.service.test;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 注释:启动类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/1/29 16:32
 */
@MapperScan({"com.liyz.dubbo.service.test.dao"})
@EnableCaching
@EnableDubbo(scanBasePackages = {"com.liyz.dubbo.service.test.provider"})
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
