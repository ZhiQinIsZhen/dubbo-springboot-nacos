package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:资产负债(excel A股)
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 16:42
 */
@Getter
@Setter
public class RaProjectFinancialSheetShExcelResponseVO implements Serializable {
    private static final long serialVersionUID = -8056775654673200102L;

    @ApiModelProperty("报告期")
    private String year;

    @ApiModelProperty("报表类型")
    private String type;

    @ApiModelProperty("货币资金")
    private String moneyFunds;

    @ApiModelProperty("交易性金融资产")
    private String tradableFnnclAssets;

    @ApiModelProperty("衍生金融资产")
    private String derivativeFinancialAssets;

    @ApiModelProperty("应收票据及应收账款")
    private String accountBillsReceivable;

    @ApiModelProperty("应收票据")
    private String billsReceivable;

    @ApiModelProperty("应收账款")
    private String accountReceivable;

    @ApiModelProperty("应收款项融资")
    private String receivablesFinancing;

    @ApiModelProperty("预付款项")
    private String prepayments;

    @ApiModelProperty("其他应收款(合计)")
    private String totalOthrReceivables;

    @ApiModelProperty("应收股利")
    private String dividendsReceivable;

    @ApiModelProperty("应收利息")
    private String interestReceivable;

    @ApiModelProperty("其他应收款")
    private String othrReceivables;

    @ApiModelProperty("买入返售金融资产")
    private String repuResaleFinancialAssets;

    @ApiModelProperty("存货")
    private String stock;

    @ApiModelProperty("其中：消耗性生物资产")
    private String consumableBiologicalAssets;

    @ApiModelProperty("合同资产")
    private String contractAssets;

    @ApiModelProperty("划分为持有待售的资产")
    private String assetsHeldForSale;

    @ApiModelProperty("一年内到期的非流动资产")
    private String oneYearNoncurrentAssets;

    @ApiModelProperty("待摊费用")
    private String prepaidExpenses;

    @ApiModelProperty("其他流动资产")
    private String othrCurrentAssets;

    @ApiModelProperty("其他金融类流动资产")
    private String othrFnnclCurrentAssets;

    @ApiModelProperty("结算备付金")
    private String settlementProvisions;

    @ApiModelProperty("拆出资金")
    private String loanFunds;

    @ApiModelProperty("应收保费")
    private String premiumsReceivable;

    @ApiModelProperty("应收分保账款")
    private String reinAccountsReceivable;

    @ApiModelProperty("应收分保合同准备金")
    private String reinContractReservesReceivable;

    @ApiModelProperty("流动资产差额(特殊报表科目)")
    private String currentAssetsDifferenceStatement;

    @ApiModelProperty("流动资产差额(合计平衡项目)")
    private String currentAssetsDifferenceBalance;

    @ApiModelProperty("流动资产合计")
    private String totalCurrentAssets;

    @ApiModelProperty("发放贷款及垫款")
    private String loansAdvances;

    @ApiModelProperty("以公允价值且其变动计入其他综合收益的金融资产")
    private String othrCompIncomeFnnclAssetsAtFv;

    @ApiModelProperty("以摊余成本计量的金融资产")
    private String fnnclAssetsAtAc;

    @ApiModelProperty("债权投资")
    private String debtInvestment;

    @ApiModelProperty("其他债权投资")
    private String othrDebtInvestment;

    @ApiModelProperty("可供出售金融资产")
    private String availableSaleFnnclAssets;

    @ApiModelProperty("其他权益工具投资")
    private String othrEquityInstruments;

    @ApiModelProperty("持有至到期投资")
    private String heldMaturityInvestments;

    @ApiModelProperty("其他非流动金融资产")
    private String othrNoncurrentFnnclAssets;

    @ApiModelProperty("长期应收款")
    private String longTermReceivables;

    @ApiModelProperty("长期股权投资")
    private String longTermEquityInvestment;

    @ApiModelProperty("投资性房地产")
    private String investmentRealEstate;

    @ApiModelProperty("固定资产(合计)")
    private String totalFixedAssets;

