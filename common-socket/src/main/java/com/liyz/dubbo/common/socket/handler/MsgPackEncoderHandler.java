package com.liyz.dubbo.common.socket.handler;

import com.liyz.dubbo.common.socket.msg.ResponseMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * 注释:消息返回体编码
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 15:26
 */
@ChannelHandler.Sharable
public class MsgPackEncoderHandler extends MessageToByteEncoder<ResponseMsg> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseMsg responseMsg, ByteBuf byteBuf) throws Exception {
        // 创建MessagePack对象
        MessagePack pack = new MessagePack();
        // 将对象编码为MessagePack格式的字节数组
        byteBuf.writeBytes(pack.write(responseMsg));
    }
}
