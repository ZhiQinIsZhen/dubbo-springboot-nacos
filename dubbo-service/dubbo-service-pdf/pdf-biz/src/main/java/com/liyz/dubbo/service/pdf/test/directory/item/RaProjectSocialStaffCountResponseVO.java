package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/10/17 9:38
 */
@Getter
@Setter
public class RaProjectSocialStaffCountResponseVO implements Serializable {
    private static final long serialVersionUID = -5592580892573078616L;

    @ApiModelProperty("年份")
    private String year;

    @ApiModelProperty("参保人数")
    private Integer socialStaffNum;

    @ApiModelProperty("同比增长率")
    private BigDecimal yoyGrowthRate;
}
