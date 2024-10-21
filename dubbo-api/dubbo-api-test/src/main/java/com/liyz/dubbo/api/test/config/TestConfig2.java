package com.liyz.dubbo.api.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/8/20 21:56
 */
@Component
public class TestConfig2 {

    public TestConfig2() {
        System.out.println("Component");
    }
}
