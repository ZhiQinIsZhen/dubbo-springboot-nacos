package com.liyz.dubbo.service.websocket.config;

import com.liyz.dubbo.service.websocket.strap.SocketBootStrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
@Component
public class MonitorConfig {

    @Autowired
    private SocketBootStrap socketBootStrap;

    @PostConstruct
    public void init() {
        socketBootStrap.init();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(socketBootStrap.getMonitorTask(), 5000,5000, TimeUnit.MILLISECONDS);
    }
}
