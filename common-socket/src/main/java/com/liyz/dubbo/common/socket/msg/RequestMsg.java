package com.liyz.dubbo.common.socket.msg;

import com.google.common.base.Splitter;
import lombok.Getter;
import lombok.Setter;
import org.msgpack.annotation.Index;

import java.io.Serializable;
import java.util.Map;

/**
 * 注释:消息体
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 15:02
 */
@Getter
@Setter
public class RequestMsg implements Serializable {
    private static final long serialVersionUID = 7824464357337229231L;

    @Index(0)
    private String biz;

    @Index(1)
    private String op;

    @Index(2)
    private String args;

    public Map<String, String> argsMap() {
        return Splitter.on("&").withKeyValueSeparator("=").split(args);
    }
}
