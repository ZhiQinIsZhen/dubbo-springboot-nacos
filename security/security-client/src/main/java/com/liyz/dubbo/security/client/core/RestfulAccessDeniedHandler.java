package com.liyz.dubbo.security.client.core;

import com.google.common.base.Charsets;
import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注释:restful access handler
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 9:59
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding(String.valueOf(Charsets.UTF_8));
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().println(JsonMapperUtil.toJSONString(Result.error(CommonExceptionCodeEnum.NO_RIGHT)));
        response.getWriter().flush();
    }
}
