package com.liyz.dubbo.service.websocket.core;

import com.liyz.dubbo.common.base.util.GZipUtil;
import com.liyz.dubbo.service.websocket.scheduler.MonitorTask;
import com.liyz.dubbo.service.websocket.storage.SessionStorage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import java.nio.charset.StandardCharsets;

@Slf4j
public class BusinessHandler extends SimpleChannelInboundHandler<Object> {

    private final WebSocketClientHandshaker handshaker;
    private MonitorTask monitorTask;
    @Getter
    private ChannelPromise channelPromise;

    public BusinessHandler(WebSocketClientHandshaker handshaker, MonitorTask monitorTask) {
        this.handshaker = handshaker;
        this.monitorTask = monitorTask;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        Channel channel = channelHandlerContext.channel();
        if (!this.handshaker.isHandshakeComplete()) {
            this.handshaker.finishHandshake(channel, (FullHttpResponse) msg);
            this.channelPromise.setSuccess();
            return;
        }
        WebSocketFrame frame = (WebSocketFrame) msg;
        if (frame instanceof BinaryWebSocketFrame) {
            //解压
            BinaryWebSocketFrame binaryFrame = (BinaryWebSocketFrame) frame;
            byte[] temp = new byte[binaryFrame.content().readableBytes()];
            binaryFrame.content().readBytes(temp);
            String content = new String(GZipUtil.decompress(temp), StandardCharsets.UTF_8);
            processMsg(content, channel);
        } else if (frame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
            processMsg(textFrame.text(), channel);
        } else if (frame instanceof PongWebSocketFrame) {
            PongWebSocketFrame pongFrame = (PongWebSocketFrame) frame;
            log.info("ping content : {}", pongFrame.toString());
        } else if (frame instanceof CloseWebSocketFrame) {
            log.info("close client");
            channel.close();
        }
    }

    private void processMsg(String content, Channel channel) {
        monitorTask.setUpdateTime(System.currentTimeMillis());
        log.info("content : {}", content);
        if (content.contains("ping")) {
            String msg = content.replace("ping", "pong");
            log.info("send msg : {}", msg);
            channel.writeAndFlush(new TextWebSocketFrame(msg));
            return;
        }
        if (content.contains("pong")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ping", System.currentTimeMillis());
            log.info("send msg : {}", jsonObject.toString());
            channel.writeAndFlush(new TextWebSocketFrame(jsonObject.toString()));
            return;
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        this.channelPromise = ctx.newPromise();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        handshaker.handshake(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String clientId = ctx.channel().id().asLongText();
        ctx.close();
        SessionStorage.remove(clientId);
        log.info("channelInactive" + "WebSocket 退出 Client disconnected! count{} ctx{}" ,SessionStorage.count(),ctx.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (!channelPromise.isDone()) {
            channelPromise.setFailure(cause);
        }
        ctx.close();
    }
}
