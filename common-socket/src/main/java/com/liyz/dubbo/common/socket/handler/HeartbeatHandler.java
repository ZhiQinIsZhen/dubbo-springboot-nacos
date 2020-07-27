package com.liyz.dubbo.common.socket.handler;

import com.liyz.dubbo.common.base.util.JsonMapperUtil;
import com.liyz.dubbo.common.socket.msg.RequestMsg;
import com.liyz.dubbo.common.socket.msg.ResponseMsg;
import com.liyz.dubbo.common.socket.storage.SessionStorage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.msgpack.MessagePack;
import org.springframework.util.Assert;

/**
 * 注释:心跳以及不同协议以及session消息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 14:55
 */
@Slf4j
@ChannelHandler.Sharable
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("socket pong", CharsetUtil.UTF_8));

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                ctx.writeAndFlush(HEARTBEAT_SEQUENCE).addListeners((ChannelFutureListener) future -> {
                    if (!future.isSuccess()) {
                        log.info("enter user event triggered");
                        future.channel().close();
                    }
                });
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof WebSocketFrame) {
            dealWebSocketMsg(ctx, (WebSocketFrame) msg);
            return;
        } else if (msg instanceof FullHttpRequest) {
            log.info("enter FullHttpRequest ...");
            FullHttpRequest request = (FullHttpRequest) msg;
        }
        super.channelRead(ctx, msg);
    }

    /**
     * 处理websocket消息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    private void dealWebSocketMsg(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        if (msg instanceof CloseWebSocketFrame) {
            String id = ctx.channel().id().asLongText();
            log.info("read -- > remove id : {}", id);
            SessionStorage.remove(id);
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
            return;
        }
        if (msg instanceof PingWebSocketFrame) {
            final PingWebSocketFrame frame = (PingWebSocketFrame) msg;
            try {
                log.info("read --> ping");
                ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
            } catch (Exception e) {
                log.error("error ", e);
            } finally {
                frame.release();
            }
            return;
        }
        if (msg instanceof PongWebSocketFrame) {
            final PongWebSocketFrame frame = (PongWebSocketFrame) msg;
            try {
                log.info("read --> pong");
                ctx.channel().writeAndFlush(new PingWebSocketFrame(frame.content().retain()));
            } catch (Exception e) {
                log.error("error ", e);
            } finally {
                frame.release();
            }
            return;
        }
        if (msg instanceof TextWebSocketFrame) {
            final TextWebSocketFrame frame = (TextWebSocketFrame) msg;
            String text = frame.text();
            try {
                log.info("read --> text:{}", text);
                RequestMsg request = JsonMapperUtil.readValue(text, RequestMsg.class);
                Assert.notNull(request.getOp(), "op must be not null");
                ctx.fireChannelRead(request);
            } catch (IllegalArgumentException e) {
                ctx.writeAndFlush(ResponseMsg.error(e.getMessage()));
            } finally {
                frame.release();
            }
            return;
        }
        if (msg instanceof BinaryWebSocketFrame) {
            log.info("inter BinaryWebSocketFrame ... ");
            final BinaryWebSocketFrame frame = (BinaryWebSocketFrame) msg;
            ByteBuf content = frame.content();
            final int length = content.readableBytes();
            final byte[] array = new byte[length];
            content.getBytes(content.readerIndex(), array, 0, length);
            MessagePack messagePack = new MessagePack();
            RequestMsg req = messagePack.read(array, RequestMsg.class);
            Assert.notNull(req.getOp(), "op must be not null");
            ctx.fireChannelRead(req);
            return;
        }
        if (msg instanceof ContinuationWebSocketFrame) {
            final ContinuationWebSocketFrame frame = (ContinuationWebSocketFrame) msg;
            return;
        }
    }
}
