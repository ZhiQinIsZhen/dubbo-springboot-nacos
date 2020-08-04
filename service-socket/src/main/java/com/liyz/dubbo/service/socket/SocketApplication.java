package com.liyz.dubbo.service.socket;

import com.liyz.dubbo.common.socket.strap.SocketBootStrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 注释:启动类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/4 15:39
 */
@SpringBootApplication(scanBasePackages = {"com.liyz.dubbo"})
public class SocketApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SocketApplication.class, args);
        SocketBootStrap bootStrap = context.getBean(SocketBootStrap.class);
        new Thread(bootStrap).start();
    }
}
