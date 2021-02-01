package com.liyz.dubbo.service.websocket.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.ssl.SslContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 注释:netty channel 初始化类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 14:45
 */
@Slf4j
public class SocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final String host;
    private final int port;
    private SslContext sslContext;
    private SimpleChannelInboundHandler inboundHandler;

    public SocketChannelInitializer(String host, int port, SslContext sslContext, SimpleChannelInboundHandler inboundHandler) {
        this.host = host;
        this.port = port;
        this.sslContext = sslContext;
        this.inboundHandler = inboundHandler;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        if (sslContext != null) {
            pipeline.addLast(sslContext.newHandler(socketChannel.alloc(), host, port));
        }
        // HttpClientCodec ,将请求和应答消息编码或者解码为HTTP消息
        pipeline.addLast(new HttpClientCodec());
        // 将HTTP消息的多个部分组合成一条完整的HTTP消息;
        pipeline.addLast(new HttpObjectAggregator(8192));
        pipeline.addLast("response", new TextResponseHandler());
        // 业务逻辑处理
        pipeline.addLast(inboundHandler);
    }
}
