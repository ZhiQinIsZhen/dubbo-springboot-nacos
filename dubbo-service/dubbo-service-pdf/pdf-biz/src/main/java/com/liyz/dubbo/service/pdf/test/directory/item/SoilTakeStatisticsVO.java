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
public class SoilTakeStatisticsVO implements Serializable {
    private static final long serialVersionUID = 3049554136931282600L;

    @ApiModelProperty("地块数量")
    private Integer soilCount;

    @ApiModelProperty("权益占比(%)")
    private BigDecimal shareValue;

    @ApiModelProperty("拿地金额(亿元)")
    private BigDecimal closingCost;

    @ApiModelProperty("权益拿地金额(亿元)")
    private BigDecimal shareClosingCost;

    @ApiModelProperty("规划建筑面积(万㎡)")
    private BigDecimal planningBuildingArea;

    @ApiModelProperty("权益规划建筑面积(万㎡)")
    private BigDecimal sharePlanningArea;
}
