package com.liyz.dubbo.service.websocket.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注释:netty配置信息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 14:06
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "io.netty")
public class NettyProperties {

    private int workThread = 2;

    private boolean daemon = true;

    private boolean ePoll = false;

    private String workerPollName = "worker";

    private String address;

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

    private long readerIdleTime = 60;

    private long writerIdleTime = 0;

    private long allIdleTime = 0;
}
