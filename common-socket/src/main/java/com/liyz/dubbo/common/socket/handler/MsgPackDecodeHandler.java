package com.liyz.dubbo.common.socket.handler;

import com.liyz.dubbo.common.socket.msg.RequestMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * 注释:消息体解码
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 15:25
 */
@ChannelHandler.Sharable
public class MsgPackDecodeHandler extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        final int length = byteBuf.readableBytes();
        final byte[] array = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), array, 0, length);

        MessagePack pack = new MessagePack();
        RequestMsg req = pack.read(array, RequestMsg.class);
        list.add(req);
    }
}
