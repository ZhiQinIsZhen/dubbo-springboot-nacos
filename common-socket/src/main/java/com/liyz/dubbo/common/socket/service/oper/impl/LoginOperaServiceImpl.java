package com.liyz.dubbo.common.socket.service.oper.impl;

import com.liyz.dubbo.common.socket.constant.OperaTypeService;
import com.liyz.dubbo.common.socket.msg.AuthMsg;
import com.liyz.dubbo.common.socket.msg.DataMsg;
import com.liyz.dubbo.common.socket.msg.RequestMsg;
import com.liyz.dubbo.common.socket.msg.ResponseMsg;
import com.liyz.dubbo.common.socket.service.oper.OperaService;
import com.liyz.dubbo.common.socket.service.user.UserService;
import com.liyz.dubbo.common.socket.session.SocketSession;
import com.liyz.dubbo.common.socket.storage.OnlineUserStorage;
import com.liyz.dubbo.common.socket.storage.SessionStorage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注释:登陆操作
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 16:01
 */
@Slf4j
@Service
public class LoginOperaServiceImpl implements OperaService, OperaTypeService {

    @Autowired
    UserService userService;

    @Override
    public String getOperaType() {
        return "login";
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
        AuthMsg authMsg = new AuthMsg();
        authMsg.setAuth(requestMsg.argsMap().get("token"));
        String userId = userService.getUserId(authMsg);
        DataMsg message = DataMsg.builder().op(requestMsg.getOp()).build();
        if (StringUtils.isNotBlank(userId)) {
            log.info("user login success, uid -> {}", userId);
            // 缓存用户信息
            OnlineUserStorage.add(userId, socketSession.getSessionId());
            // 重置session
            socketSession.setUserId(userId);
            SessionStorage.add(socketSession);
            // 返回信息
            socketSession.send(ResponseMsg.ok("login success!", message));
            return;
        }
        socketSession.send(ResponseMsg.error("login failure!", message));
    }
}
