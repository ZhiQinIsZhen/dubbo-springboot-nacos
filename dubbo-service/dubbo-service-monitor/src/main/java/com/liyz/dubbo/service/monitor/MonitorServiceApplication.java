package com.liyz.dubbo.service.monitor;

import com.liyz.dubbo.common.api.config.WebMvcAutoConfig;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/26 16:23
 */
@EnableAdminServer
@SpringBootApplication
//@SpringBootApplication(exclude = {WebMvcAutoConfig.class})
public class MonitorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorServiceApplication.class, args);
    }
}
