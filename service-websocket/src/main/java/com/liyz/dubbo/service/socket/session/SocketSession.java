package com.liyz.dubbo.service.socket.session;

import io.netty.channel.Channel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 注释:socket session info
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 13:54
 */
@Getter
@Setter
@Builder
public class SocketSession implements SessionService {

    private final Channel channel;
    private String userId;

    public SocketSession(Channel channel, String userId) {
        this.channel = channel;
        this.userId = userId;
    }

    @Override
    public String getSessionId() {
        return channel.id().asLongText();
    }

    @Override
    public boolean isWriteAble() {
        return channel.isWritable();
    }

    @Override
    public void send(Object data) {
        if (isWriteAble()) {
            channel.writeAndFlush(data);
        }
    }
}
