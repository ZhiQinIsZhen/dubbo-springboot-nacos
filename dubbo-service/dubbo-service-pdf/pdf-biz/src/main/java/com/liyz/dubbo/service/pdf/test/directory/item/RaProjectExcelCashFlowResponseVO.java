package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:现金流A股
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 16:42
 */
@Getter
@Setter
public class RaProjectExcelCashFlowResponseVO implements Serializable {
    private static final long serialVersionUID = -7724961813614297345L;

    @ApiModelProperty("报告期")
    private String year;

    @ApiModelProperty("报表类型")
    private String type;

    @ApiModelProperty("销售商品、提供劳务收到的现金")
    private String salesGoodsRenderServicesCash;

    @ApiModelProperty("收到的税费返还")
    private String taxRefund;

    @ApiModelProperty("收到其他与经营活动有关的现金")
    private String otherOperActiCashReceived;

    @ApiModelProperty("经营活动现金流入(金融类)")
    private String cashInflowFromOperActiFnnc;

    @ApiModelProperty("保户储金净增加额")
    private String increasePolicySavings;

    @ApiModelProperty("客户存款和同业存放款项净增加额")
    private String increaseCustDepoInteDepo;

    @ApiModelProperty("向中央银行借款净增加额")
    private String increaseBorrCentralBank;

    @ApiModelProperty("向其他金融机构拆入资金净增加额")
    private String increaseFundsBorrOtherFnncInst;

    @ApiModelProperty("收取利息和手续费净增加额")
    private String increaseInterestFeesCharged;

    @ApiModelProperty("收到原保险合同保费取得的现金")
    private String cashReceivedFromOicp;

    @ApiModelProperty("收到再保业务现金净额")
    private String cashReceivedFromRo;

    @ApiModelProperty("处置交易性金融资产净增加额")
    private String increaseInDfah;

    @ApiModelProperty("拆入资金净增加额")
    private String increaseInBorrowedFunds;

    @ApiModelProperty("回购业务资金净增加额")
    private String increaseInRbf;

    @ApiModelProperty("经营活动现金流入差额(特殊报表科目)")
    private String cashInflowDiffFromOaStatement;

    @ApiModelProperty("经营活动现金流入差额(合计平衡项目)")
    private String cashInflowDiffFromOaBalance;

    @ApiModelProperty("经营活动现金流入小计")
    private String totalCashInflow;

    @ApiModelProperty("购买商品、接受劳务支付的现金")
    private String payGoodsRenderServicesCash;

    @ApiModelProperty("支付给职工以及为职工支付的现金")
    private String payCashForEmployees;

    @ApiModelProperty("支付的各项税费")
    private String taxesPaid;

    @ApiModelProperty("支付其他与经营活动有关的现金")
    private String payOtherCashToOa;

    @ApiModelProperty("经营活动现金流出(金融类)")
    private String outflowCashFromOaFnnc;

    @ApiModelProperty("客户贷款及垫款净增加额")
    private String increaseInLaToCust;

    @ApiModelProperty("存放央行和同业款项净增加额")
    private String increaseInDcbb;

    @ApiModelProperty("支付原保险合同赔付款项的现金")
    private String payCashOicc;

    @ApiModelProperty("支付手续费的现金")
    private String CashForHandleFees;

    @ApiModelProperty("支付保单红利的现金")
    private String payCashToPd;

    @ApiModelProperty("经营活动现金流出差额(特殊报表科目)")
    private String cashOutflowDiffFromOaStatement;

    @ApiModelProperty("经营活动现金流出差额(合计平衡项目)")
    private String cashOutflowDiffFromOaBalance;

    @ApiModelProperty("经营活动现金流出小计")
    private String totalCashOutflow;

    @ApiModelProperty("经营活动产生的现金流量净额差额(合计平衡项目)")
    private String cashFlowDiffFromOaBalance;

