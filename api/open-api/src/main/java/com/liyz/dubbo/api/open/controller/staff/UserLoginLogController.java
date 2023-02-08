package com.liyz.dubbo.api.open.controller.staff;

import com.liyz.dubbo.api.open.dto.staff.UserLoginLogDTO;
import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.service.staff.remote.RemoteStaLogService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注释:staff controller
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/1/6 14:31
 */
@Api(tags = "用户登陆日志信息")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/user/log")
public class UserLoginLogController {

    @DubboReference
    private RemoteStaLogService remoteStaLogService;

    @Limits(value = {@Limit(count = 2)})
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    @ApiOperation("删除登陆日志")
    @PostMapping("/deleteById")
    public Result<Boolean> deleteById(@Validated @RequestBody UserLoginLogDTO loginLogDTO) {
        return Result.success(remoteStaLogService.deleteById(loginLogDTO.getId()) > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Limits(value = {@Limit(count = 2)})
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    @ApiOperation("移除登陆日志")
    @PostMapping("/removeById")
    public Result<Boolean> removeById(@Validated @RequestBody UserLoginLogDTO loginLogDTO) {
        return Result.success(remoteStaLogService.removeById(loginLogDTO.getId()) > 0 ? Boolean.TRUE : Boolean.FALSE);
    }
}
