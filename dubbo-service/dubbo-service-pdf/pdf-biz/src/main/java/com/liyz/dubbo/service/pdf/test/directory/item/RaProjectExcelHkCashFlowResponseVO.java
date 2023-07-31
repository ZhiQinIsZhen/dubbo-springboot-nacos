package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:现金流
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 16:42
 */
@Getter
@Setter
public class RaProjectExcelHkCashFlowResponseVO implements Serializable {
    private static final long serialVersionUID = -4799145838202125021L;

    @ApiModelProperty("报告期")
    private String year;

    @ApiModelProperty("报表类型")
    private String type;

    @ApiModelProperty("净利润")
    private String netProfit;

    @ApiModelProperty("加：折旧与摊销")
    private String depreciationAmortization;

    @ApiModelProperty("营运资本变动")
    private String operationCapitalChange;

    @ApiModelProperty("其他非现金调整")
    private String otherNonCashAdjustments;

    @ApiModelProperty("经营活动产生的现金流量净额")
    private String operatingActivitiesCashFlow;

    @ApiModelProperty("出售固定资产收到的现金")
    private String saleFixedAssetsReceivedCash;

    @ApiModelProperty("减：资本性支出")
    private String capitalExpenditure;

    @ApiModelProperty("投资减少")
    private String lessInvestment;

    @ApiModelProperty("减：投资增加")
    private String increasedInvestment;

    @ApiModelProperty("其他投资活动产生的现金流量净额")
    private String otherInvestingActivitiesCashFlow;

    @ApiModelProperty("投资活动产生的现金流量净额")
    private String investingActivitiesCashFlow;

    @ApiModelProperty("债务增加")
    private String debtIncreases;

    @ApiModelProperty("减：债务减少")
    private String debtReduction;

    @ApiModelProperty("股本增加")
    private String increaseCapital;

    @ApiModelProperty("减：股本减少")
    private String debtCapital;

    @ApiModelProperty("支付的股利合计")
    private String totalDividendsPaid;

    @ApiModelProperty("其他筹资活动产生的现金流量净额")
    private String otherFinancingActivitiesCashFlow;

    @ApiModelProperty("筹资活动产生的现金流量净额")
    private String financingActivitiesCashFlow;

    @ApiModelProperty("汇率变动影响")
    private String rateChangesImpact;

    @ApiModelProperty("其他现金流量调整")
    private String otherCashFlowAdjustments;

    @ApiModelProperty("现金及现金等价物净增加额")
    private String cashEquivalentsIncreaseAmount;

    @ApiModelProperty("现金及现金等价物期初余额")
    private String cashEquivalentsOpeningBalance;

    @ApiModelProperty("现金及现金等价物期末余额")
    private String cashEquivalentsClosingBalance;

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
