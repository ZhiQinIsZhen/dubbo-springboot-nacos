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
public class OperationYearDetailResponseVO extends YearDetailBaseResponseVO implements Serializable  {




    //营业收入
    @ApiModelProperty("营业收入")
    private BigDecimal businessIncome;

    @ApiModelProperty("营业收入[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]")
    private String businessIncomeChange;


    @ApiModelProperty("营业收入[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]值")
    private BigDecimal businessIncomeChangeValue;

    //税前利润
    @ApiModelProperty("税前利润")
    private BigDecimal profitBeforeTax;

    @ApiModelProperty("税前利润[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]")
    private String profitBeforeTaxChange;


    @ApiModelProperty("税前利润[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]值")
    private BigDecimal profitBeforeTaxChangeValue;

    //销售净利率
    @ApiModelProperty("销售净利率")
    private BigDecimal saleNetProfitRatio;

    @ApiModelProperty("销售净利率[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]")
    private String saleNetProfitRatioChange;


    @ApiModelProperty("销售净利率[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]值")
    private BigDecimal saleNetProfitRatioChangeValue;
}
