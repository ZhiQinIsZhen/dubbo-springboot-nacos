package com.liyz.dubbo.security.client.context;

import com.liyz.dubbo.common.api.util.CookieUtil;
import com.liyz.dubbo.common.api.util.HttpServletContext;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.common.util.PatternUtil;
import com.liyz.dubbo.security.client.constant.SecurityClientConstant;
import com.liyz.dubbo.security.client.user.AuthUserDetails;
import com.liyz.dubbo.service.auth.bo.AuthUserBO;
import com.liyz.dubbo.service.auth.bo.AuthUserLoginBO;
import com.liyz.dubbo.service.auth.bo.AuthUserLogoutBO;
import com.liyz.dubbo.service.auth.bo.AuthUserRegisterBO;
import com.liyz.dubbo.service.auth.enums.LoginType;
import com.liyz.dubbo.service.auth.exception.AuthExceptionCodeEnum;
import com.liyz.dubbo.service.auth.exception.RemoteAuthServiceException;
import com.liyz.dubbo.service.auth.remote.RemoteAuthService;
import com.liyz.dubbo.service.auth.remote.RemoteJwtParseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/13 20:18
 */
public class AuthContext implements EnvironmentAware, ApplicationContextAware, InitializingBean {

    private static String clientId;
    private static ApplicationContext applicationContext;
    private static AuthenticationManager authenticationManager;
    private static RemoteJwtParseService remoteJwtParseService;
    private static RemoteAuthService remoteAuthService;

    private static final InheritableThreadLocal<AuthUserBO> innerContext = new InheritableThreadLocal<>();

    /**
     * 获取认证用户
     *
     * @return 认证用户
     */
    public static AuthUserBO getAuthUser() {
        return innerContext.get();
    }

    /**
     * 设置认证用户
     *
     * @param authUser 认证用户
     */
    public static void setAuthUser(AuthUserBO authUser) {
        innerContext.set(authUser);
    }

    /**
     * 移除认证用户
     */
    public static void remove() {
        innerContext.remove();
    }

    @Override
    public void setEnvironment(Environment environment) {
        clientId = environment.getProperty(SecurityClientConstant.DUBBO_APPLICATION_NAME_PROPERTY);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        remoteAuthService = applicationContext.getBean(SecurityClientConstant.AUTH_SERVICE_BEAN_NAME, RemoteAuthService.class);
        remoteJwtParseService = applicationContext.getBean(SecurityClientConstant.JWT_SERVICE_BEAN_NAME, RemoteJwtParseService.class);
        authenticationManager = applicationContext.getBean(SecurityClientConstant.AUTH_MANAGER_BEAN_NAME, AuthenticationManager.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AuthContext.applicationContext = applicationContext;
    }

    /**
     * 认证服务
     */
    public static class AuthService {

        /**
         * 用户注册
         *
         * @param authUserRegisterBO 用户注册参数
         * @return 是否注册成功
         */
        public static Boolean registry(AuthUserRegisterBO authUserRegisterBO) {
            authUserRegisterBO.setClientId(clientId);
            return remoteAuthService.registry(authUserRegisterBO);
        }

        /**
         * 登录
         *
         * @param authUserLoginBO 登录参数
         * @param redirect 重定向路劲
         * @return 登录用户信息
         */
        public static AuthUserBO login(AuthUserLoginBO authUserLoginBO, final String redirect) throws IOException {
            String jwtPrefix = remoteJwtParseService.jwtPrefix(clientId);
            if (jwtPrefix == null) {
                throw new RemoteAuthServiceException(AuthExceptionCodeEnum.LOGIN_ERROR_SOURCE_ID);
            }
            authUserLoginBO.setClientId(clientId);
            authUserLoginBO.setDevice(DeviceContext.getDevice(HttpServletContext.getRequest()));
            authUserLoginBO.setLoginType(LoginType.getByType(PatternUtil.checkMobileEmail(authUserLoginBO.getUsername())));
            authUserLoginBO.setIp(HttpServletContext.getIpAddress(HttpServletContext.getRequest()));
            Authentication authentication = new UsernamePasswordAuthenticationToken(authUserLoginBO.getUsername(), authUserLoginBO.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authentication));
            AuthUserDetails authUserDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AuthUserBO authUserBO = BeanUtil.copyProperties(authUserDetails.getAuthUser(), AuthUserBO.class, (s, t) -> {
                t.setPassword(null);
                t.setSalt(null);
                t.setLoginKey(null);
                t.setToken(JwtService.generateToken(s));
            });
            if (StringUtils.isNotBlank(redirect)) {
                HttpServletResponse response = HttpServletContext.getResponse();
                response.setHeader(SecurityClientConstant.DEFAULT_TOKEN_HEADER_KEY, authUserBO.getToken());
                response.sendRedirect(redirect);
            }
            CookieUtil.addCookie(
                    SecurityClientConstant.DEFAULT_TOKEN_HEADER_KEY,
                    jwtPrefix + authUserBO.getToken(),
                    30 * 60,
                    null
            );
            return authUserBO;
        }

        /**
         * 登出
         *
         * @return 是否登出成功
         */
        public static Boolean logout() {
            AuthUserBO authUser = getAuthUser();
            if (Objects.isNull(authUser)) {
                return Boolean.FALSE;
            }
            AuthUserLogoutBO authUserLogoutBO = BeanUtil.copyProperties(authUser, AuthUserLogoutBO.class, (s, t) -> {
                t.setLogoutType(s.getLoginType());
                t.setDevice(s.getDevice());
                t.setIp(HttpServletContext.getIpAddress());
            });
            CookieUtil.removeCookie(SecurityClientConstant.DEFAULT_TOKEN_HEADER_KEY);
            return remoteAuthService.logout(authUserLogoutBO);
        }
    }

    /**
     * JWT服务
     */
    public static class JwtService {

        /**
         * 解析token
         *
         * @param token jwt
         * @return 用户信息
         */
        public static AuthUserBO parseToken(final String token) {
            return remoteJwtParseService.parseToken(token, clientId);
        }

        /**
         * 创建token
         *
         * @param authUser 认证用户信息
         * @return jwt
         */
        public static String generateToken(final AuthUserBO authUser) {
            authUser.setClientId(clientId);
            return remoteJwtParseService.generateToken(authUser);
        }

        /**
         * 获取失效时间
         *
         * @param token jwt
         * @return 失效时间戳
         */
        public static Long getExpiration(final String token) {
            return remoteJwtParseService.getExpiration(token);
        }
    }
}