    @ApiModelProperty("固定资产")
    private String fixedAssets;

    @ApiModelProperty("固定资产清理")
    private String fixedAssetsLiquidation;

    @ApiModelProperty("在建工程(合计)")
    private String totalProgressConstruction;

    @ApiModelProperty("在建工程")
    private String progressConstruction;

    @ApiModelProperty("工程物资")
    private String engineerMaterial;

    @ApiModelProperty("生产性生物资产")
    private String productiveBiologicalAssets;

    @ApiModelProperty("油气资产")
    private String oilGasAssets;

    @ApiModelProperty("使用权资产")
    private String useRightAssets;

    @ApiModelProperty("无形资产")
    private String intangibleAssets;

    @ApiModelProperty("开发支出")
    private String developmentExpenditure;

    @ApiModelProperty("商誉")
    private String goodwill;

    @ApiModelProperty("长期待摊费用")
    private String longTermPrepaidExpenses;

    @ApiModelProperty("递延所得税资产")
    private String deferredTaxAssets;

    @ApiModelProperty("其他非流动资产")
    private String othrNonCurrentAssets;

    @ApiModelProperty("流动资产差额(特殊报表科目)")
    private String noncurrentAssetsDifferenceStatement;

    @ApiModelProperty("流动资产差额(合计平衡项目)")
    private String noncurrentAssetsDifferenceBalance;

    @ApiModelProperty("非流动资产合计")
    private String totalNoncurrentAssets;

    @ApiModelProperty("资产差额(特殊报表科目)")
    private String assetsDifferenceStatement;

    @ApiModelProperty("资产差额(合计平衡项目)")
    private String assetsDifferenceBalance;

    @ApiModelProperty("资产总计")
    private String totalAssets;

    @ApiModelProperty("短期借款")
    private String shortTermLoan;

    @ApiModelProperty("交易性金融负债")
    private String heldTradingFnnclLiab;

    @ApiModelProperty("衍生金融负债")
    private String derivativeFnnclLiab;

    @ApiModelProperty("应付账款及票据")
    private String accountBillsPayable;

    @ApiModelProperty("应付账款")
    private String accountPayable;

    @ApiModelProperty("应付票据")
    private String billsPayable;

    @ApiModelProperty("预收款项")
    private String advancePayment;

    @ApiModelProperty("合同负债")
    private String contractLiabilities;

    @ApiModelProperty("应付手续费及佣金")
    private String feesCommissions;

    @ApiModelProperty("应付职工薪酬")
    private String payrollPayable;

    @ApiModelProperty("应交税费")
    private String taxesPayable;

    @ApiModelProperty("其他应付款(合计)")
    private String totalOtherPayable;

    @ApiModelProperty("应付利息")
    private String interestPayable;

    @ApiModelProperty("应付股利")
    private String dividendsPayable;

    @ApiModelProperty("其他应付款")
    private String otherPayable;

    @ApiModelProperty("划分为持有待售的负债")
    private String heldSaleLiability;

    @ApiModelProperty("一年内到期的非流动负债")
    private String oneYearNoncurrentLiab;

    @ApiModelProperty("预提费用")
    private String accruedCharges;

    @ApiModelProperty("递延收益-流动负债")
    private String defeIncomeSubCurrentLiab;

    @ApiModelProperty("应付短期债券")
    private String shortTermBondsPayable;

    @ApiModelProperty("其他流动负债")
    private String otherNoncurrentLiab;

    @ApiModelProperty("其他金融类流动负债")
    private String otherFnnclNoncurrentLiab;

    @ApiModelProperty("向中央银行借款")
    private String borrowingFromCentralBank;

    @ApiModelProperty("吸收存款及同业存放")
    private String depositsDeposits;

    @ApiModelProperty("拆入资金")
    private String borrowedFunds;

    @ApiModelProperty("卖出回购金融资产款")
    private String fnnclAssetsSoldUnderRepu;

    @ApiModelProperty("应付分保账款")
    private String reinAccountsPayable;

