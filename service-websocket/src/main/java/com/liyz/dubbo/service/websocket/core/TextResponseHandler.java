package com.liyz.dubbo.service.websocket.core;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * 编码工具
 */
@Slf4j
@Sharable
public class TextResponseHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            final TextWebSocketFrame textWF = new TextWebSocketFrame();
            textWF.content().writeBytes(msg.toString().getBytes());
            ctx.writeAndFlush(textWF);
        super.write(ctx, msg, promise);
    }

}
