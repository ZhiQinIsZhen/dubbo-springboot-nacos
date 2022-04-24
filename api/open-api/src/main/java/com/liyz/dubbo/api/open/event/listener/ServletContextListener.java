package com.liyz.dubbo.api.open.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/11/24 14:24
 */
@Slf4j
@Component
public class ServletContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("{} 应用启动成功了 ***************************",
                applicationContext.getEnvironment().getProperty(
                        "spring.application.name",
                        "application")
        );
    }
}
