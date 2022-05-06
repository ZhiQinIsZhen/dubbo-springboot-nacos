package com.liyz.dubbo.security.core.remote;

import com.liyz.dubbo.common.core.auth.AuthUser;
import com.liyz.dubbo.security.core.constant.SecurityEnum;
import com.liyz.dubbo.security.core.user.ClaimDetail;

import java.util.Date;

/**
 * 注释:jwt认证接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 9:39
 */
public interface RemoteJwtAuthCoreService {

    /**
     * 登陆
     *
     * @param userId
     * @param device
     * @param audienceType
     * @return
     */
    Date login(final Long userId, final Integer device, final SecurityEnum.AudienceType audienceType);

    /**
     * 登出
     *
     * @param userId
     * @param device
     * @param audienceType
     * @return
     */
    Date logout(final Long userId, final Integer device, final SecurityEnum.AudienceType audienceType);

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @param device
     * @param audienceType
     * @return
     */
    AuthUser loadUserByUsername(final String username, final Integer device, final SecurityEnum.AudienceType audienceType);

    /**
     * 根据token解析认证用户信息
     *
     * @param token
     * @return
     */
    AuthUser loadUserByToken(final String token);

    /**
     * 根据claim信息生成token
     *
     * @param claimDetail
     * @return
     */
    String getJWT(final ClaimDetail claimDetail);

    /**
     * 获取token中的用户名
     *
     * @param token
     * @return
     */
    String getUsernameByToken(final String token);

    /**
     * 获取token失效时间
     *
     * @param token
     * @return
     */
    Long getExpirationByToken(final String token);

    /**
     * 校验token是否失效
     *
     * @param token
     * @param authUser
     * @param device
     * @return
     */
    void validateToken(final String token, final AuthUser authUser, final Integer device);
}
