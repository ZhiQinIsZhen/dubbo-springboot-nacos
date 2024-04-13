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
 * @date 2023/2/1 17:11
 */
@Getter
@Setter
public class SoilTakeLastYearStatisticsVO implements Serializable {
    private static final long serialVersionUID = -2888749435952360797L;

    @ApiModelProperty("地块数量")
    private Integer soilCount;

    @ApiModelProperty("拿地金额(万元)")
    private BigDecimal closingCost;

    @ApiModelProperty("规划建筑面积(㎡)")
    private BigDecimal planningBuildingArea;

    @ApiModelProperty("权益占比(%)")
    private BigDecimal shareValue;
}
