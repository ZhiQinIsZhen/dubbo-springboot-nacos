package com.liyz.dubbo.common.security.filter;

import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import com.liyz.dubbo.common.security.core.JwtAccessTokenConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
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
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader.key:Authorization}")
    private String tokenHeaderKey;
    @Value("${jwt.tokenHeader.head:Bearer }")
    private String tokenHeaderHead;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeaderKey = httpServletRequest.getHeader(this.tokenHeaderKey);
        if (StringUtils.isNotBlank(tokenHeaderKey)) {
            tokenHeaderKey = URLDecoder.decode(tokenHeaderKey, "UTF-8");
            if (tokenHeaderKey.startsWith(tokenHeaderHead)) {
                final String authToken = tokenHeaderKey.substring(tokenHeaderHead.length()).trim();
                final String username = jwtAccessTokenConverter.getUsernameFromToken(authToken);
                if (StringUtils.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    if (Objects.isNull(userDetails)) {
                        throw new RemoteServiceException(CommonCodeEnum.AuthorizationFail);
                    }
                    Device device = new LiteDeviceResolver().resolveDevice(httpServletRequest);
                    if (jwtAccessTokenConverter.validateToken(authToken, userDetails, device)) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        throw new RemoteServiceException(CommonCodeEnum.AuthorizationFail);
                    }
                }
            } else {
                throw new RemoteServiceException(CommonCodeEnum.FORBIDDEN);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
