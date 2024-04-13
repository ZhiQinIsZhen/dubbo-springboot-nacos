package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/23 14:55
 */
@Getter
@Setter
public class FinanceDataResponseVO extends  IndicatorDataResponseVO implements Serializable  {


    //排序id
    private Long id;

    @ApiModelProperty("年份")
    private Integer year;

    //财报类别 1：年报；2：中报 ；3：一季度；4:三季度
    @ApiModelProperty("财报类别 1：年报；2：中报 ；3：一季度；4:三季度")
    private Integer reportType;

    //总资产
    @ApiModelProperty("总资产")
    private BigDecimal totalAsset;

    //流动资产
    @ApiModelProperty("流动资产")
    private BigDecimal liquidAsset;

    //非流动资产
    @ApiModelProperty("非流动资产")
    private BigDecimal nonLiquidAsset;

    //总负债
    @ApiModelProperty("总负债")
    private BigDecimal totalLiabilities;

    //流动负债
    @ApiModelProperty("流动负债")
    private BigDecimal liquidLiabilities;

    //非流动负债
    @ApiModelProperty("非流动负债")
    private BigDecimal nonLiquidLiabilities;

    //所有者权益
    @ApiModelProperty("所有者权益")
    private BigDecimal ownerEquity;

    //少数股东权益
    @ApiModelProperty("少数股东权益")
    private BigDecimal holderEquity;
}
