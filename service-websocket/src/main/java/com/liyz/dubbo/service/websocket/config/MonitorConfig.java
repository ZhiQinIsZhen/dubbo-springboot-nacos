package com.liyz.dubbo.service.websocket.config;

import com.liyz.dubbo.service.websocket.strap.SocketBootStrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/2/1 10:58
 */
@Slf4j
@Component
public class MonitorConfig {

    @Resource
    private SocketBootStrap socketBootStrap;

    private ScheduledExecutorService scheduledExecutorService;

    @PostConstruct
    public void init() {
        socketBootStrap.init();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(socketBootStrap.getMonitorTask(), 5000,5000, TimeUnit.MILLISECONDS);
    }

    @PreDestroy
    public void destroy() {
        log.info("destroy start ...");
        if (Objects.nonNull(scheduledExecutorService) && !scheduledExecutorService.isShutdown()) {
            scheduledExecutorService.isShutdown();
        }
    }
}
