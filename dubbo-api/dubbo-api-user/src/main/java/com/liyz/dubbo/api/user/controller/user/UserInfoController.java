package com.liyz.dubbo.api.user.controller.user;

import com.liyz.dubbo.api.user.vo.user.UserInfoVO;
import com.liyz.dubbo.api.user.vo.user.UserLoginLogVO;
import com.liyz.dubbo.api.user.vo.user.UserLogoutLogVO;
import com.liyz.dubbo.common.api.dto.PageDTO;
import com.liyz.dubbo.common.api.result.PageResult;
import com.liyz.dubbo.common.api.result.Result;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.remote.page.PageBO;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.security.client.annotation.Anonymous;
import com.liyz.dubbo.security.client.context.AuthContext;
import com.liyz.dubbo.service.auth.bo.AuthUserBO;
import com.liyz.dubbo.service.user.bo.UserLoginLogBO;
import com.liyz.dubbo.service.user.bo.UserLogoutLogBO;
import com.liyz.dubbo.service.user.remote.RemoteUserInfoService;
import com.liyz.dubbo.service.user.remote.RemoteUserLoginLogService;
import com.liyz.dubbo.service.user.remote.RemoteUserLogoutLogService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/19 11:17
 */
@Api(tags = "员工信息")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @DubboReference
    private RemoteUserInfoService remoteUserInfoService;
    @DubboReference
    private RemoteUserLoginLogService remoteUserLoginLogService;
    @DubboReference
    private RemoteUserLogoutLogService remoteUserLogoutLogService;

    @Limits(@Limit(count = 100))
    @ApiOperation("查询当前登录员工信息")
    @GetMapping("/current")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    public Result<UserInfoVO> userInfo() {
        AuthUserBO authUserBO = AuthContext.getAuthUser();
        return Result.success(BeanUtil.copyProperties(remoteUserInfoService.getByUserId(authUserBO.getAuthId()), UserInfoVO.class));
    }

    @Limits(@Limit(type = LimitType.USER_MAPPING, count = 2))
    @ApiOperation("分页查询员工登录日志")
    @GetMapping("/loginLogs/page")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    public PageResult<UserLoginLogVO> pageLoginLogs(PageDTO page) {
        AuthUserBO authUserBO = AuthContext.getAuthUser();
        page = Objects.nonNull(page) ? page : new PageDTO();
        RemotePage<UserLoginLogBO> remotePage = remoteUserLoginLogService.page(authUserBO.getAuthId(), BeanUtil.copyProperties(page, PageBO.class));
        return PageResult.success(BeanUtil.copyProperties(remotePage, UserLoginLogVO.class));
    }

    @Limits(@Limit(type = LimitType.IP, count = 2))
    @ApiOperation("分页查询员工登出日志")
    @GetMapping("/logoutLogs/page")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    public PageResult<UserLogoutLogVO> pageLogoutLogs(PageDTO page) {
        AuthUserBO authUserBO = AuthContext.getAuthUser();
        page = Objects.nonNull(page) ? page : new PageDTO();
        RemotePage<UserLogoutLogBO> remotePage = remoteUserLogoutLogService.page(authUserBO.getAuthId(), BeanUtil.copyProperties(page, PageBO.class));
        return PageResult.success(BeanUtil.copyProperties(remotePage, UserLogoutLogVO.class));
    }

    @Anonymous
    @ApiOperation("测试")
    @GetMapping("/test")
    public Result<Boolean> test() {
        remoteUserInfoService.test();
        return Result.success(Boolean.TRUE);
    }
}
