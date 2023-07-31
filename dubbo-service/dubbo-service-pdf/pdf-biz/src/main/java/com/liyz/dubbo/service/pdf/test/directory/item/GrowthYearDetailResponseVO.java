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
public class GrowthYearDetailResponseVO extends YearDetailBaseResponseVO implements Serializable  {


    /**
     * 营业收入%
     */
    @ApiModelProperty("营业收入%")
    private BigDecimal revenueRatio;


    @ApiModelProperty("营业收入%变化[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]")
    private String revenueChange;


    @ApiModelProperty("营业收入%变化[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]值")
    private BigDecimal revenueChangeValue;
    /**
     * 归母公司净利润%
     */
    @ApiModelProperty("归母公司净利润%")
    private BigDecimal momNetProfitRatio;

    @ApiModelProperty("归母公司净利润%变化[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]")
    private String momNetProfitChange;

    @ApiModelProperty("归母公司净利润%变化[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]值")
    private BigDecimal momNetProfitChangeValue;

    /**
     * 归母公司净利润-扣除非经常损益%
     */
    @ApiModelProperty("归母公司净利润-扣除非经常损益%")
    private BigDecimal deficitRatio;

    /**
     * 归母公司净利润-扣除非经常损益%
     */
    @ApiModelProperty("归母公司净利润-扣除非经常损益%变化[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]")
    private String deficitChange;

    @ApiModelProperty("归母公司净利润-扣除非经常损益%变化[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]值")
    private BigDecimal deficitChangeValue;
    /**
     * 经营活动现金流量净额%
     */
    @ApiModelProperty("经营活动现金流量净额%")
    private BigDecimal netCashRatio;

    @ApiModelProperty("经营活动现金流量净额%变化[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]")
    private String netCashChange;

    @ApiModelProperty("经营活动现金流量净额%变化[上升:UP,下降:DOWN,持平:FLAT,未知:NONE]值")
    private BigDecimal netCashChangeValue;

}
