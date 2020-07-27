package com.liyz.dubbo.common.socket.task;

import com.liyz.dubbo.common.socket.storage.SessionStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 注释:监控中心 task
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/27 16:20
 */
@Slf4j
@Component
public class MonitorTask {

    @Scheduled(initialDelay = 5000, fixedRate = 10000)
    public void monitor() {
        log.info("session monitor : {}", SessionStorage.getChannels());
    }
}
