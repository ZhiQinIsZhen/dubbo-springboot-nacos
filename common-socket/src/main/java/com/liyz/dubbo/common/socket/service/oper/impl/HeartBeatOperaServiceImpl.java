package com.liyz.dubbo.common.socket.service.oper.impl;

import com.liyz.dubbo.common.socket.constant.OperaTypeService;
import com.liyz.dubbo.common.socket.msg.DataMsg;
import com.liyz.dubbo.common.socket.msg.RequestMsg;
import com.liyz.dubbo.common.socket.msg.ResponseMsg;
import com.liyz.dubbo.common.socket.service.oper.OperaService;
import com.liyz.dubbo.common.socket.session.SocketSession;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 注释:心跳操作
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 16:01
 */
@Slf4j
@Service
public class HeartBeatOperaServiceImpl implements OperaService, OperaTypeService {

    @Override
    public String getOperaType() {
        return "heartbeat";
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public OperaTypeService getOperaTypeService() {
        return this;
    }

    @Override
    public void process(RequestMsg requestMsg, SocketSession socketSession) {
        DataMsg data = DataMsg.builder().op(requestMsg.getOp()).build();
        socketSession.send(ResponseMsg.ok("socket pong", data));
    }
}
