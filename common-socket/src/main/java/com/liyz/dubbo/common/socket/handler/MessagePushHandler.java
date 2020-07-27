package com.liyz.dubbo.common.socket.handler;

import com.liyz.dubbo.common.socket.msg.RequestMsg;
import com.liyz.dubbo.common.socket.service.oper.OperaService;
import com.liyz.dubbo.common.socket.service.user.UserService;
import com.liyz.dubbo.common.socket.session.SocketSession;
import com.liyz.dubbo.common.socket.storage.SessionStorage;
import com.liyz.dubbo.common.socket.util.OperaDataUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 注释:消息读取后根据类型处理
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 15:29
 */
@Slf4j
@ChannelHandler.Sharable
public class MessagePushHandler extends SimpleChannelInboundHandler<RequestMsg> {

    private final UserService userService;

    public MessagePushHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestMsg requestMsg) throws Exception {
        log.info("enter into channelRead0 ......");
        SocketSession socketSession = SessionStorage.getAndSet(new SocketSession(channelHandlerContext.channel(), null));
        if (Objects.isNull(requestMsg) || StringUtils.isBlank(requestMsg.getOp())) {
            SessionStorage.remove(socketSession.getSessionId());
            channelHandlerContext.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
            return;
        }
        OperaService operaService = OperaDataUtil.getService(requestMsg.getOp());
        if (Objects.isNull(operaService)) {
            SessionStorage.remove(socketSession.getSessionId());
            channelHandlerContext.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
            return;
        }
        operaService.process(requestMsg, socketSession);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String clientId = ctx.channel().id().asLongText();
        SessionStorage.remove(clientId);
        ctx.close();
        log.error("channel error ", cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String clientId = ctx.channel().id().asLongText();
        SessionStorage.remove(clientId);
        log.info("channelInactive remove clientId :{}", clientId);
        super.channelInactive(ctx);
    }
}
