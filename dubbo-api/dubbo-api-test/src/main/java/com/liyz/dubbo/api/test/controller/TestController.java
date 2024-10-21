package com.liyz.dubbo.api.test.controller;

import com.liyz.dubbo.api.test.vo.TestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2024/5/17 15:27
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/init")
    public TestVO init() {
        TestVO testVO = new TestVO();
        testVO.setName("liyz");
        testVO.setId(1787312316911792129L);
        return testVO;
    }

    @GetMapping("/init1")
    public TestVO init1() {
        TestVO testVO = new TestVO();
        testVO.setName("liyz1");
        return testVO;
    }

    @GetMapping("/init2")
    public Boolean init2() throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executorService.execute(() -> {
            log.info("1111");
        });
        Thread.sleep(1000);
        executorService.execute(() -> {
            log.info("1111");
        });
        Thread.sleep(1000);
        executorService.execute(() -> {
            log.info("1111");
        });
        Thread.sleep(1000);
        executorService.execute(() -> {
            log.info("1111");
        });
        Thread.sleep(1000);
        Thread.sleep(1000);
        executorService.execute(() -> {
            log.info("1111");
        });
        Thread.sleep(1000);
        Thread.sleep(1000);
        executorService.execute(() -> {
            log.info("1111");
        });
        Thread.sleep(1000);
        Thread.sleep(1000);
        executorService.execute(() -> {
            log.info("1111");
        });
        Thread.sleep(1000);
        Thread.sleep(1000);
        executorService.execute(() -> {
            log.info("1111");
        });
        Thread.sleep(1000);
        return Boolean.TRUE;
    }

    @GetMapping("/init3")
    public Boolean init3() {
        log.info("0000");
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
               log.info("1111");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        log.info("2222");
        return Boolean.TRUE;
    }

    public static void main(String[] args) {
        /*ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        map.compute(1, (k, v) -> v == null ? 1 : v + 1);
        map.put(1, 1);

        Map<Integer, Integer> integerMap = new HashMap<>();

        int n = 32;
        System.out.println("n:" + (n - (n>>>2)));

        Executors.newSingleThreadExecutor();
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
//        executorService.execute();
        executorService.allowCoreThreadTimeOut(true);
        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(1);
        Executors.newWorkStealingPool();*/

        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY   = (1 << COUNT_BITS) - 1;
        System.out.println(CAPACITY);

        Map<String, String> map = new HashMap<>(3);
    }
}
