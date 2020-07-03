package com.liyz.dubbo.common.web.security.core.filter;

import com.liyz.dubbo.common.web.security.util.JwtAuthenticationUtil;
import com.liyz.dubbo.common.web.security.util.JwtTokenAnalysisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 * @date 2019/9/7 17:19
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.header.key:Authorization}")
    private String tokenHeaderKey;
    @Value("${jwt.tokenHeader.head:Bearer }")
    private String tokenHeaderHead;

    @Autowired
    JwtTokenAnalysisUtil jwtTokenAnalysisUtil;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader(this.tokenHeaderKey);
        if (StringUtils.isNotBlank(authHeader)) {
            authHeader = URLDecoder.decode(authHeader, "UTF-8");
            if (authHeader.startsWith(tokenHeaderHead)) {
                final String authToken = authHeader.substring(tokenHeaderHead.length());
                String username = jwtTokenAnalysisUtil.getUsernameFromToken(authToken);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    if (Objects.isNull(userDetails)) {
                        JwtAuthenticationUtil.authFail(httpServletResponse);
                        return;
                    }
                    Device device = new LiteDeviceResolver().resolveDevice(httpServletRequest);
                    if (jwtTokenAnalysisUtil.validateToken(authToken, userDetails, device)) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        logger.info("authenticated user " + username + ", setting security context");
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } else {
                    JwtAuthenticationUtil.authFail(httpServletResponse);
                    return;
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
