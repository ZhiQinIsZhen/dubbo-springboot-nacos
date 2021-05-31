package com.liyz.dubbo.service.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

/**
 * 注释:定时任务线程池
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 10:59
 */
@EnableScheduling
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setDaemon(true);
        scheduler.setPoolSize(2);
        scheduler.setThreadNamePrefix("service-websocket-task-");
        scheduler.setAwaitTerminationSeconds(300);
        scheduler.setWaitForTasksToCompleteOnShutdown(false);
        return scheduler;
    }
}
