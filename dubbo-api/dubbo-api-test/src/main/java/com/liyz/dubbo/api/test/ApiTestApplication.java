package com.liyz.dubbo.api.test;

import com.liyz.dubbo.api.test.config.TestConfig1;
import com.liyz.dubbo.api.test.config.TestConfig2;
import com.liyz.dubbo.api.test.config.TestConfig3;
import com.liyz.dubbo.api.test.config.TestConfig4;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/5/17 15:25
 */
@SpringBootApplication
public class ApiTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ApiTestApplication.class, args);
        System.out.println(applicationContext.getBean(TestConfig1.class));
        System.out.println(applicationContext.getBean(TestConfig2.class));
        System.out.println(applicationContext.getBean(TestConfig3.class));
        System.out.println(applicationContext.getBean(TestConfig4.class));
    }
}