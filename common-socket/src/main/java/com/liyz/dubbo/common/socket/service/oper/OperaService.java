package com.liyz.dubbo.common.socket.service.oper;

import com.liyz.dubbo.common.socket.constant.OperaTypeService;
import com.liyz.dubbo.common.socket.msg.RequestMsg;
import com.liyz.dubbo.common.socket.session.SocketSession;

/**
 * 注释:oper接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 15:50
 */
public interface OperaService {

    OperaTypeService getOperaTypeService();

    void process(RequestMsg requestMsg, SocketSession socketSession);
}
