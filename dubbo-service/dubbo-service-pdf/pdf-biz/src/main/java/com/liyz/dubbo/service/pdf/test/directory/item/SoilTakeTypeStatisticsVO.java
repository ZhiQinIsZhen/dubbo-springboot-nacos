package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2023/2/1 17:47
 */
@Getter
@Setter
public class SoilTakeTypeStatisticsVO implements Serializable {
    private static final long serialVersionUID = 3051800914316068560L;

    @ApiModelProperty("省份")
    private String province;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("地区")
    private String area;

    @ApiModelProperty("住宅用地数量")
    private Integer zzCount;

    @ApiModelProperty("住宅用地规划建筑面积")
    private BigDecimal zzPlanningBuildingArea;

    @ApiModelProperty("住宅用地权益规划建筑面积")
    private BigDecimal zzSharePlanningArea;

    @ApiModelProperty("综合用地(含住宅)数量")
    private Integer zhCount;

    @ApiModelProperty("综合用地(含住宅)规划建筑面积")
    private BigDecimal zhPlanningBuildingArea;

    @ApiModelProperty("综合用地(含住宅)权益规划建筑面积")
    private BigDecimal zhSharePlanningArea;

    @ApiModelProperty("商业/办公用地数量")
    private Integer syCount;

    @ApiModelProperty("商业/办公用地规划建筑面积")
    private BigDecimal syPlanningBuildingArea;

    @ApiModelProperty("商业/办公用地权益规划建筑面积")
    private BigDecimal sySharePlanningArea;

    @ApiModelProperty("工业用地数量")
    private Integer gyCount;

    @ApiModelProperty("工业用地规划建筑面积")
    private BigDecimal gyPlanningBuildingArea;

    @ApiModelProperty("工业用地权益规划建筑面积")
    private BigDecimal gySharePlanningArea;

    @ApiModelProperty("其它用地数量")
    private Integer qtCount;

    @ApiModelProperty("其它用地规划建筑面积")
    private BigDecimal qtPlanningBuildingArea;

    @ApiModelProperty("其它用地权益规划建筑面积")
    private BigDecimal qtSharePlanningArea;

    public Integer getTotalCount() {
        return (Objects.nonNull(zzCount) ? zzCount : 0) +
                (Objects.nonNull(zhCount) ? zhCount : 0) +
                (Objects.nonNull(syCount) ? syCount : 0) +
                (Objects.nonNull(gyCount) ? gyCount : 0) +
                (Objects.nonNull(qtCount) ? qtCount : 0);
    }

    public BigDecimal getTotalPlanningBuildingArea() {
        return (Objects.nonNull(zzPlanningBuildingArea) ? zzPlanningBuildingArea : BigDecimal.ZERO)
                .add(Objects.nonNull(zhPlanningBuildingArea) ? zhPlanningBuildingArea : BigDecimal.ZERO)
                .add(Objects.nonNull(syPlanningBuildingArea) ? syPlanningBuildingArea : BigDecimal.ZERO)
                .add(Objects.nonNull(gyPlanningBuildingArea) ? gyPlanningBuildingArea : BigDecimal.ZERO)
                .add(Objects.nonNull(qtPlanningBuildingArea) ? qtPlanningBuildingArea : BigDecimal.ZERO);
    }

    public BigDecimal getTotalSharePlanningArea() {
        return (Objects.nonNull(zzSharePlanningArea) ? zzSharePlanningArea : BigDecimal.ZERO)
                .add(Objects.nonNull(zhSharePlanningArea) ? zhSharePlanningArea : BigDecimal.ZERO)
                .add(Objects.nonNull(sySharePlanningArea) ? sySharePlanningArea : BigDecimal.ZERO)
                .add(Objects.nonNull(gySharePlanningArea) ? gySharePlanningArea : BigDecimal.ZERO)
                .add(Objects.nonNull(qtSharePlanningArea) ? qtSharePlanningArea : BigDecimal.ZERO);
    }
}
