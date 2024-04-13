package com.liyz.dubbo.api.admin.controller.authenticate;

import com.liyz.dubbo.api.admin.dto.auth.StaffLoginDTO;
import com.liyz.dubbo.api.admin.dto.auth.StaffRegisterDTO;
import com.liyz.dubbo.api.admin.vo.auth.AuthLoginVO;
import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.common.api.util.HttpServletContext;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.security.client.annotation.Anonymous;
import com.liyz.dubbo.security.client.constant.SecurityClientConstant;
import com.liyz.dubbo.security.client.context.AuthContext;
import com.liyz.dubbo.service.auth.bo.AuthUserBO;
import com.liyz.dubbo.service.auth.bo.AuthUserLoginBO;
import com.liyz.dubbo.service.auth.bo.AuthUserRegisterBO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/15 16:15
 */
@Api(tags = "员工鉴权")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Anonymous
    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<Boolean> register(@Validated({StaffRegisterDTO.Register.class}) @RequestBody StaffRegisterDTO staffRegister) {
        return Result.success(AuthContext.AuthService.registry(BeanUtil.copyProperties(staffRegister, AuthUserRegisterBO.class)));
    }

    @Anonymous
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<AuthLoginVO> login(@Validated({StaffLoginDTO.Login.class}) @RequestBody StaffLoginDTO loginDTO) throws IOException {
        AuthUserBO authUserBO = AuthContext.AuthService.login(BeanUtil.copyProperties(loginDTO, AuthUserLoginBO.class));
        AuthLoginVO authLoginVO = new AuthLoginVO();
        authLoginVO.setToken(authUserBO.getToken());
        authLoginVO.setExpiration(AuthContext.JwtService.getExpiration(authUserBO.getToken()));
        if (StringUtils.isNotBlank(loginDTO.getRedirect())) {
            HttpServletResponse response = HttpServletContext.getResponse();
            response.setHeader(SecurityClientConstant.DEFAULT_TOKEN_HEADER_KEY, authLoginVO.getToken());
            response.sendRedirect(loginDTO.getRedirect());
        }
        return Result.success(authLoginVO);
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    public Result<Boolean> logout() {
        return Result.success(AuthContext.AuthService.logout());
    }
}
