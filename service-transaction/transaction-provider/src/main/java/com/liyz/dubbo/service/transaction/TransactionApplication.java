package com.liyz.dubbo.service.transaction;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 注释:启动类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/20 10:34
 */
@EnableTransactionManagement
@MapperScan(basePackages = {"com.liyz.dubbo.service.transaction.dao"})
@EnableDubbo(scanBasePackages = {"com.liyz.dubbo.service.transaction.provider"})
@SpringBootApplication(scanBasePackages = {"com.liyz.dubbo.common", "com.liyz.dubbo.service.transaction"})
public class TransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }
}
