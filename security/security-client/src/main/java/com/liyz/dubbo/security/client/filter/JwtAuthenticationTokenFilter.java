package com.liyz.dubbo.security.client.filter;

import com.google.common.base.Charsets;
import com.liyz.dubbo.common.core.auth.AuthUser;
import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.core.util.AuthContext;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import com.liyz.dubbo.security.client.context.AnonymousUrlContext;
import com.liyz.dubbo.security.client.context.JwtContextHolder;
import com.liyz.dubbo.security.client.impl.UserDetailsServiceImpl;
import com.liyz.dubbo.security.core.user.AuthUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * 注释:认证过滤器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 10:33
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final String tokenHeaderKey;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationTokenFilter(String tokenHeaderKey, UserDetailsService userDetailsService) {
        this.tokenHeaderKey = tokenHeaderKey;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //处理request head信息
            String token = request.getHeader(this.tokenHeaderKey);
            if (StringUtils.isNotBlank(token) && !AnonymousUrlContext.getAnonymousUrls().contains(request.getServletPath())) {
                token = URLDecoder.decode(token, String.valueOf(Charsets.UTF_8));
                final AuthUser authUser = JwtContextHolder.getJwtAuthCoreService().loadUserByToken(token);
                if (Objects.nonNull(authUser)) {
                    AuthContext.setAuthUser(authUser);
                    AuthUserDetails authUserDetails = UserDetailsServiceImpl.getByAuthUser(authUser);
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    authUserDetails,
                                    null,
                                    authUserDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (RemoteServiceException exception) {
            log.error("auth token fail, cause by ==> code : {}, msg : {}", exception.getCode(), exception.getMessage());
            response.setCharacterEncoding(String.valueOf(Charsets.UTF_8));
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().println(JsonMapperUtil.toJSONString(Result.error(exception.getCode(),
                    exception.getMessage())));
            response.getWriter().flush();
        } finally {
            AuthContext.remove();
        }
    }
}
