package com.liyz.dubbo.api.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/8/20 21:57
 */
@Configuration
public class TestConfig3 {

    public TestConfig3() {
        System.out.println("Configuration");
    }
}
