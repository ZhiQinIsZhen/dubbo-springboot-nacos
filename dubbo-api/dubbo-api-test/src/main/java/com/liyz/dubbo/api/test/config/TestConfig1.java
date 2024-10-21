package com.liyz.dubbo.api.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/8/20 21:55
 */
@RestController
public class TestConfig1 {

    public TestConfig1() {
        System.out.println("RestController");
    }
}
