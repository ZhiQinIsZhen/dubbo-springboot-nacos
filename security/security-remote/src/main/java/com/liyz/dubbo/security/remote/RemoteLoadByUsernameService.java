package com.liyz.dubbo.security.remote;

import com.liyz.dubbo.security.remote.bo.AuthUserBO;

import java.util.Date;

/**
 * 注释:用户鉴权查询接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/22 16:26
 */
public interface RemoteLoadByUsernameService {

    /**
     * 登陆
     *
     * @param username
     * @return
     */
    AuthUserBO login(String username);

    /**
     * 通过username查询认证信息
     *
     * @param username
     * @return
     */
    AuthUserBO loadByUsername(String username);
}
