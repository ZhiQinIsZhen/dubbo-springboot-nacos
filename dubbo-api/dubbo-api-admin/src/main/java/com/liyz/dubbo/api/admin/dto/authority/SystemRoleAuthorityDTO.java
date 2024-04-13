package com.liyz.dubbo.api.admin.dto.authority;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/11 15:33
 */
@Getter
@Setter
public class SystemRoleAuthorityDTO implements Serializable {
    private static final long serialVersionUID = 8328113920009535585L;

    @NotNull(message = "角色ID不能为空")
    @ApiModelProperty(value = "角色ID", required = true)
    private Integer roleId;

    @NotNull(message = "权限ID不能为空")
    @ApiModelProperty(value = "权限ID", required = true)
    private Integer authorityId;
}
