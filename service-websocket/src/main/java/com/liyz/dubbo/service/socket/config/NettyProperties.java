package com.liyz.dubbo.service.socket.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 注释:netty配置信息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 14:06
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "io.netty")
public class NettyProperties {

    private int bossThread = 1;

    private int workThread = 10;

    private int port;

    private boolean ePoll = false;

    private String bossPollName = "boss";

    private String workerPollName = "worker";

    /**
     * 发送缓冲区
     */
    private Integer sndbuf = 64 * 1024;

    /**
     * 接受缓冲区
     */
    private Integer rcvbug = 64 * 1024;

    /**
     * 连接超时时间 ms
     */
    private Integer connectTimeout = 15000;

    /**
     * 低延时多交互次数
     */
    private boolean nodelay = true;

    /**
     * 火星探测
     */
    private boolean keepalive =true;

    /**
     * 客户端连接超时间 ms
     */
    private Integer socketTimeOut = 15000;

    /**
     * 处理线程全满时，临时缓存的请求个数
     */
    private Integer backlog = 1024;

    private int writeBufferLow = 512 * 1024;

    private int writeBufferHigh = 1024 * 1024;

    private String socketPath = "/websocket";

    private long readerIdleTime = 60;

    private long writerIdleTime = 0;

    private long allIdleTime = 0;
}
