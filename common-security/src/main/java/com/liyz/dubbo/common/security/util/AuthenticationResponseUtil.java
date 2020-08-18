package com.liyz.dubbo.common.security.util;

import com.liyz.dubbo.common.base.result.Result;
import com.liyz.dubbo.common.base.util.JsonMapperUtil;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注释:认证返回工具类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/18 11:36
 */
public class AuthenticationResponseUtil {

    public static void authFail(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JsonMapperUtil.toJSONString(Result.error(CommonCodeEnum.AuthorizationFail)));
        httpServletResponse.addHeader("Session-Invalid","true");
    }

    public static void authForbidden(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JsonMapperUtil.toJSONString(Result.error(CommonCodeEnum.FORBIDDEN)));
        httpServletResponse.addHeader("Session-Invalid","true");
    }

    public static void authNoRight(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JsonMapperUtil.toJSONString(Result.error(CommonCodeEnum.NO_RIGHT)));
        httpServletResponse.addHeader("Session-Invalid","true");
    }
}
