package com.liyz.dubbo.api.open.controller.auth;

import com.liyz.dubbo.api.open.dto.auth.LoginDTO;
import com.liyz.dubbo.api.open.vo.auth.LoginVO;
import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.security.client.context.JwtContextHolder;
import com.liyz.dubbo.security.core.annotation.Anonymous;
import com.liyz.dubbo.security.core.constant.SecurityEnum;
import com.liyz.dubbo.security.core.user.AuthUserDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
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

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/4/14 9:28
 */
@Api(value = "用户鉴权", tags = "用户鉴权")
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

    @Limits(value = {@Limit(count = 10, type = LimitType.IP), @Limit(count = 1, type = LimitType.TOTAL), @Limit(count = 1)})
    @Anonymous
    @ApiOperation(value = "登陆", notes = "登陆")
    @PostMapping("/login")
    public Result<LoginVO> login(@Validated({LoginDTO.Login.class}) @RequestBody LoginDTO loginDTO) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getLoginName(), loginDTO.getLoginPwd());
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authentication));
        AuthUserDetails authUserDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoginVO loginVO = LoginVO.builder()
                .userId(authUserDetails.getId())
                .loginName(authUserDetails.getLoginName())
                .userName(authUserDetails.getUsername())
                .nickName(authUserDetails.getNikeName())
                .email(authUserDetails.getEmail())
                .mobile(authUserDetails.getMobile())
                .roleIds(authUserDetails.getRoleIds())
                .token(JwtContextHolder.getJWT(authUserDetails.getLastWebPasswordResetDate(), SecurityEnum.AudienceType.Staff))
                .build();
        loginVO.setExpirationDate(JwtContextHolder.getJwtAuthCoreService().getExpirationByToken(loginVO.getToken()));
        return Result.success(loginVO);
    }
}
