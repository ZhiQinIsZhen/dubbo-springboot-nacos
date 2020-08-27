package com.liyz.dubbo.service.sharding;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注释:启动类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/26 17:38
 */
@EnableDubbo(scanBasePackages = {"com.liyz.dubbo.service.sharding.provider"})
@SpringBootApplication(scanBasePackages = {"com.liyz.dubbo.common", "com.liyz.dubbo.service.sharding"})
public class ShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingApplication.class, args);
    }
}