    @ApiModelProperty("经营活动产生的现金流量净额")
    private String cashFlowFromOa;

    @ApiModelProperty("收回投资收到的现金")
    private String cashBackOnInvestment;

    @ApiModelProperty("取得投资收益收到的现金")
    private String cashReceivedFromIi;

    @ApiModelProperty("处置固定资产、无形资产和其他长期资产收回的现金净额")
    private String cashReceivedFromDfiolta;

    @ApiModelProperty("处置子公司及其他营业单位收到的现金净额")
    private String cashReceivedFromDsobu;

    @ApiModelProperty("收到其他与投资活动有关的现金")
    private String otherCashReceivedToIa;

    @ApiModelProperty("投资活动现金流入差额(特殊报表科目)")
    private String cashInflowsDiffFromIaStatement;

    @ApiModelProperty("投资活动现金流入差额(合计平衡项目)")
    private String cashInflowsDiffFromIaBalance;

    @ApiModelProperty("投资活动现金流入小计")
    private String totalCashInflowsFromIa;

    @ApiModelProperty("购建固定资产、无形资产和其他长期资产支付的现金")
    private String cashPaidForPcfaIaLta;

    @ApiModelProperty("投资支付的现金")
    private String cashInvestment;

    @ApiModelProperty("取得子公司及其他营业单位支付的现金净额")
    private String cashPaidToAsobu;

    @ApiModelProperty("支付其他与投资活动有关的现金")
    private String otherCashPaidToIa;

    @ApiModelProperty("投资活动现金流出差额(特殊报表科目)")
    private String cashOutflowsDiffFromIaStatement;

    @ApiModelProperty("投资活动现金流出差额(合计平衡项目)")
    private String cashOutflowsDiffFromIaBalance;

    @ApiModelProperty("投资活动现金流出小计")
    private String totalCashOutflowsFromIa;

    @ApiModelProperty("投资活动产生的现金流量净额差额(合计平衡项目)")
    private String cashFlowDiffFromIaBalance;

    @ApiModelProperty("投资活动产生的现金流量净额")
    private String cashFlowFromIa;

    @ApiModelProperty("吸收投资收到的现金")
    private String cashReceivedFromInvestments;

    @ApiModelProperty("其中：子公司吸收少数股东投资收到的现金")
    private String cashReceivedFromMsi;

    @ApiModelProperty("取得借款收到的现金")
    private String cashReceivedFromBorrowing;

    @ApiModelProperty("收到其他与筹资活动有关的现金")
    private String cashReceivedFromOfa;

    @ApiModelProperty("发行债券收到的现金")
    private String cashReceivedFromBi;

    @ApiModelProperty("筹资活动现金流入差额(特殊报表科目)")
    private String cashInflowDiffFromFaStatement;

    @ApiModelProperty("筹资活动现金流入差额(合计平衡项目)")
    private String cashInflowDiffFromFaBalance;

    @ApiModelProperty("筹资活动现金流入小计")
    private String totalCashInflowFromFa;

    @ApiModelProperty("偿还债务支付的现金")
    private String cashPaidForDr;

    @ApiModelProperty("分配股利、利润或偿付利息支付的现金")
    private String cashPaidToDdpri;

    @ApiModelProperty("其中：子公司支付给少数股东的股利、利润")
    private String dpPaidByStms;

    @ApiModelProperty("支付其他与筹资活动有关的现金")
    private String payOtherCashToFa;

    @ApiModelProperty("筹资活动现金流出差额(特殊报表科目)")
    private String cashOutflowDiffFromFaStatement;

    @ApiModelProperty("筹资活动现金流出差额(合计平衡项目)")
    private String cashOutflowDiffFromFaBalance;

    @ApiModelProperty("筹资活动现金流出小计")
    private String totalCashOutflowFromFa;

    @ApiModelProperty("筹资活动产生的现金流量净额差额(合计平衡项目)")
    private String cashFlowDiffFromFaBalance;

