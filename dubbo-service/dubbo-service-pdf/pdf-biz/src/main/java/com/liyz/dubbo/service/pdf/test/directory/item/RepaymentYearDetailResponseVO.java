package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/23 15:01
 */
@Getter
@Setter
public class RepaymentYearDetailResponseVO extends YearDetailBaseResponseVO implements Serializable  {


    //流动比率
    @ApiModelProperty("流动比率")
    private BigDecimal liquidRatio;

    @ApiModelProperty("流动比率[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]")
    private String liquidRatioChange;


    @ApiModelProperty("流动比率[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]值")
    private BigDecimal liquidRatioChangeValue;


    //速动比率
    @ApiModelProperty("速动比率")
    private BigDecimal quickRatio;


    @ApiModelProperty("速动比率[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]")
    private String quickRatioChange;


    @ApiModelProperty("速动比率[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]值")
    private BigDecimal quickRatioChangeValue;



    //资产负债率
    @ApiModelProperty("资产负债率")
    private BigDecimal assetLiabilityRatio;

    @ApiModelProperty("资产负债率[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]")
    private String assetLiabilityRatioChange;


    @ApiModelProperty("资产负债率[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]值")
    private BigDecimal assetLiabilityRatioChangeValue;

    /**
     * 已获利息倍数
     */
    @ApiModelProperty("已获利息倍数")
    private BigDecimal interestEarned;


    @ApiModelProperty("已获利息倍数[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]")
    private String interestEarnedChange;


    @ApiModelProperty("已获利息倍数[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]值")
    private BigDecimal interestEarnedChangeValue;
}
