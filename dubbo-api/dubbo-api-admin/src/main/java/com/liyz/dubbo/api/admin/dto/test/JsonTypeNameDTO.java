package com.liyz.dubbo.api.admin.dto.test;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/3 14:43
 */
@Data
public class JsonTypeNameDTO extends JsonTypeBaseDTO {
    private static final long serialVersionUID = 7247530189505383026L;

    @ApiModelProperty(value = "名字")
    private String name;
}
