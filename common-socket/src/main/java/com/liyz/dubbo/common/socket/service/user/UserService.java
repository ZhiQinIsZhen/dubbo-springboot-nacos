package com.liyz.dubbo.common.socket.service.user;

import com.liyz.dubbo.common.socket.msg.AuthMsg;

/**
 * 注释:用户相关接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 14:26
 */
public interface UserService {

    String getUserId(AuthMsg authMsg);
}
