package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:利润(excel A股)
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 16:42
 */
@Getter
@Setter
public class RaProjectExcelProfitResponseVO implements Serializable {
    private static final long serialVersionUID = -7220236957747312657L;

    @ApiModelProperty("报告期")
    private String year;

    @ApiModelProperty("报表类型")
    private String type;

    @ApiModelProperty("营业总收入")
    private String totalOperatingIncome;

    @ApiModelProperty("营业收入")
    private String operatingIncome;

    @ApiModelProperty("其他类金融业务收入")
    private String otherFnncIncome;

    @ApiModelProperty("利息收入")
    private String interestIncome;

    @ApiModelProperty("已赚保费")
    private String premiumEarned;

    @ApiModelProperty("手续费及佣金收入")
    private String feeCommissionIncome;

    @ApiModelProperty("营业总成本")
    private String totalOperatingCost;

    @ApiModelProperty("营业成本")
    private String operatingCost;

    @ApiModelProperty("税金及附加")
    private String taxesSurcharges;

    @ApiModelProperty("销售费用")
    private String salesFee;

    @ApiModelProperty("管理费用")
    private String manageFee;

    @ApiModelProperty("研发费用")
    private String radCost;

    @ApiModelProperty("财务费用")
    private String financingExpenses;

    @ApiModelProperty("其中：利息费用")
    private String interestExpenseFee;

    @ApiModelProperty("减：利息收入")
    private String interestIncomeFee;

    @ApiModelProperty("其他业务成本(金融类)")
    private String otherBusinessCostsFnnc;

    @ApiModelProperty("利息支出")
    private String interestExpense;

    @ApiModelProperty("手续费及佣金支出")
    private String feesCommissionsExpenses;

    @ApiModelProperty("退保金")
    private String surrender;

    @ApiModelProperty("赔付支出净额")
    private String netPayout;

    @ApiModelProperty("提取保险合同准备金净额")
    private String withdrawalOfIcr;

    @ApiModelProperty("保单红利支出")
    private String dividendPaymentPolicy;

    @ApiModelProperty("分保费用")
    private String reinsuranceCosts;

    @ApiModelProperty("加：其他收益")
    private String otherIncome;

    @ApiModelProperty("投资净收益")
    private String netInvestmentIncome;

    @ApiModelProperty("其中：对联营企业和合营企业的投资收益")
    private String investmentIncomeFromAjv;

    @ApiModelProperty("以摊余成本计量的金融资产终止确认收益")
    private String dereGainsFromFam;

    @ApiModelProperty("净敞口套期收益")
    private String exposureHedgingGain;

    @ApiModelProperty("公允价值变动净收益")
    private String incomeFromCifv;

    @ApiModelProperty("资产减值损失")
    private String assetImpairmentLoss;

    @ApiModelProperty("信用减值损失")
    private String creditImpairmentLoss;

    @ApiModelProperty("资产处置收益")
    private String assetDisposalIncome;

    @ApiModelProperty("汇兑净收益")
    private String foreignExchangeGain;

    @ApiModelProperty("加：营业利润差额(特殊报表科目)")
    private String operProfitDiffReport;

    @ApiModelProperty("营业利润差额(合计平衡项目)")
    private String operProfitDiffBalance;

    @ApiModelProperty("营业利润")
    private String operatingProfit;

    @ApiModelProperty("加：营业外收入")
    private String nonOperatingIncome;

    @ApiModelProperty("减：营业外支出")
    private String nonOperatingExpenses;

    @ApiModelProperty("其中：非流动资产处置净损失")
    private String lossOnDnca;

    @ApiModelProperty("加：利润总额差额(特殊报表科目)")
    private String totalProfitDiffReport;

    @ApiModelProperty("利润总额差额(合计平衡项目)")
    private String totalProfitDiffBalance;

    @ApiModelProperty("利润总额")
    private String totalProfit;

    @ApiModelProperty("减：所得税")
    private String incomeTax;

    @ApiModelProperty("加：未确认的投资损失")
    private String unrecoInvestmentLosses;

    @ApiModelProperty("加：净利润差额(特殊报表科目)")
    private String profitDiffReport;

    @ApiModelProperty("净利润差额(合计平衡项目)")
    private String profitDiffBalance;

    @ApiModelProperty("净利润")
    private String netProfit;

    @ApiModelProperty("持续经营净利润")
    private String profitFromCo;

    @ApiModelProperty("终止经营净利润")
    private String profitFromDo;

    @ApiModelProperty("减：少数股东损益")
    private String minorityHolderProfitLoss;

    @ApiModelProperty("归属于母公司所有者的净利润")
    private String profitsBelongPco;

    @ApiModelProperty("加：其他综合收益")
    private String otherComprehensiveIncome;

    @ApiModelProperty("综合收益总额")
    private String totalComprehensiveIncome;

    @ApiModelProperty("减：归属于少数股东的综合收益总额")
    private String totalCompIncomeAttrToMs;

    @ApiModelProperty("归属于母公司普通股东综合收益总额")
    private String totalCompIncomeAttrToOsopc;

    @ApiModelProperty("基本每股收益")
    private String basicEps;

    @ApiModelProperty("稀释每股收益")
    private String dltEarningsPerShare;

    @ApiModelProperty("显示币种")
    private String displayCurrency;

    @ApiModelProperty("原始币种")
    private String originalCurrency;

    @ApiModelProperty("转换汇率")
    private String convertExchangeRate;

    @ApiModelProperty("利率类型")
    private String rateType;

    @ApiModelProperty("利率")
    private String taxRate;

    @ApiModelProperty("利率说明")
    private String taxRateDesc;

    @ApiModelProperty("审计意见(境内)")
    private String auditOpinionsInner;

    @ApiModelProperty("审计意见(境外)")
    private String auditOpinionsOut;

    @ApiModelProperty("调整原因")
    private String adjustmentReason;

    @ApiModelProperty("调整说明")
    private String adjustmentDesc;

    @ApiModelProperty("公告日期")
    private String announcementDate;

    @ApiModelProperty("数据来源")
    private String dataSource;
}