    @ApiModelProperty("保险合同准备金")
    private String insuranceContractReserve;

    @ApiModelProperty("代理买卖证券款")
    private String agentBuySellSecurities;

    @ApiModelProperty("代理承销证券款")
    private String agentUnderwriteSecurities;

    @ApiModelProperty("流动负债差额(特殊报表科目)")
    private String currentLiabDifferenceStatement;

    @ApiModelProperty("流动负债差额(合计平衡项目)")
    private String currentLiabDifferenceBalance;

    @ApiModelProperty("流动负债合计")
    private String totalCurrentLiab;

    @ApiModelProperty("长期借款")
    private String longTermLoan;

    @ApiModelProperty("应付债券")
    private String bondsPayable;

    @ApiModelProperty("租赁负债")
    private String leaseLiability;

    @ApiModelProperty("长期应付款(合计)")
    private String totalLongTermPayable;

    @ApiModelProperty("长期应付款")
    private String longTermPayable;

    @ApiModelProperty("专项应付款")
    private String specialPayable;

    @ApiModelProperty("长期应付职工薪酬")
    private String longTermEmplCompPayable;

    @ApiModelProperty("预计负债")
    private String estimatedLiabilities;

    @ApiModelProperty("递延所得税负债")
    private String deferredTaxLiabilities;

    @ApiModelProperty("递延收益-非流动负债")
    private String defeIncomeSubNoncurrentLiab;

    @ApiModelProperty("其他非流动负债")
    private String othrNonCurrentLiab;

    @ApiModelProperty("非流动负债差额(特殊报表科目)")
    private String noncurrentLiabDifferenceStatement;

    @ApiModelProperty("非流动负债差额(合计平衡项目)")
    private String noncurrentLiabDifferenceBalance;

    @ApiModelProperty("非流动负债合计")
    private String totalOthrNonCurrentLiab;

    @ApiModelProperty("负债差额(特殊报表科目)")
    private String liabDifferenceStatement;

    @ApiModelProperty("负债差额(合计平衡项目)")
    private String liabDifferenceBalance;

    @ApiModelProperty("负债合计")
    private String totalLiab;

    @ApiModelProperty("实收资本(或股本)")
    private String paidUpCapital;

    @ApiModelProperty("其它权益工具")
    private String otherEquityInstruments;

    @ApiModelProperty("其中：优先股")
    private String preferredStock;

    @ApiModelProperty("永续债")
    private String perpetualBond;

    @ApiModelProperty("资本公积金")
    private String capitalReserve;

    @ApiModelProperty("减：库存股")
    private String treasuryStocks;

    @ApiModelProperty("其它综合收益")
    private String otherComprehensiveIncome;

    @ApiModelProperty("专项储备")
    private String specialReserves;

    @ApiModelProperty("盈余公积金")
    private String surplusReserve;

    @ApiModelProperty("一般风险准备")
    private String generalRiskPreparation;

    @ApiModelProperty("未分配利润")
    private String undistributedProfit;

    @ApiModelProperty("外币报表折算差额")
    private String tranDiffForeignCurrencyStat;

    @ApiModelProperty("未确认的投资损失")
    private String unrecdInvestmentLosses;

    @ApiModelProperty("股东权益差额(特殊报表科目)")
    private String holdersEquityDifferenceStatement;

    @ApiModelProperty("股权权益差额(合计平衡项目)")
    private String holdersEquityDifferenceBalance;

    @ApiModelProperty("归属于母公司所有者权益合计")
    private String parentCompanyEquity;

    @ApiModelProperty("少数股东权益")
    private String minorityInterests;

    @ApiModelProperty("所有者权益合计")
    private String totalOwnerEquity;

    @ApiModelProperty("负债及股东权益差额(特殊报表科目)")
    private String liabHoldersEquityDifferenceStatement;

    @ApiModelProperty("负债及股东权益差额(合计平衡项目)")
    private String liabHoldersEquityDifferenceBalance;

    @ApiModelProperty("负债和所有者权益总计")
    private String totalLiabOwnerEquity;

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
