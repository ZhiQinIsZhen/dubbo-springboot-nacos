package com.liyz.dubbo.api.test.algorithm.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/8/2 19:08
 */
@Slf4j
public class TestRunnable implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("run thread");
    }

    public static void main(String[] args) {
        log.info("started thread");
        Runnable runnable = new TestRunnable();
        runnable.run();
        log.info("end thread");
    }
}
