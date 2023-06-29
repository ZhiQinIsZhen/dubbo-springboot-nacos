package com.liyz.dubbo.api.openapi3.controller.auth;

import com.liyz.dubbo.api.openapi3.dto.LoginDTO;
import com.liyz.dubbo.api.openapi3.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/29 9:51
 */
@Tag(name = "认证测试")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Operation(summary = "登录", description = "这是一个登录接口")
    @PostMapping("/login")
    public Result<String> login(/*@Validated(LoginDTO.Login.class)*/ @RequestBody LoginDTO loginDTO) {
        return Result.success("登录成功");
    }
}
