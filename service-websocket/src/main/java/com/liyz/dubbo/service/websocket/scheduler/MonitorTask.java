package com.liyz.dubbo.service.websocket.scheduler;

import com.liyz.dubbo.service.websocket.strap.SocketBootStrap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 注释:监控websocket client 是否断开
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/2/1 10:42
 */
@Slf4j
public class MonitorTask implements Runnable {

    @Getter
    @Setter
    private long updateTime = System.currentTimeMillis();

    private static final Long CHECK_TIME = 5000L;

    private SocketBootStrap socketBootStrap;

    public MonitorTask(SocketBootStrap socketBootStrap) {
        this.socketBootStrap = socketBootStrap;
    }

    @Override
    public void run() {
        log.info("monitor start run...");
        if (System.currentTimeMillis() - updateTime > CHECK_TIME) {
            try {
                socketBootStrap.refresh();
            } catch (Exception e) {
                log.error("websocket client refresh fail ...", e);
            }
        }
        log.info("monitor end run...");
    }
}
