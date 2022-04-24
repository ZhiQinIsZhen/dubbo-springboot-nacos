package com.liyz.dubbo.security.client.core;

import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import org.springframework.http.MediaType;
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
 * @date 2022/4/24 10:29
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7414479405026928667L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JsonMapperUtil.toJSONString(Result.error(CommonExceptionCodeEnum.AUTHORIZATION_FAIL)));
    }
}
