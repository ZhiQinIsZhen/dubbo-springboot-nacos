package com.liyz.dubbo.api.open.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/8/27 16:15
 */
@Getter
@Setter
@ApiModel("分页DTO")
public class PageDTO implements Serializable {
    private static final long serialVersionUID = -8112579193480486838L;

    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页展示数量", example = "10")
    private Integer pageSize = 10;
}
