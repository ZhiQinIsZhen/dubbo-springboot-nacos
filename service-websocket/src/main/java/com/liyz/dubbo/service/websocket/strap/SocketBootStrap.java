package com.liyz.dubbo.service.websocket.strap;

import com.liyz.dubbo.service.websocket.constant.HuoBiTopicConstant;
import com.liyz.dubbo.service.websocket.core.BusinessHandler;
import com.liyz.dubbo.service.websocket.core.SocketChannelInitializer;
import com.liyz.dubbo.service.websocket.properties.NettyProperties;
import com.liyz.dubbo.service.websocket.properties.SocketServerProperties;
import com.liyz.dubbo.service.websocket.scheduler.MonitorTask;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.UUID;

/**
 * 注释:netty 启动线程
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 14:24
 */
@Slf4j
@Component
@EnableConfigurationProperties({NettyProperties.class})
public class SocketBootStrap {

    @Autowired
    private NettyProperties nettyProperties;
    @Getter
    private EventLoopGroup workGroup;
    private Channel channel;
    @Getter
    private MonitorTask monitorTask;

    private String subId = StringUtils.EMPTY;

    public void init() {
        monitorTask = new MonitorTask(this);
        this.start();
        this.subscribe();
    }

    public void subscribe() {
        String sub = String.format(HuoBiTopicConstant.KLINE_SUB, "btcusdt", HuoBiTopicConstant.PERIOD[0]);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sub", sub);
        jsonObject.put("id", subId);
        log.info("send msg : {}", jsonObject.toString());
        channel.writeAndFlush(new TextWebSocketFrame(jsonObject.toString()));

//        String subEth = String.format(HuoBiTopicConstant.KLINE_SUB, "ethusdt", HuoBiTopicConstant.PERIOD[0]);
//        JSONObject jsonObjectEth = new JSONObject();
//        jsonObjectEth.put("sub", subEth);
//        jsonObjectEth.put("id", subId);
//        log.info("send msg : {}", jsonObjectEth.toString());
//        channel.writeAndFlush(new TextWebSocketFrame(jsonObjectEth.toString()));
    }

    /**
     * 开始
     */
    @SneakyThrows
    public void start() {
        log.info("websocket client start ...");
        subId = UUID.randomUUID().toString();
        final URI uri = URI.create(nettyProperties.getAddress());
        WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory
                .newHandshaker(uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders());
        //解析得到host、port等信息
        SocketServerProperties properties = getHostPort(uri);
        Class channelClass;
        if (nettyProperties.isEPoll()) {
            workGroup = new EpollEventLoopGroup(nettyProperties.getWorkThread(),
                    new DefaultThreadFactory(nettyProperties.getWorkerPollName(),
                            nettyProperties.isDaemon()));
            channelClass = EpollSocketChannel.class;
        } else {
            workGroup = new NioEventLoopGroup(nettyProperties.getWorkThread(),
                    new DefaultThreadFactory(nettyProperties.getWorkerPollName(),
                            nettyProperties.isDaemon()));
            channelClass = NioSocketChannel.class;
        }
        BusinessHandler businessHandler = new BusinessHandler(handshaker, monitorTask);
        final Bootstrap client = new Bootstrap();
        client
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .group(workGroup)
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(channelClass)
                .handler(new SocketChannelInitializer(
                        properties.getHost(),
                        properties.getPort(),
                        properties.getSslContext(),
                        businessHandler));
        channel = client.connect(properties.getHost(), properties.getPort()).sync().channel();
        if (isAlive()) {
            businessHandler.getChannelPromise().sync();
        }
        log.info("websocket client start success ...");
    }

    public boolean isAlive() {
        return this.channel != null && this.channel.isActive();
    }

    /**
     * 停止
     */
    public void stop() {
        log.info("websocket client stop ...");
        if (workGroup != null) {
            workGroup.shutdownGracefully();
            workGroup = null;
        }
        log.info("websocket client stop success ...");
    }

    /**
     * 重新刷新
     */
    public void refresh() {
        log.info("websocket client refresh ...");
        stop();
        start();
        subscribe();
        log.info("websocket client refresh success ...");
    }

    @SneakyThrows
    private static SocketServerProperties getHostPort(final URI uri) {
        String scheme = uri.getScheme() == null ? "http" : uri.getScheme();
        final String host = uri.getHost() == null ? "127.0.0.1" : uri.getHost();
        final int port;
        if (uri.getPort() == -1) {
            if ("http".equalsIgnoreCase(scheme) || "ws".equalsIgnoreCase(scheme)) {
                port =80;
            } else if ("wss".equalsIgnoreCase(scheme)) {
                port = 443;
            } else {
                port = -1;
            }
        } else {
            port = uri.getPort();
        }
        final boolean ssl = "wss".equalsIgnoreCase(scheme);
        final SslContext sslContext;
        if (ssl) {
            sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslContext = null;
        }
        return new SocketServerProperties().setHost(host).setPort(port).setSslContext(sslContext);
    }
}
