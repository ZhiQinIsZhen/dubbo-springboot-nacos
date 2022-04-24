package com.liyz.dubbo.security.client.core;

import com.google.common.base.Charsets;
import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注释:认证失败handler
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 10:32
 */
public class RestfulAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding(String.valueOf(Charsets.UTF_8));
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().println(JsonMapperUtil.toJSONString(Result.error(CommonExceptionCodeEnum.LOGIN_FAIL)));
        response.getWriter().flush();
    }
}
