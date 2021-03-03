package com.liyz.dubbo.api.open.controller.customer;

import com.liyz.dubbo.api.open.vo.customer.CustomerInfoVO;
import com.liyz.dubbo.common.base.request.annotation.Logs;
import com.liyz.dubbo.common.base.result.Result;
import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.controller.resolver.annotation.LoginUser;
import com.liyz.dubbo.common.remote.bo.JwtUserBO;
import com.liyz.dubbo.common.security.annotation.NonAuthority;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/8/17 15:37
 */
@Api(value = "customer信息", tags = "customer信息")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败"),
        @ApiResponse(code = 401, message = "暂无授权"),
        @ApiResponse(code = 404, message = "找不到资源"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Logs
    @NonAuthority
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header")
    @ApiOperation(value = "获取登陆的用户信息", notes = "获取登陆的用户信息")
    @GetMapping("/info")
    public Result<CustomerInfoVO> info(@ApiIgnore @LoginUser JwtUserBO jwtUserBO) {
        return Result.success(CommonConverterUtil.beanCopy(jwtUserBO, CustomerInfoVO.class));
    }
}
