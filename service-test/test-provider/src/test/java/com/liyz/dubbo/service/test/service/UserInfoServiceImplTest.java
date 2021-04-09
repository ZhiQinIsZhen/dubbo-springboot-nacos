package com.liyz.dubbo.service.test.service;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/3/30 15:55
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceImplTest {

    @Resource
    StringEncryptor stringEncryptor;

    @Value("${ABC}")
    private String abc;

    @Test
    public void test() {
        log.info("value:{}", stringEncryptor.encrypt("abc"));
        log.info("value:{}", stringEncryptor.decrypt(abc));
    }
}