package com.liyz.dubbo.common.socket.strap;

import com.liyz.dubbo.common.socket.handler.*;
import com.liyz.dubbo.common.socket.service.user.UserService;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 注释:netty channel 初始化类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 14:45
 */
@Slf4j
public class SocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    private String socketPath;
    private long readerIdleTime;
    private long writerIdleTime;
    private long allIdleTime;
    private UserService userService;

    public SocketChannelInitializer(String socketPath, long readerIdleTime, long writerIdleTime, long allIdleTime, UserService userService) {
        this.socketPath = socketPath;
        this.readerIdleTime = readerIdleTime;
        this.writerIdleTime = writerIdleTime;
        this.allIdleTime = allIdleTime;
        this.userService = userService;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        log.info("initChannel .... {}", socketChannel);
        final ChannelPipeline pipeline = socketChannel.pipeline();
        // HttpServerCodec ,将请求和应答消息编码或者解码为HTTP消息
        pipeline.addLast("codec-http", new HttpServerCodec());
        // 将HTTP消息的多个部分组合成一条完整的HTTP消息;
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        // WebSocket升级
        pipeline.addLast("web-socket", new WebSocketServerProtocolHandler(socketPath, null, true));
        // 空闲检查, 60秒就关闭连接
        pipeline.addLast("idle", new IdleStateHandler(readerIdleTime, writerIdleTime, allIdleTime, TimeUnit.SECONDS));
        // 心跳处理
        pipeline.addLast("heart", new HeartbeatHandler());
        // 编码转换器
        pipeline.addLast("decoder", new MsgPackDecodeHandler());
        pipeline.addLast("encoder", new MsgPackEncoderHandler());
        pipeline.addLast("response", new TextResponseHandler());
        // 业务逻辑处理
        pipeline.addLast("msg-push", new MessagePushHandler(userService));
    }
}
