package com.liyz.dubbo.api.test.config;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/8/15 14:32
 */
public class Test2Config {

    static {
        System.out.println("静态方法");
    }

    {
        System.out.println("静态方法1");
    }

    public Test2Config() {
        System.out.println("静态方法3");
    }

    public static void main(String[] args) {
        Test2Config.class.getClass();
        new Test2Config();
    }
}
