package com.liyz.dubbo.service.search.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/11/17 15:46
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class QccVagueServiceTest {

    @Autowired
    QccVagueService qccVagueService;

    @Test
    public void test() {
        qccVagueService.delete(Lists.newArrayList("530625600039335", "92540194MA6T4WP105", "2658504"));
    }
}