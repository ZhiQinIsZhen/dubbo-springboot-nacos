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
public class SoilTakeMonthAreaStatisticsVO implements Serializable {
    private static final long serialVersionUID = 2197289252099521188L;

    @ApiModelProperty("年月")
    private String yearMonth;

    @ApiModelProperty("地块数量")
    private Integer soilCount;

    @ApiModelProperty("规划建筑面积(㎡)")
    private BigDecimal planningBuildingArea;

    @ApiModelProperty("权益规划建筑面积(㎡)")
    private BigDecimal sharePlanningArea;

    @ApiModelProperty("拿地中位线")
    private Integer midCount;
}
