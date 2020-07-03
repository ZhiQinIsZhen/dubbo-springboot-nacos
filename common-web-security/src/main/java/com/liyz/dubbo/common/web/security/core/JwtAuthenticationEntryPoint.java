package com.liyz.dubbo.common.web.security.core;

import com.liyz.dubbo.common.web.security.util.JwtAuthenticationUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 注释:认证入口点
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/7 17:35
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = 8446825957432945858L;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        JwtAuthenticationUtil.authFail(httpServletResponse);
    }
}
