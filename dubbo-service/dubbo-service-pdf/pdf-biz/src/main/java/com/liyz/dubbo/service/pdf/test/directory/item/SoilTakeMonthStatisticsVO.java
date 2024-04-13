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
 * @date 2023/2/1 17:31
 */
@Getter
@Setter
public class SoilTakeMonthStatisticsVO implements Serializable {
    private static final long serialVersionUID = 3049554136931282600L;

    @ApiModelProperty("年月")
    private String yearMonth;

    @ApiModelProperty("地块数量")
    private Integer soilCount;

    @ApiModelProperty("拿地金额(万元)")
    private BigDecimal closingCost;

    @ApiModelProperty("权益拿地金额(万元)")
    private BigDecimal shareClosingCost;

    @ApiModelProperty("规划建筑面积(㎡)")
    private BigDecimal planningBuildingArea;

    @ApiModelProperty("权益规划建筑面积(㎡)")
    private BigDecimal sharePlanningArea;

    @ApiModelProperty("成交楼面均价(万元/㎡)")
    private BigDecimal closingFloorPrice;

    @ApiModelProperty("溢价率(%)")
    private BigDecimal averagePremiumRate;
}
