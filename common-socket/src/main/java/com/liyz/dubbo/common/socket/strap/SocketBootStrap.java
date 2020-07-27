package com.liyz.dubbo.common.socket.strap;

import com.liyz.dubbo.common.socket.config.NettyProperties;
import com.liyz.dubbo.common.socket.service.user.UserService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 注释:netty 启动线程
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 14:24
 */
@Slf4j
@Component
public class SocketBootStrap implements Runnable {

    @Autowired
    NettyProperties nettyProperties;
    @Autowired
    UserService userService;

    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workGroup = null;

    @SneakyThrows
    @Override
    public void run() {
        log.info("socket server start ...");
        Class channelClass;
        if (nettyProperties.isEPoll()) {
            bossGroup = new EpollEventLoopGroup(1, new DefaultThreadFactory("boss", true));
            workGroup = new EpollEventLoopGroup(10, new DefaultThreadFactory("worker", true));
            channelClass = EpollServerSocketChannel.class;
        } else {
            bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("boss", true));
            workGroup = new NioEventLoopGroup(10, new DefaultThreadFactory("worker", true));
            channelClass = NioServerSocketChannel.class;
        }
        final ServerBootstrap bootstrap = new ServerBootstrap();
        // 地址复用
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        // 发送缓冲区
        bootstrap.option(ChannelOption.SO_SNDBUF, nettyProperties.getSndbuf());
        // 接收缓冲区
        bootstrap.option(ChannelOption.SO_RCVBUF, nettyProperties.getRcvbug());
        // 客户端调用超时时间
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, nettyProperties.getConnectTimeout());
        // 低延时多交互次数
        bootstrap.option(ChannelOption.TCP_NODELAY, nettyProperties.isNodelay());
        // 探测客户端存活性
        bootstrap.option(ChannelOption.SO_KEEPALIVE, nettyProperties.isKeepalive());
        // 客户端连接超时间
        bootstrap.option(ChannelOption.SO_TIMEOUT, nettyProperties.getSocketTimeOut());
        // 处理线程全满时，临时缓存的请求个数
        bootstrap.option(ChannelOption.SO_BACKLOG, nettyProperties.getBacklog());

        final WriteBufferWaterMark write = new WriteBufferWaterMark(nettyProperties.getWriteBufferLow(), nettyProperties.getWriteBufferHigh());
        bootstrap.option(ChannelOption.WRITE_BUFFER_WATER_MARK, write);
        bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        // 动态调整
        bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT);
        bootstrap.group(bossGroup, workGroup).channel(channelClass).childHandler(
                new SocketChannelInitializer(nettyProperties.getSocketPath(), nettyProperties.getReaderIdleTime(),
                        nettyProperties.getWriterIdleTime(), nettyProperties.getAllIdleTime(), userService)
        );

        ChannelFuture channelFuture = bootstrap.bind(nettyProperties.getPort()).sync();
        log.info("push server start success");
        channelFuture.channel().closeFuture().sync();
    }

    public void stop() {
        log.info("push server stop ...");
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workGroup != null) {
            workGroup.shutdownGracefully();
        }
        log.info("push server stop success");
    }
}
