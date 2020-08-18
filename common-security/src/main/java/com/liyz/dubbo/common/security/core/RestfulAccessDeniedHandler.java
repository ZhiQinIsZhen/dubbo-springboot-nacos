package com.liyz.dubbo.common.security.core;

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
 * 注释:RestfulAccessDeniedHandler
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/18 11:21
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JsonMapperUtil.toJSONString(Result.error(CommonCodeEnum.NO_RIGHT)));
        response.getWriter().flush();
    }
}
