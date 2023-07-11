package com.liyz.dubbo.service.pdf.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/11 10:50
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceImplTest {

    @Test
    public void test() {
        String html = TestServiceImpl.html();
        log.info("html : {}", html);
    }
}