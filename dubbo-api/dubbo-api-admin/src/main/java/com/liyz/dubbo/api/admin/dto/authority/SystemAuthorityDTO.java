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
 * @date 2023/3/13 14:33
 */
@Getter
@Setter
public class SystemAuthorityDTO implements Serializable {
    private static final long serialVersionUID = 223587002932780274L;

    @NotNull(message = "权限ID不能为空")
    @ApiModelProperty(value = "权限ID", required = true)
    private Integer authorityId;

    @NotBlank(message = "权限码不能为空")
    @ApiModelProperty(value = "权限码", required = true)
    private String authority;

    @NotBlank(message = "权限名称不能为空")
    @ApiModelProperty(value = "权限名称", required = true)
    private String authorityName;

    @ApiModelProperty(value = "父权限ID")
    private Integer parentAuthorityId;

    @NotBlank(message = "应用ID不能为空")
    @ApiModelProperty(value = "应用ID", required = true)
    private String clientId;
}
