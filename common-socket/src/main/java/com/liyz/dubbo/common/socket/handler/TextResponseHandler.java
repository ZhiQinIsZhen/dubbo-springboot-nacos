package com.liyz.dubbo.common.socket.handler;

import com.liyz.dubbo.common.socket.msg.ResponseMsg;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * 注释:response handler
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 15:28
 */
@Slf4j
@ChannelHandler.Sharable
public class TextResponseHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ResponseMsg) {
            ResponseMsg response = (ResponseMsg) msg;
            log.info("msg is response :{}", response);
            final TextWebSocketFrame frame = new TextWebSocketFrame();
            frame.content().writeBytes(response.toString().getBytes());
            ctx.writeAndFlush(frame);
        }
        super.write(ctx, msg, promise);
    }
}
