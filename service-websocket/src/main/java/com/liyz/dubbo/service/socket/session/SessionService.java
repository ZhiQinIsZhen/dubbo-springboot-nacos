package com.liyz.dubbo.service.socket.session;

import io.netty.channel.Channel;

/**
 * 注释:socket session接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 13:52
 */
public interface SessionService {

    /**
     * 获取socket session id
     *
     * @return
     */
    String getSessionId();

    /**
     * 获取对应session的userId
     *
     * @return
     */
    String getUserId();

    /**
     * 是否可写
     *
     * @return
     */
    boolean isWriteAble();

    /**
     * 发送消息
     *
     * @param data
     */
    void send(Object data);

    /**
     * 获取session channel
     *
     * @return
     */
    Channel getChannel();
}
