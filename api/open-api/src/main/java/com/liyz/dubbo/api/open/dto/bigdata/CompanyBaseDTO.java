package com.liyz.dubbo.api.open.dto.bigdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/20 11:04
 */
@Getter
@Setter
public class CompanyBaseDTO implements Serializable {
    private static final long serialVersionUID = 5071181318396498180L;

    @NotBlank
    @ApiModelProperty(value = "公司id")
    private String id;
}
