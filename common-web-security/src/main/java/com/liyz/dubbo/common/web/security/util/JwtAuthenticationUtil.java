package com.liyz.dubbo.common.web.security.util;

import com.liyz.dubbo.common.base.result.Result;
import com.liyz.dubbo.common.base.util.JsonMapperUtil;
import com.liyz.dubbo.common.remote.bo.JwtUserBO;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import com.liyz.dubbo.common.web.security.core.JwtUserDetails;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/7 17:37
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtAuthenticationUtil {

    public static JwtUserDetails create(JwtUserBO user) {
        if (Objects.isNull(user)) {
            return null;
        }
        return new JwtUserDetails(
                user.getUserId(),
                user.getLoginName(),
                user.getLoginPwd(),
                user.getEmail(),
                //由于web没有角色这种概念，所以不用设置
                null,
                user.getWebTokenTime(),
                user.getAppTokenTime()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public static void authFail(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JsonMapperUtil.toJSONString(Result.error(CommonCodeEnum.AuthorizationFail)));
        httpServletResponse.addHeader("Session-Invalid","true");
    }
}
