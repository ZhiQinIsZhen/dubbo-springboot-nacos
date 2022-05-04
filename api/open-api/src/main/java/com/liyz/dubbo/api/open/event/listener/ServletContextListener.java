package com.liyz.dubbo.api.open.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 注释:spring上下文时间监听器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/11/24 14:24
 */
@Slf4j
@Component
public class ServletContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private String applicationName;

    public ServletContextListener(ApplicationContext applicationContext) {
        this.applicationName = applicationContext.getEnvironment().getProperty("spring.application.name", "application");
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Application name {} running success", applicationName);
    }

    @EventListener
    public void closeEvent(ContextClosedEvent event) {
        log.info("Application name {} closed success", applicationName);
    }

    @EventListener
    public void startEvent(ContextStartedEvent event) {
        log.info("Application name {} start success", applicationName);
    }

    @EventListener
    public void stopEvent(ContextClosedEvent event) {
        log.info("Application name {} closed success", applicationName);
    }
}
