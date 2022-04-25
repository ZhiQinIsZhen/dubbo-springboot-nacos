package com.liyz.dubbo.api.open.controller.staff;

import com.github.xiaoymin.knife4j.annotations.Ignore;
import com.liyz.dubbo.api.open.event.UserEvent;
import com.liyz.dubbo.api.open.vo.staff.UserInfoVO;
import com.liyz.dubbo.common.controller.annotation.LoginUser;
import com.liyz.dubbo.common.core.auth.AuthUser;
import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.core.util.AuthContext;
import com.liyz.dubbo.common.core.util.CommonCloneUtil;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.security.core.annotation.Anonymous;
import com.liyz.dubbo.security.core.annotation.NonAuthority;
import com.liyz.dubbo.service.staff.bo.CustomerBO;
import com.liyz.dubbo.service.staff.remote.RemoteCustomerService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/1/6 14:31
 */
@Api(value = "用户信息", tags = "用户信息")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private ApplicationContext applicationContext;
    @DubboReference
    private RemoteCustomerService remoteCustomerService;

    @PreAuthorize("hasAuthority('ALL;/user/info')")
    @Limits(value = {@Limit(count = 1)})
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header")
    @ApiOperation(value = "获取登陆的用户信息", notes = "获取登陆的用户信息")
    @GetMapping("/info")
    public Result<UserInfoVO> info() {
        applicationContext.publishEvent(new UserEvent(this, AuthContext.getAuthUser().getUserId()));
        return Result.success(CommonCloneUtil.objectClone(AuthContext.getAuthUser(), UserInfoVO.class));
    }

    @NonAuthority
    @Limits(value = {@Limit(count = 1)})
    @ApiOperation(value = "获取登陆的用户ID", notes = "获取登陆的用户ID")
    @GetMapping("/id")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header")
    public Result<Long> id(@Ignore @LoginUser AuthUser authUser) {
        applicationContext.publishEvent(new UserEvent(this, AuthContext.getAuthUser().getUserId()));
        return Result.success(Objects.isNull(AuthContext.getAuthUser()) ? null : AuthContext.getAuthUser().getUserId());
    }

    @Anonymous
    @Limits(value = {@Limit(count = 1)})
    @ApiOperation(value = "根据用户名查询用户信息", notes = "根据用户名查询用户信息")
    @GetMapping("/userInfo")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header")
    public Result<UserInfoVO> userInfo(@RequestParam("username") String username) {
        CustomerBO customerBO = remoteCustomerService.getByUsername(username);
        return Result.success(CommonCloneUtil.objectClone(customerBO, UserInfoVO.class));
    }
}
