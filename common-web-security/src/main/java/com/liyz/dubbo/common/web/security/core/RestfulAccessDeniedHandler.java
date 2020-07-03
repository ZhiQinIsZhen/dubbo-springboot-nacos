package com.liyz.dubbo.common.web.security.core;

import com.liyz.dubbo.common.base.result.Result;
import com.liyz.dubbo.common.base.util.JsonMapperUtil;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义返回结果：没有权限访问时
 * Created by macro on 2018/4/26.
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JsonMapperUtil.toJSONString(Result.error(CommonCodeEnum.FORBIDDEN)));
        response.getWriter().flush();
    }
}
