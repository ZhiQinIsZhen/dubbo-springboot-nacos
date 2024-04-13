package com.liyz.dubbo.common.api.dto;

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
 * @date 2023/3/9 10:35
 */
@Getter
@Setter
public class PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码")
    @NotNull(groups = {PageQuery.class}, message = "分页查询页码不能为空")
    private Long pageNum = 1L;

    @ApiModelProperty(value = "每页数量")
    @NotNull(groups = {PageQuery.class}, message = "分页查询每页数量不能为空")
    private Long pageSize = 10L;

    public interface PageQuery {}

    public interface Query {}
}
