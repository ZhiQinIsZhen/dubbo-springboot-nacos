package com.liyz.dubbo.security.server.provider;

import com.liyz.dubbo.common.core.auth.AuthUser;
import com.liyz.dubbo.common.core.util.CommonCloneUtil;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.security.core.constant.SecurityConstant;
import com.liyz.dubbo.security.core.constant.SecurityEnum;
import com.liyz.dubbo.security.core.remote.RemoteGrantedAuthorityCoreService;
import com.liyz.dubbo.security.core.remote.RemoteJwtAuthCoreService;
import com.liyz.dubbo.security.core.user.ClaimDetail;
import com.liyz.dubbo.security.remote.RemoteLoadByUsernameService;
import com.liyz.dubbo.security.remote.bo.AuthUserBO;
import com.liyz.dubbo.security.server.parse.JwtAccessTokenParser;
import com.liyz.dubbo.security.server.util.DubboGenericServiceUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 注释:jwt认证核心实现类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 14:15
 */
@DubboService
public class RemoteJwtAuthCoreServiceImpl implements RemoteJwtAuthCoreService {

    @Value("${jwt.tokenHeader.head:Bearer }")
    private String tokenHeaderHead;

    @Resource
    private RemoteGrantedAuthorityCoreService remoteGrantedAuthorityCoreService;

    @Autowired
    private JwtAccessTokenParser jwtAccessTokenParser;

    /**
     * 登录
     *
     * @param username
     * @param audienceType
     * @return
     */
    @Override
    public AuthUser login(String username, SecurityEnum.AudienceType audienceType) {
        AuthUserBO authUserBO = null;
        if (StringUtils.isNotBlank(username) && Objects.nonNull(audienceType)) {
            authUserBO = getAuthUserByGenericService("login", username, audienceType.getCode());
        }
        return CommonCloneUtil.objectClone(authUserBO, AuthUser.class);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @param audienceType
     * @return
     */
    @Override
    public AuthUser loadUserByUsername(String username, SecurityEnum.AudienceType audienceType) {
        AuthUserBO authUserBO = null;
        if (StringUtils.isNotBlank(username) && Objects.nonNull(audienceType)) {
            authUserBO = getAuthUserByGenericService("loadByUsername", username, audienceType.getCode());
        }
        AuthUser authUser = CommonCloneUtil.objectClone(authUserBO, AuthUser.class);
        if (Objects.nonNull(authUserBO) && !CollectionUtils.isEmpty(authUserBO.getRoleIds())) {
            authUser.setAuthorityList(remoteGrantedAuthorityCoreService.getByRoleIds(authUserBO.getRoleIds(), audienceType.getCode()));
        }
        return authUser;
    }

    /**
     * 根据token信息获取对应的认证信息
     *
     * @param token
     * @return
     */
    @Override
    public AuthUser loadUserByToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            if (token.startsWith(tokenHeaderHead)) {
                final String authToken = token.substring(tokenHeaderHead.length()).trim();
                if (jwtAccessTokenParser.isTokenExpired(authToken)) {
                    throw new RemoteServiceException(CommonExceptionCodeEnum.AUTHORIZATION_TIMEOUT);
                }
                ClaimDetail claimDetail = jwtAccessTokenParser.getDetailByToken(authToken);
                AuthUser authUser = loadUserByUsername(
                        claimDetail.getUsername(),
                        SecurityEnum.AudienceType.getByCode(claimDetail.getAudience())
                );
                if (jwtAccessTokenParser.getCreationByToken(authToken).compareTo(authUser.getWebTokenTime()) != 0) {
                    throw new RemoteServiceException(CommonExceptionCodeEnum.OTHERS_LOGIN);
                }
                return authUser;
            }
        }
        return null;
    }

    /**
     * 生成token信息
     *
     * @param claimDetail
     * @return
     */
    @Override
    public String getJWT(ClaimDetail claimDetail) {
        return jwtAccessTokenParser.generateToken(claimDetail);
    }

    /**
     * 获取token失效时间
     *
     * @param token
     * @return
     */
    @Override
    public Long getExpirationByToken(String token) {
        Date expiration = jwtAccessTokenParser.getExpirationByToken(token);
        return Objects.isNull(expiration) ? null : expiration.getTime();
    }

    /**
     * 校验token有效性
     *
     * @param token
     * @param userDetails
     * @param device
     * @return
     */
    @Override
    public Integer validateToken(String token, UserDetails userDetails, Integer device) {
        Boolean isExpired = jwtAccessTokenParser.isTokenExpired(token);
        if (isExpired) {
            return 1;
        }
        if (Objects.nonNull(userDetails) && StringUtils.isNotBlank(userDetails.getUsername())) {
            if (!userDetails.getUsername().equals(jwtAccessTokenParser.getUsernameByToken(token))) {
                return 2;
            }
        }
        if (Objects.nonNull(device)) {
            if (!device.equals(jwtAccessTokenParser.getDeviceByToken(token))) {
                return 3;
            }
        }
        return 0;
    }

    /**
     * 通过 genericService 获取用户信息
     *
     * @param methodName
     * @param o
     * @param group
     * @return
     */
    private AuthUserBO getAuthUserByGenericService(String methodName, Object o, String group) {
        String[] parameterTypes = new String[] {String.class.getName()};
        Map<String, Object> map = (Map<String, Object>) DubboGenericServiceUtil
                .getByClassName(RemoteLoadByUsernameService.class, SecurityConstant.DEFAULT_VERSION, group)
                .$invoke(methodName, parameterTypes, new Object[] {o});
        AuthUserBO authUserBO = null;
        if (!CollectionUtils.isEmpty(map)) {
            try {
                authUserBO = CommonCloneUtil.MapToBean(map, AuthUserBO.class);
            } catch (Exception e) {
                throw new RemoteServiceException(CommonExceptionCodeEnum.GRANTED_AUTHORITY_TRANS);
            }
        }
        return authUserBO;
    }
}
