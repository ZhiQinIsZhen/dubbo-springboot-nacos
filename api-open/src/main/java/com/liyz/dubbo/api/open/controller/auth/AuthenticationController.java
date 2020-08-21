package com.liyz.dubbo.api.open.controller.auth;

import com.liyz.dubbo.api.open.dto.auth.LoginDTO;
import com.liyz.dubbo.api.open.vo.auth.LoginVO;
import com.liyz.dubbo.common.base.log.annotation.Logs;
import com.liyz.dubbo.common.base.result.Result;
import com.liyz.dubbo.common.base.service.LoginInfoService;
import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.base.util.DateUtil;
import com.liyz.dubbo.common.base.util.HttpRequestUtil;
import com.liyz.dubbo.common.controller.limit.annotation.Limit;
import com.liyz.dubbo.common.controller.limit.annotation.Limits;
import com.liyz.dubbo.common.controller.limit.enums.LimitType;
import com.liyz.dubbo.common.remote.bo.JwtUserBO;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import com.liyz.dubbo.common.security.annotation.Anonymous;
import com.liyz.dubbo.common.security.core.JwtAccessTokenConverter;
import com.liyz.dubbo.common.security.core.UserDetailsServiceImpl;
import com.liyz.dubbo.service.member.constant.MemberEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 16:31
 */
@Api(value = "用户鉴权", tags = "用户鉴权")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败"),
        @ApiResponse(code = 401, message = "暂无授权"),
        @ApiResponse(code = 404, message = "找不到资源"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private LoginInfoService loginInfoService;

    @Logs
    @Anonymous
    @ApiOperation(value = "登陆", notes = "登陆")
    @Limits(value = {@Limit(count = 1, type = LimitType.IP), @Limit(count = 10)})
    @PostMapping("/login")
    public Result<LoginVO> login(@Validated({LoginDTO.Login.class}) @RequestBody LoginDTO loginDTO) {
        HttpServletRequest httpServletRequest = HttpRequestUtil.getRequest();
        LiteDeviceResolver resolver = new LiteDeviceResolver();
        Device device = resolver.resolveDevice(httpServletRequest);
        String ip = HttpRequestUtil.getIpAddress(httpServletRequest);
        log.info("user login，ip:{}", ip);
        if (!doAuth(loginDTO)) {
            return Result.error(CommonCodeEnum.LoginFail);
        }
        LoginVO loginVO = loginToken(device, ip);
        return Result.success(loginVO);
    }

    private boolean doAuth(LoginDTO loginDTO) {
        try {
            Authentication token = new UsernamePasswordAuthenticationToken(loginDTO.getLoginName(), loginDTO.getLoginPwd());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (AuthenticationException e) {
            log.error("认证出错 method : {}", "doAuth");
            return false;
        }
    }

    private LoginVO loginToken(Device device, String ip) {
        MemberEnum.DeviceEnum deviceEnum ;
        if(device.isMobile()){
            deviceEnum = MemberEnum.DeviceEnum.MOBILE;
        }else{
            deviceEnum = MemberEnum.DeviceEnum.WEB;
        }
        JwtUserBO userInfo = loginInfoService.getUser();
        Date date = DateUtil.currentDate();
        final UserDetails userDetails = UserDetailsServiceImpl.getByJwtUser(userInfo);
        final String token = jwtAccessTokenConverter.generateToken(userDetails, device, date, userInfo.getUserId());
        Date expirationDateFromToken = jwtAccessTokenConverter.getExpirationDateFromToken(token);
        Long expirationDate = expirationDateFromToken.getTime();
        LoginVO loginVO = CommonConverterUtil.beanCopy(userInfo, LoginVO.class);
        loginVO.setExpirationDate(expirationDate);
        loginVO.setToken(token);
        return loginVO;
    }
}