    @ApiModelProperty("筹资活动产生的现金流量净额")
    private String financingActivitiesCashFlow;

    @ApiModelProperty("直接法-现金及现金等价物净增加额差额(特殊报表科目)")
    private String diffIncreaseInCceStatement;

    @ApiModelProperty("直接法-现金及现金等价物净增加额差额(合计平衡项目)")
    private String diffIncreaseInCceBalance;

    @ApiModelProperty("现金及现金等价物净增加额")
    private String increaseInCce;

    @ApiModelProperty("期初现金及现金等价物余额")
    private String beginBalanceOfCce;

    @ApiModelProperty("期末现金及现金等价物余额")
    private String endBalanceOfCce;

    @ApiModelProperty("净利润")
    private String netProfit;

    @ApiModelProperty("加：资产减值准备")
    private String provisionForAi;

    @ApiModelProperty("信用减值损失")
    private String creditImpairmentLoss;

    @ApiModelProperty("固定资产折旧、油气资产折耗、生产性生物资产折旧")
    private String deprOfFaDoPba;

    @ApiModelProperty("无形资产摊销")
    private String amorOfIa;

    @ApiModelProperty("使用权资产折旧")
    private String deprOfRua;

    @ApiModelProperty("长期待摊费用摊销")
    private String amorOfLtde;

    @ApiModelProperty("待摊费用减少")
    private String decrInPe;

    @ApiModelProperty("预提费用增加")
    private String increaseInAe;

    @ApiModelProperty("处置固定资产、无形资产和其他长期资产的损失")
    private String lossOnDfaIaOlta;

    @ApiModelProperty("固定资产报废损失")
    private String fixedAssetRl;

    @ApiModelProperty("公允价值变动损失")
    private String lossFromChangesInFv;

    @ApiModelProperty("财务费用")
    private String financialExpenses;

    @ApiModelProperty("投资损失")
    private String investmentLoss;

    @ApiModelProperty("递延所得税资产减少")
    private String decreaseInDta;

    @ApiModelProperty("递延所得税负债增加")
    private String increaseInDtl;

    @ApiModelProperty("存货的减少")
    private String inventoryReduction;

    @ApiModelProperty("经营性应收项目的减少")
    private String decreaseInOr;

    @ApiModelProperty("经营性应付项目的增加")
    private String increaseInOp;

    @ApiModelProperty("未确认的投资损失")
    private String unrecoInvestmentLosses;

    @ApiModelProperty("其他")
    private String other;

    @ApiModelProperty("间接法-经营活动现金流量净额差额(特殊报表科目)")
    private String ccashFlowDiffFromOaStatement;

    @ApiModelProperty("间接法-经营活动现金流量净额差额(合计平衡项目)")
    private String ccashFlowDiffFromOaBalance;

    @ApiModelProperty("间接法-经营活动产生的现金流量净额")
    private String ccashFlowFromOa;

    @ApiModelProperty("债务转为资本")
    private String conversionOfDic;

    @ApiModelProperty("一年内到期的可转换公司债券")
    private String convCorBondsOneYear;

    @ApiModelProperty("融资租入固定资产")
    private String financeLeasedFa;

    @ApiModelProperty("现金的期末余额")
    private String closeBalanceOfCash;

    @ApiModelProperty("减：现金的期初余额")
    private String openBalanceOfCash;

    @ApiModelProperty("加：现金等价物的期末余额")
    private String closeBalanceOfCe;

    @ApiModelProperty("减：现金等价物的期初余额")
    private String openBalanceOfCe;

    @ApiModelProperty("加：间接法-现金净增加额差额(特殊报表科目)")
    private String increaseDiffInCashStatement;

    @ApiModelProperty("间接法-现金净增加额差额(合计平衡项目)")
    private String increaseDiffInCashBalance;

    @ApiModelProperty("间接法-现金及现金等价物净增加额")
    private String increaseInCceIm;

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
