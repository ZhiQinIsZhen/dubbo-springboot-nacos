package com.liyz.dubbo.service.socket.core;

import com.liyz.dubbo.service.socket.storage.SessionStorage;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketHandshakeException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class RobotHandler extends SimpleChannelInboundHandler<Object> {

    @Getter
    @Setter
    private WebSocketClientHandshaker handshaker;
    @Getter
    @Setter
    private ChannelPromise channelPromise;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        Channel channel = channelHandlerContext.channel();
        FullHttpResponse response;
        if (!this.handshaker.isHandshakeComplete()) {
            try {
                response = (FullHttpResponse) msg;
                this.handshaker.finishHandshake(channel, response);
                this.channelPromise.setSuccess();

            } catch (WebSocketHandshakeException e) {
                FullHttpResponse res = (FullHttpResponse) msg;
                this.channelPromise.setFailure(new Exception());
            }
        } else {
            WebSocketFrame frame = (WebSocketFrame) msg;
            if (frame instanceof TextWebSocketFrame) {
                processMessage(((TextWebSocketFrame) frame).text(), channel);
            }
        }
    }

    private void processMessage(String text,Channel channel) {
        log.info("handler processMessage" + text);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        this.channelPromise = ctx.newPromise();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive" + ctx);
        log.info("channelActive" + handshaker);
        handshaker.handshake(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String clientId = ctx.channel().id().asLongText();
        ctx.close();
        SessionStorage.remove(clientId);
        log.info("channelInactive" + "WebSocket 退出 Client disconnected! count{} ctx{}" ,SessionStorage.count(),ctx.toString());
    }
}
