package com.liyz.dubbo.security.core.remote;

import com.liyz.dubbo.common.core.auth.AuthUser;
import com.liyz.dubbo.security.core.constant.SecurityEnum;
import com.liyz.dubbo.security.core.user.ClaimDetail;
import org.springframework.security.core.userdetails.UserDetails;

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
     * @param username
     * @param audienceType
     * @return
     */
    AuthUser login(final String username, final SecurityEnum.AudienceType audienceType);

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @param audienceType
     * @return
     */
    AuthUser loadUserByUsername(final String username, final SecurityEnum.AudienceType audienceType);

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
     * 校验token是否失效
     *
     * @param token
     * @param userDetails
     * @param device
     * @return
     */
    Integer validateToken(final String token, final UserDetails userDetails, final Integer device);
}
