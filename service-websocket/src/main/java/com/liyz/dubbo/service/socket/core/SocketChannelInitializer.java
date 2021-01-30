package com.liyz.dubbo.service.socket.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
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

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        log.info("initChannel .... {}", socketChannel);
        final ChannelPipeline pipeline = socketChannel.pipeline();
        // HttpServerCodec ,将请求和应答消息编码或者解码为HTTP消息
        pipeline.addLast("codec-http", new HttpServerCodec());
        // 将HTTP消息的多个部分组合成一条完整的HTTP消息;
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));

        pipeline.addLast("response", new TextResponseHandler());
        // 业务逻辑处理
        pipeline.addLast("robotHandler", new RobotHandler());
    }
}
