package com.liyz.dubbo.service.search.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/11/5 10:42
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortPathServiceTest {

    @Autowired
    ShortPathService shortPathService;

    @Test
    public void deleteTest() {
        List<String> list = Lists.newArrayList("64786241_72220773_4", "64786241_72220773_6", "1698375_72220773_6");
        shortPathService.delete(list);
    }
}