package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2023/2/1 17:35
 */
@Getter
@Setter
public class SoilTakeQuarterCityLevelStatisticsVO implements Serializable {
    private static final long serialVersionUID = 3648086686901653256L;

    @ApiModelProperty("年份")
    private Integer soilYear;

    @ApiModelProperty("季度")
    private Integer soilQuarter;

    @ApiModelProperty("一线数量")
    private Integer one;

    @ApiModelProperty("新一线数量")
    private Integer newOne;

    @ApiModelProperty("二线数量")
    private Integer two;

    @ApiModelProperty("三线数量")
    private Integer three;

    @ApiModelProperty("四线数量")
    private Integer four;

    @ApiModelProperty("五线数量")
    private Integer five;
}
