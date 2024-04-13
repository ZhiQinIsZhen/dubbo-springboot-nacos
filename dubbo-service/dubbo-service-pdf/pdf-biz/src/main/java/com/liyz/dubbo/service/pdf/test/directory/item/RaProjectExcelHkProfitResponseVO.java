package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:利润(excel)
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 16:42
 */
@Getter
@Setter
public class RaProjectExcelHkProfitResponseVO implements Serializable {
    private static final long serialVersionUID = 3597020977610991338L;

    @ApiModelProperty("报告期")
    private String year;

    @ApiModelProperty("报表类型")
    private String type;

    @ApiModelProperty("营业总收入")
    private String totalOperatingIncome;

    @ApiModelProperty("主营业务收入")
    private String mainBusinessIncome;

    @ApiModelProperty("其他营业收入")
    private String otherBusinessIncome;

    @ApiModelProperty("营业总支出")
    private String totalOperatingExpenses;

    @ApiModelProperty("营业成本")
    private String operatingCost;

    @ApiModelProperty("营业开支")
    private String operatingExpenses;

    @ApiModelProperty("营业利润")
    private String operatingProfit;

    @ApiModelProperty("加：利息收入")
    private String interestIncome;

    @ApiModelProperty("减：利息支出")
    private String interestExpense;

    @ApiModelProperty("加：权益性投资损益")
    private String equityInvestmentProfitLoss;

    @ApiModelProperty("其他非经营性损益")
    private String otherNonOperatingProfitLoss;

    @ApiModelProperty("非经常项目前利润")
    private String  extraordinaryItemsBeforeProfit;

    @ApiModelProperty("加：非经常项目损益")
    private String extraordinaryItemsProfitLoss;

    @ApiModelProperty("除税前利润")
    private String profitBeforeTax;

    @ApiModelProperty("减：所得税")
    private String incomeTax;

    @ApiModelProperty("少数股东损益")
    private String minorityShareholderProfitLoss;

    @ApiModelProperty("持续经营净利润")
    private String continuingOperationsProfit;

    @ApiModelProperty("加：非持续经营净利润")
    private String discontinuedOperationsProfit;

    @ApiModelProperty("其他特殊项")
    private String otherSpecialItems;

    @ApiModelProperty("净利润")
    private String netProfit;

    @ApiModelProperty("减：优先股利及其他调整项")
    private String preferredDividendsOtherAdjustments;

    @ApiModelProperty("归属普通股东净利润")
    private String ordinaryShareholdersProfit;

    @ApiModelProperty("综合收益")
    private String comprehensiveIncome;

    @ApiModelProperty("显示币种")
    private String displayCurrency;

    @ApiModelProperty("原始币种")
    private String originalCurrency;

    @ApiModelProperty("转换汇率")
    private String convertExchangeRate;

    @ApiModelProperty("利率类型")
    private String rateType;

    @ApiModelProperty("区间起始日")
    private String intervalStartDate;

    @ApiModelProperty("区间截止日")
    private String intervalEndDate;

    @ApiModelProperty("报告期跨度")
    private String spanReportingPeriod;

    @ApiModelProperty("公告日期")
    private String announcementDate;

    @ApiModelProperty("会计准则")
    private String accountingStandards;

    @ApiModelProperty("审计意见")
    private String auditOpinions;

    @ApiModelProperty("核数师")
    private String auditor;

    @ApiModelProperty("原始报表")
    private String originalReport;
}
