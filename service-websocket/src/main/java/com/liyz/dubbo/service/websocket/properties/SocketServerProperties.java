package com.liyz.dubbo.service.websocket.properties;

import io.netty.handler.ssl.SslContext;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/2/1 11:36
 */
@Getter
@Setter
@Accessors(chain = true)
public class SocketServerProperties implements Serializable {
    private static final long serialVersionUID = -7910833464735572334L;

    private String host;

    private int port;

    private SslContext sslContext;
}
