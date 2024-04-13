package com.liyz.dubbo.api.admin.dto.authority;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/13 13:40
 */
@Getter
@Setter
public class StaffRoleDTO implements Serializable {
    private static final long serialVersionUID = -5019855188001537438L;

    @ApiModelProperty(value = "员工ID", required = true)
    private Long staffId;

    @NotNull(message = "角色ID不能为空")
    @ApiModelProperty(value = "角色ID", required = true)
    private Integer roleId;
}
