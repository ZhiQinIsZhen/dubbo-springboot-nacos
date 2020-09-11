package com.liyz.dubbo.common.security.filter;

import com.liyz.dubbo.common.base.service.LoginInfoService;
import com.liyz.dubbo.common.security.constant.SecurityConstant;
import com.liyz.dubbo.common.security.core.JwtAccessTokenConverter;
import com.liyz.dubbo.common.security.util.AuthenticationResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Objects;

/**
 * 注释:登陆鉴权 filter
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/18 10:01
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final String tokenHeaderKey;
    private final String tokenHeaderHead;
    private final UserDetailsService userDetailsService;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final LoginInfoService loginInfoService;

    public JwtAuthenticationTokenFilter(String tokenHeaderKey, String tokenHeaderHead, UserDetailsService userDetailsService,
                                        JwtAccessTokenConverter jwtAccessTokenConverter, LoginInfoService loginInfoService) {
        this.tokenHeaderKey = tokenHeaderKey;
        this.tokenHeaderHead = tokenHeaderHead;
        this.userDetailsService = userDetailsService;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.loginInfoService = loginInfoService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String tokenHeaderKey = httpServletRequest.getHeader(this.tokenHeaderKey);
            if (StringUtils.isNotBlank(tokenHeaderKey)) {
                tokenHeaderKey = URLDecoder.decode(tokenHeaderKey, "UTF-8");
                if (tokenHeaderKey.startsWith(tokenHeaderHead)) {
                    final String authToken = tokenHeaderKey.substring(tokenHeaderHead.length()).trim();
                    final String username = jwtAccessTokenConverter.getUsernameFromToken(authToken);
                    if (StringUtils.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                        if (Objects.isNull(userDetails)) {
                            AuthenticationResponseUtil.authFail(httpServletResponse);
                            return;
                        }
                        Device device = new LiteDeviceResolver().resolveDevice(httpServletRequest);
                        Integer validate = jwtAccessTokenConverter.validateToken(authToken, userDetails, device);
                        if (validate == SecurityConstant.VALIDATE_TOKEN_SUCCESS) {
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        } else if (validate == SecurityConstant.VALIDATE_TOKEN_FAIL_EXPIRED) {
                            AuthenticationResponseUtil.authExpired(httpServletResponse);
                            return;
                        } else if (validate == SecurityConstant.VALIDATE_TOKEN_FAIL_OTHER_LOGIN) {
                            AuthenticationResponseUtil.authOthersLogin(httpServletResponse);
                            return;
                        }
                    } else {
                        AuthenticationResponseUtil.authFail(httpServletResponse);
                        return;
                    }
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            loginInfoService.remove();
        }
    }
}
