package com.liyz.dubbo.api.web.controller.transaction;

import com.liyz.dubbo.api.web.vo.transaction.AccountVO;
import com.liyz.dubbo.common.base.result.Result;
import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.security.annotation.Anonymous;
import com.liyz.dubbo.service.transaction.bo.AccountBO;
import com.liyz.dubbo.service.transaction.remote.RemoteAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/20 15:03
 */
@Api(value = "账户信息", tags = "账户信息")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败"),
        @ApiResponse(code = 401, message = "暂无授权"),
        @ApiResponse(code = 404, message = "找不到资源"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
@Slf4j
@RestController
@RequestMapping("/transaction")
public class AccountController {

    @DubboReference(version = "1.0.0")
    RemoteAccountService remoteAccountService;

    @Anonymous
    @ApiOperation(value = "更新账户信息", notes = "更新账户信息")
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody AccountVO accountVO) {
        remoteAccountService.update(CommonConverterUtil.beanCopy(accountVO, AccountBO.class));
        return Result.success(Boolean.TRUE);
    }
}
