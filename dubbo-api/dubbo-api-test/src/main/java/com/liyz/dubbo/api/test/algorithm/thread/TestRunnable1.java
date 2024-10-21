package com.liyz.dubbo.api.test.algorithm.thread;

import lombok.Setter;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/8/2 19:48
 */
public class TestRunnable1 implements Runnable{

    private static volatile int NUM = 1;

    @Setter
    private Thread before;

    private final String name;

    public TestRunnable1(Thread before, String name) {
        this.name = name;
        this.before = before;
    }

    public TestRunnable1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            if (before != null) {
                before.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name + NUM);
        NUM++;
    }

    public static void main(String[] args) {
        Thread a = new Thread(new TestRunnable1("a"));
        Thread b = new Thread(new TestRunnable1(a, "b"));
        Thread c = new Thread(new TestRunnable1(b, "c"));
        b.start();
        c.start();
        a.start();
    }
}
