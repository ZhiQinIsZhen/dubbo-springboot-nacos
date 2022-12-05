package com.liyz.dubbo.api.open.vo.bigdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/10/12 15:26
 */
@Getter
@Setter
public class RaProjectSocialStaffResponse implements Serializable {
    private static final long serialVersionUID = -3980100918988173181L;

    @ApiModelProperty("年份")
    private Integer year;

    @ApiModelProperty("参保人数")
    private Integer socialStaffNum;
}
