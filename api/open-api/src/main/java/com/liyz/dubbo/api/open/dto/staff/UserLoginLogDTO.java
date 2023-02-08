package com.liyz.dubbo.api.open.dto.staff;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2023/2/8 14:57
 */
@Getter
@Setter
public class UserLoginLogDTO implements Serializable {
    private static final long serialVersionUID = -8626685271237433398L;

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "日志ID", example = "1")
    private Long id;
}
