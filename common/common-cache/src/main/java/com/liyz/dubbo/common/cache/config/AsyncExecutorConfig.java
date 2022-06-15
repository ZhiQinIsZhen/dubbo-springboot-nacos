package com.liyz.dubbo.common.cache.config;

import com.liyz.dubbo.common.cache.task.MonitorTask;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 注释:定时任务配置
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/11/24 10:55
 */
@EnableScheduling
@EnableAsync(proxyTargetClass = true)
@Configuration
@AutoConfigureBefore(TaskExecutionAutoConfiguration.class)
@EnableConfigurationProperties({CacheRedisCaffeineProperties.class})
@ConditionalOnProperty(name = "spring.cache.multi.monitor", havingValue = "true", matchIfMissing = true)
public class AsyncExecutorConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(1);
        executor.setDaemon(true);
        executor.setThreadNamePrefix("async-cache-monitor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean
    public MonitorTask monitorTask() {
        return new MonitorTask();
    }
}
