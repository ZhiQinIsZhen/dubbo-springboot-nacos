package com.liyz.dubbo.service.socket.strap;

import com.liyz.dubbo.service.socket.config.NettyProperties;
import com.liyz.dubbo.service.socket.core.RobotHandler;
import com.liyz.dubbo.service.socket.core.SocketChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;

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

    private EventLoopGroup workGroup = null;

    @SneakyThrows
    @Override
    public void run() {
        log.info("socket client start ...");
        URI uri = new URI("wss://api.huobipro.com/ws");
        Class channelClass;
        if (nettyProperties.isEPoll()) {
            workGroup = new EpollEventLoopGroup(10, new DefaultThreadFactory("worker", true));
            channelClass = EpollServerSocketChannel.class;
        } else {
            workGroup = new NioEventLoopGroup(10, new DefaultThreadFactory("worker", true));
            channelClass = NioServerSocketChannel.class;
        }
        final Bootstrap client = new Bootstrap();
        client.option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .group(workGroup)
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(channelClass)
                .handler(new SocketChannelInitializer());

        HttpHeaders headers = new DefaultHttpHeaders();
        WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory
                .newHandshaker(uri, WebSocketVersion.V13, null, true, headers);
        final Channel channel = client.connect(uri.getHost(), uri.getPort()).sync().channel();
        RobotHandler handler = (RobotHandler) channel.pipeline().get("robotHandler");
        handler.setHandshaker(handshaker);
        handshaker.handshake(channel);
        handler.getChannelPromise().sync();
    }

    public void stop() {
        log.info("push server stop ...");
        if (workGroup != null) {
            workGroup.shutdownGracefully();
        }
        log.info("push server stop success");
    }
}
