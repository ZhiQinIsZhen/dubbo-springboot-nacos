package com.liyz.dubbo.api.admin.dto.staff;

import com.liyz.dubbo.common.api.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/11 10:29
 */
@Getter
@Setter
public class StaffLogPageDTO extends PageDTO {
    private static final long serialVersionUID = -4827292438113353379L;

    @ApiModelProperty(value = "员工ID")
    private Long staffId;
}
