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
     * 修改登陆时间
     *
     * @param userId
     * @param device
     * @param isLogin
     * @return
     */
    Date updateLoginTime(final Long userId, final Integer device, final Boolean isLogin);

    /**
     * 通过username查询认证信息
     *
     * @param username
     * @param device
     * @return
     */
    AuthUserBO loadByUsername(final String username, final Integer device);
}
