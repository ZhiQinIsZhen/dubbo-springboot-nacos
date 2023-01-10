package com.liyz.dubbo.service.dfa;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注释:启动类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/15 10:41
 */
@EnableDubbo
@SpringBootApplication
@MapperScan(basePackages = {"com.liyz.dubbo.service.dfa.dao"})
public class DfaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfaServiceApplication.class, args);
    }
}
