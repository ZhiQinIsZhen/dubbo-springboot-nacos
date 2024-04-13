package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:利润详情
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 15:34
 */
@Getter
@Setter
@ApiModel("利润详情 -三方接口")
public class RaProjectTycProfitResponseVO implements Serializable {
    private static final long serialVersionUID = 2126421559819544784L;

    @ApiModelProperty("年")
    private String showYear;

    @ApiModelProperty("营业总收入")
    private String totalRevenue;

    @ApiModelProperty("营业收入")
    private String revenue;

    @ApiModelProperty("营业总成本")
    private String operatingCosts;

    @ApiModelProperty("营业成本")
    private String operatingCost;

    @ApiModelProperty("研发费用")
    private String radCost;

    @ApiModelProperty("营业税金及附加")
    private String operatingTaxesAndSurcharge;

    @ApiModelProperty("销售费用")
    private String salesFee;

    @ApiModelProperty("管理费用")
    private String manageFee;

    @ApiModelProperty("财务费用")
    private String financingExpenses;

    @ApiModelProperty("资产减值损失")
    private String assetImpairmentLoss;

    @ApiModelProperty("其他经营收益")
    private String othrIncome;

    @ApiModelProperty("加:公允价值变动收益")
    private String incomeFromChgInFv;

    @ApiModelProperty("加:投资收益")
    private String investIncome;

    @ApiModelProperty("其中:对联营企业和合营企业的投资收益")
    private String investIncomesFromRr;

    @ApiModelProperty("营业利润")
    private String op;

    @ApiModelProperty("加:营业外收入")
    private String nonOperatingIncome;

    @ApiModelProperty("减:营业外支出")
    private String nonOperatingPayout;

    @ApiModelProperty("利润总额")
    private String profitTotalAmt;

    @ApiModelProperty("减:所得税费用")
    private String incomeTaxExpenses;

    @ApiModelProperty("净利润")
    private String netProfit;

    @ApiModelProperty("其中:归属于母公司股东的净利润")
    private String netProfitAtsopc;

    @ApiModelProperty("少数股东损益")
    private String minorityGal;

    @ApiModelProperty("扣除非经常性损益后的净利润")
    private String profitDeductNrgalLySq;

    @ApiModelProperty("基本每股收益")
    private String basicEps;

    @ApiModelProperty("稀释每股收益")
    private String dltEarningsPerShare;

    @ApiModelProperty("其他综合收益")
    private String othrCompreIncome;

    @ApiModelProperty("归属于母公司股东的其他综合收益")
    private String othrCompreIncomeAtoopc;

    @ApiModelProperty("归属于少数股东的其他综合收益")
    private String othrCompreIncomeAtms;

    @ApiModelProperty("综合收益总额")
    private String totalCompreIncome;

    @ApiModelProperty("归属于母公司所有者的综合收益总额")
    private String totalCompreIncomeAtsopc;

    @ApiModelProperty("归属于少数股东的综合收益总额")
    private String totalCompreIncomeAtms;
}
