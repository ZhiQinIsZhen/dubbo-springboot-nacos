package com.liyz.dubbo.api.openapi3.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/10 14:24
 */
@Getter
@Setter
@Schema(name = "登录请求DTO", description = "登录请求")
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = -4503927168128475109L;

    @Schema(description = "用户名", required = true)
    @NotBlank(message = "用户名不能为空", groups = {Login.class})
    private String username;

    @Schema(description = "密码，8-20位数字或字母组成", example = "123456789", required = true)
    private String password;

    @Schema(description = "重定向路劲")
    private String redirect;

    public interface Login {}
}
