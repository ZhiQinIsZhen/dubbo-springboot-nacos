package com.liyz.dubbo.api.admin.dto.test;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/3 14:43
 */
@Getter
@Setter
public class JsonTypeAgeDTO extends JsonTypeBaseDTO {
    private static final long serialVersionUID = 7247530189505383026L;

    @ApiModelProperty(value = "年龄")
    private Integer age;
}
