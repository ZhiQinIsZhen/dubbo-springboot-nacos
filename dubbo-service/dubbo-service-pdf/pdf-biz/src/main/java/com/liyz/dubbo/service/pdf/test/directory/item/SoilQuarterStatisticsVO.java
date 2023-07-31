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
 * @date 2023/2/1 17:35
 */
@Getter
@Setter
public class SoilQuarterStatisticsVO implements Serializable {
    private static final long serialVersionUID = 3648086686901653256L;

    @ApiModelProperty("年份")
    private Integer soilYear;

    @ApiModelProperty("季度")
    private Integer soilQuarter;

    @ApiModelProperty("统计量")
    private BigDecimal amount;
}
