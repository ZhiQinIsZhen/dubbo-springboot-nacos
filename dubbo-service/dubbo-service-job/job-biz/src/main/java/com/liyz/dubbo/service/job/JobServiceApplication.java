package com.liyz.dubbo.service.job;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.shardingsphere.elasticjob.lite.spring.core.scanner.ElasticJobScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/26 10:55
 */
@EnableDubbo
@ElasticJobScan(basePackages = {"com.liyz.dubbo.service.job.service"})
@SpringBootApplication
public class JobServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobServiceApplication.class, args);
    }
}
