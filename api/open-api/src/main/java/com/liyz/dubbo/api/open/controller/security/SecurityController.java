package com.liyz.dubbo.api.open.controller.security;

import com.liyz.dubbo.common.core.auth.AuthUser;
import com.liyz.dubbo.common.core.result.Result;
import com.liyz.dubbo.common.core.util.AuthContext;
import com.liyz.dubbo.common.limit.annotation.Limit;
import com.liyz.dubbo.common.limit.annotation.Limits;
import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import com.liyz.dubbo.security.core.annotation.Anonymous;
import com.liyz.dubbo.security.encrypt.annotation.Encrypt;
import com.liyz.dubbo.security.encrypt.remote.RemoteAlgorithmService;
import com.liyz.dubbo.security.encrypt.remote.RemoteUserAlgorithmService;
import com.liyz.dubbo.security.encrypt.vo.UserAlgorithmVO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/16 20:44
 */
@Api(tags = "安全")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/security")
public class SecurityController {

    @DubboReference
    private RemoteAlgorithmService remoteAlgorithmService;
    @DubboReference
    private RemoteUserAlgorithmService remoteUserAlgorithmService;

    @Anonymous
    @Limits(value = {@Limit(count = 10, type = LimitType.IP), @Limit(count = 1, type = LimitType.TOTAL), @Limit(count = 1)})
    @ApiOperation("创建RSA密钥对")
    @GetMapping("/create/rsa")
    public Result<List<String>> createRsa(@RequestParam(value = "keySize", defaultValue = "1024") Integer keySize) {
        return Result.success(remoteAlgorithmService.createKeys(keySize));
    }

    @Limits(value = {@Limit(count = 10, type = LimitType.IP), @Limit(count = 1, type = LimitType.TOTAL), @Limit(count = 1)})
    @ApiOperation("给登陆用户创建对应加解密")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    @PostMapping("/userAlgorithm/add")
    public Result<Boolean> userAlgorithmAdd(@RequestBody UserAlgorithmVO userAlgorithmVO) {
        AuthUser authUser = AuthContext.getAuthUser();
        userAlgorithmVO.setUserId(authUser.getUserId());
        return Result.success(remoteUserAlgorithmService.create(userAlgorithmVO));
    }


    @Encrypt
    @Limits(value = {@Limit(count = 10, type = LimitType.IP), @Limit(count = 1, type = LimitType.TOTAL), @Limit(count = 1)})
    @ApiOperation("测试")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    @PostMapping("/test")
    public Result<UserAlgorithmVO> test(@RequestBody UserAlgorithmVO userAlgorithmVO) {
        AuthUser authUser = AuthContext.getAuthUser();
        userAlgorithmVO.setUserId(authUser.getUserId());
        log.info("解密后内容为:{}", JsonMapperUtil.toJSONString(userAlgorithmVO));
        return Result.success(userAlgorithmVO);
    }

    @Limits(value = {@Limit(count = 10, type = LimitType.IP), @Limit(count = 1, type = LimitType.TOTAL), @Limit(count = 1)})
    @ApiOperation("加密数据")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    @PostMapping("/encrypt")
    public Result<String> encrypt(@RequestParam("data") String data) {
        return Result.success(remoteAlgorithmService.encrypt(data, AuthContext.getAuthUser().getUserId()));
    }

    @Limits(value = {@Limit(count = 10, type = LimitType.IP), @Limit(count = 1, type = LimitType.TOTAL), @Limit(count = 1)})
    @ApiOperation("解密数据")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    @PostMapping("/decrypt")
    public Result<String> decrypt(@RequestParam("data") String data) {
        return Result.success(remoteAlgorithmService.decrypt(data, AuthContext.getAuthUser().getUserId()));
    }
}
