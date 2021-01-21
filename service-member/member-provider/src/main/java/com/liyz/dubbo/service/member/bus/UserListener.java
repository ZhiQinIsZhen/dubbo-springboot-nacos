package com.liyz.dubbo.service.member.bus;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/1/19 17:27
 */
@Slf4j
public class UserListener {

    @Subscribe
    public void dealWithEvent(UserEvent event) {
        log.info("hello {}", event.getUserName());
    }
}
