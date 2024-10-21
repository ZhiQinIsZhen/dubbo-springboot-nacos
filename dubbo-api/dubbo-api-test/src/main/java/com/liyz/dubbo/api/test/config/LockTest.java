package com.liyz.dubbo.api.test.config;

import org.openjdk.jol.info.ClassLayout;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/8/20 21:08
 */
public class LockTest {

    public static void main(String[] args) {
        Object test = new Object();
        System.out.println(ClassLayout.parseInstance(test).toPrintable());

        synchronized (test) {
            System.out.println(ClassLayout.parseInstance(test).toPrintable());
        }

        System.out.println(ClassLayout.parseInstance(test).toPrintable());
    }
}
