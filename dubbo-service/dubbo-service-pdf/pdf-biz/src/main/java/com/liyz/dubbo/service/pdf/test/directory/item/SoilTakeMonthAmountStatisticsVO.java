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
 * @date 2023/2/1 15:04
 */
@Getter
@Setter
public class SoilTakeMonthAmountStatisticsVO implements Serializable {
    private static final long serialVersionUID = 2197289252099521188L;

    @ApiModelProperty("年月")
    private String yearMonth;

    @ApiModelProperty("拿地金额(万元)")
    private BigDecimal closingCost;

    @ApiModelProperty("溢价率(%)")
    private BigDecimal averagePremiumRate;

    @ApiModelProperty("权益拿地金额(万元)")
    private BigDecimal shareClosingCost;
}
