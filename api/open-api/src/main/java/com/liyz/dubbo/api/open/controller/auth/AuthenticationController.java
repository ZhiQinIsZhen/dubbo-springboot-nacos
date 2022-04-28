package com.liyz.dubbo.api.open.controller.auth;

import com.liyz.dubbo.api.open.dto.auth.LoginDTO;
import com.liyz.dubbo.api.open.dto.auth.UserRegisterDTO;
import com.liyz.dubbo.api.open.vo.auth.LoginVO;
import com.liyz.dubbo.common.core.auth.AuthUser;
import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.core.util.AuthContext;
import com.liyz.dubbo.common.core.util.CommonCloneUtil;
import com.liyz.dubbo.common.core.util.HttpRequestUtil;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.security.client.context.JwtContextHolder;
import com.liyz.dubbo.security.core.annotation.Anonymous;
import com.liyz.dubbo.security.core.constant.SecurityEnum;
import com.liyz.dubbo.security.core.user.AuthUserDetails;
import com.liyz.dubbo.service.staff.bo.UserRegisterBO;
import com.liyz.dubbo.service.staff.remote.RemoteCustomerService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/4/14 9:28
 */
@Api(tags = "用户鉴权")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Resource
    private AuthenticationManager authenticationManager;
    @DubboReference
    private RemoteCustomerService remoteCustomerService;

    @Limits(value = {@Limit(count = 10, type = LimitType.IP), @Limit(count = 1, type = LimitType.TOTAL), @Limit(count = 1)})
    @Anonymous
    @ApiOperation("登陆")
    @PostMapping("/login")
    public Result<LoginVO> login(@Validated({LoginDTO.Login.class}) @RequestBody LoginDTO loginDTO) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getLoginName(), loginDTO.getLoginPwd());
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authentication));
        AuthUserDetails authUserDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthUser authUser = JwtContextHolder.getJwtAuthCoreService().login(
                authUserDetails.getLoginName(),
                SecurityEnum.AudienceType.getByCode(authUserDetails.getGroup()));
        LoginVO loginVO = LoginVO.builder()
                .userId(authUserDetails.getId())
                .loginName(authUserDetails.getLoginName())
                .userName(authUserDetails.getUsername())
                .nickName(authUserDetails.getNikeName())
                .email(authUserDetails.getEmail())
                .mobile(authUserDetails.getMobile())
                .roleIds(authUserDetails.getRoleIds())
                .token(JwtContextHolder.getJWT(authUser.getWebTokenTime(), SecurityEnum.AudienceType.getByCode(authUserDetails.getGroup())))
                .build();
        loginVO.setExpirationDate(JwtContextHolder.getJwtAuthCoreService().getExpirationByToken(loginVO.getToken()));
        return Result.success(loginVO);
    }

    @Anonymous
    @ApiOperation("登出")
    @PostMapping("/logout")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    public Result<Boolean> logout() {
        AuthUser authUser = AuthContext.getAuthUser();
        if (Objects.isNull(authUser)) {
            return Result.success(Boolean.FALSE);
        }
        SecurityEnum.AudienceType audienceType = SecurityEnum.AudienceType.getByCode(authUser.getGroup());
        JwtContextHolder.getJwtAuthCoreService().logout(authUser.getLoginName(), audienceType);
        return Result.success(Boolean.TRUE);
    }

    @Limits(value = {@Limit(count = 10, type = LimitType.IP), @Limit(count = 10)})
    @Anonymous
    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<Boolean> register(@Validated({UserRegisterDTO.Register.class}) @RequestBody UserRegisterDTO userRegisterDTO) {
        HttpServletRequest request = HttpRequestUtil.getRequest();
        LiteDeviceResolver resolver = new LiteDeviceResolver();
        String ip = HttpRequestUtil.getIpAddress(request);
        //如果需要统计注册设备，这里可以加进去
        Device device = resolver.resolveDevice(request);
        log.info("user register，ip:{}, isMobile:{}", ip, device.isMobile());
        UserRegisterBO bo = CommonCloneUtil.objectClone(userRegisterDTO, UserRegisterBO.class);
        bo.setLoginPwd(JwtContextHolder.getPasswordEncoder().encode(userRegisterDTO.getLoginPwd()));
        bo.setDevice(device.isMobile() ? 1: 2);
        bo.setIp(ip);
        remoteCustomerService.register(bo);
        return Result.success(Boolean.TRUE);
    }
}
