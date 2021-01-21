package com.liyz.dubbo.service.member.provider;

import com.google.common.eventbus.EventBus;
import com.liyz.dubbo.service.member.bus.UserEvent;
import com.liyz.dubbo.service.member.bus.UserListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/1/19 17:19
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RemoteUserInfoServiceImplTest.class})
public class RemoteUserInfoServiceImplTest {

    @Test
    public void test() {
        EventBus eventBus = new EventBus("test");
        eventBus.register(new UserListener());
        eventBus.post(new UserEvent("Bonnie"));
    }

}