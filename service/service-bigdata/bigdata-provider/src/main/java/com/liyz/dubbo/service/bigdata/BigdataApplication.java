package com.liyz.dubbo.service.bigdata;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/17 15:52
 */
@EnableDubbo
@SpringBootApplication
@MapperScan(basePackages = {"com.liyz.dubbo.service.bigdata.dao"})
public class BigdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigdataApplication.class, args);
    }
}
