package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2023/2/22 16:03
 */
@Getter
@Setter
public class RaProjectWdCashFlowResponseVO implements Serializable {
    private static final long serialVersionUID = -1758992958566085969L;

    @ApiModelProperty("年份")
    private String showYear;

    @ApiModelProperty("财报类别 1：年报；2：中报 ；3：一季度；4:三季度")
    private Integer type;

    @ApiModelProperty("销售商品、提供劳务收到的现金")
    private String cashRecpSgAndRs;

    /**
     * 收到的税费返还
     */
    @ApiModelProperty("收到的税费返还")
    private String recpTaxRends;

    /**
     * 收到其他与经营活动有关的现金
     */
    @ApiModelProperty("收到其他与经营活动有关的现金")
    private String otherCashRecpRalOperAct;


    /**
     * 经营活动现金流入（金融类）
     *
     * @return 保户储金净增加额 + 客户存款和同业存放款项净增加额 + 向中央银行借款净增加额 + 向其他金融机构拆入资金净增加额 + 收取利息和手续费净增加额 + 收到原保险合同保费取得的现金
     * + 收到再保业务现金净额 + 处置交易性金融资产净增加额 + 拆入资金净增加额 + 回购业务资金净增加额
     */
    @ApiModelProperty("经营活动现金流入（金融类）")
    public String cashInflowFromOperatingActivities;


    /**
     * 保户储金净增加额
     */
    @ApiModelProperty("保户储金净增加额")
    private String netIncrInsuredDep;

    /**
     * 客户存款和同业存放款项净增加额
     */
    @ApiModelProperty("客户存款和同业存放款项净增加额")
    private String netIncrDepCob;

    /**
     * 向中央银行借款净增加额
     */
    @ApiModelProperty("向中央银行借款净增加额")
    private String netIncrLoansCentralBank;

    /**
     * 向其他金融机构拆入资金净增加额
     */
    @ApiModelProperty("向其他金融机构拆入资金净增加额")
    private String netIncrFundBorrOfi;

    /**
     * 收取利息和手续费净增加额
     */
    @ApiModelProperty("收取利息和手续费净增加额")
    private String netIncrIntHandlingChrg;

    /**
     * 收到原保险合同保费取得的现金
     */
    @ApiModelProperty("收到原保险合同保费取得的现金")
    private String cashRecpPremOrigInco;

    /**
     * 收到再保业务现金净额
     */
    @ApiModelProperty("收到再保业务现金净额")
    private String netCashReceivedReinsuBus;

    /**
     * 处置交易性金融资产净增加额
     */
    @ApiModelProperty("处置交易性金融资产净增加额")
    private String netIncrDispTfa;

    /**
     * 拆入资金净增加额
     */
    @ApiModelProperty("拆入资金净增加额")
    private String netIncrLoansOtherBank;

    /**
     * 回购业务资金净增加额
     */
    @ApiModelProperty("回购业务资金净增加额")
    private String netIncrRepurchBusFund;

    /**
     * 经营活动现金流入差额（特殊报表科目）
     */
    @ApiModelProperty("经营活动现金流入差额（特殊报表科目）")
    private String cashInflowsOperActGap;

    /**
     * 经营活动现金流入差额（合计平衡项目）
     */
    @ApiModelProperty("经营活动现金流入差额（合计平衡项目）")
    private String cashInflowsOperActNetting;

    /**
     * 经营活动现金流入小计
     */
    @ApiModelProperty("经营活动现金流入小计")
    private String stotCashInflowsOperAct;

    /**
     * 购买商品、接受劳务支付的现金
     */
    @ApiModelProperty("购买商品、接受劳务支付的现金")
    private String cashPayGoodsPurchServRec;

    /**
     * 支付给职工以及为职工支付的现金
     */
    @ApiModelProperty("支付给职工以及为职工支付的现金")
    private String cashPayBehEmpl;

    /**
     * 支付的各项税费
     */
    @ApiModelProperty("支付的各项税费")
    private String payAllTypTax;

    /**
     * 支付其他与经营活动有关的现金
     */
    @ApiModelProperty("支付其他与经营活动有关的现金")
    private String otherCashPayRalOperAct;


    /**
     * 经营活动现金流出（金融类）
     *
     * @return 客户贷款及垫款净增加额 + 存放央行和同业款项净增加额 + 支付原保险合同赔付款项的现金 + 支付手续费的现金 + 支付保单红利的现金
     */
    @ApiModelProperty("经营活动现金流出（金融类）")
    private String cashOutflowFromOperatingActivities;

    /**
     * 客户贷款及垫款净增加额
     */
    @ApiModelProperty("客户贷款及垫款净增加额")
    private String netIncrClientsLoanAdv;

    /**
     * 存放央行和同业款项净增加额
     */
    @ApiModelProperty("存放央行和同业款项净增加额")
    private String netIncrDepCbob;

    /**
     * 支付原保险合同赔付款项的现金
     */
    @ApiModelProperty("支付原保险合同赔付款项的现金")
    private String cashPayClaimsOrigInco;

    /**
     * 支付手续费的现金
     */
    @ApiModelProperty("支付手续费的现金")
    private String handlingChrgPaid;

    /**
     * 支付保单红利的现金
     */
    @ApiModelProperty("支付保单红利的现金")
    private String commInsurPlcyPaid;

    /**
     * 经营活动现金流出差额（特殊报表科目）
     */
    @ApiModelProperty("经营活动现金流出差额（特殊报表科目）")
    private String cashOutflowsOperActGap;

    /**
     * 经营活动现金流出差额（合计平衡项目）
     */
    @ApiModelProperty("cash_outflows_oper_act_netting")
    private String cashOutflowsOperActNetting;

    /**
     * 经营活动现金流出小计
     */
    @ApiModelProperty("经营活动现金流出小计")
    private String stotCashOutflowsOperAct;

    /**
     * 经营活动产生的现金流量净额差额（合计平衡项目）
     */
    @ApiModelProperty("经营活动产生的现金流量净额差额（合计平衡项目）")
    private String cfOperActNetting;

    /**
     * 经营活动产生的现金流量净额
     */
    @ApiModelProperty("经营活动产生的现金流量净额")
    private String netCashFlowsOperAct;

    /**
     * 收回投资收到的现金
     */
    @ApiModelProperty("收回投资收到的现金")
    private String cashRecpDispWithdrwlInvest;

    /**
     * 取得投资收益收到的现金
     */
    @ApiModelProperty("取得投资收益收到的现金")
    private String cashRecpReturnInvest;

    /**
     * 处置固定资产、无形资产和其他长期资产收回的现金净额
     */
    @ApiModelProperty("处置固定资产、无形资产和其他长期资产收回的现金净额")
    private String netCashRecpDispFiolta;

    /**
     * 处置子公司及其他营业单位收到的现金净额
     */
    @ApiModelProperty("处置子公司及其他营业单位收到的现金净额")
    private String netCashRecpDispSobu;

    /**
     * 收到其他与投资活动有关的现金
     */
    @ApiModelProperty("收到其他与投资活动有关的现金")
    private String otherCashRecpRalInvAct;

    /**
     * 投资活动现金流入差额（特殊报表科目）
     */
    @ApiModelProperty("投资活动现金流入差额（特殊报表科目）")
    private String cashInflowsInvActGap;

    /**
     * 投资活动现金流入差额（合计平衡项目）
     */
    @ApiModelProperty("投资活动现金流入差额（合计平衡项目）")
    private String cashInflowsInvActNetting;

    /**
     * 投资活动现金流入小计
     */
    @ApiModelProperty("投资活动现金流入小计")
    private String stotCashInflowsInvAct;

    /**
     * 购建固定资产、无形资产和其他长期资产支付的现金
     */
    @ApiModelProperty("购建固定资产、无形资产和其他长期资产支付的现金")
    private String cashPayAcqConstFiolta;

    /**
     * 投资支付的现金
     */
    @ApiModelProperty("投资支付的现金")
    private String cashPaidInvest;

    /**
     * 取得子公司及其他营业单位支付的现金净额
     */
    @ApiModelProperty("取得子公司及其他营业单位支付的现金净额")
    private String netCashPayAquisSobu;

    /**
     * 支付其他与投资活动有关的现金
     */
    @ApiModelProperty("支付其他与投资活动有关的现金")
    private String otherCashPayRalInvAct;

    /**
     * 投资活动现金流出差额（特殊报表科目）
     */
    @ApiModelProperty("投资活动现金流出差额（特殊报表科目）")
    private String cashOutflowsInvActGap;

    /**
     * 投资活动现金流出差额（合计平衡项目）
     */
    @ApiModelProperty("投资活动现金流出差额（合计平衡项目）")
    private String cashOutflowsInvActNetting;

    /**
     * 投资活动现金流出小计
     */
    @ApiModelProperty("投资活动现金流出小计")
    private String stotCashOutflowsInvAct;

    /**
     * 投资活动产生的现金流量净额差额（合计平衡项目）
     */
    @ApiModelProperty("投资活动产生的现金流量净额差额（合计平衡项目）")
    private String cfInvActNetting;

    /**
     * 投资活动产生的现金流量净额
     */
    @ApiModelProperty("投资活动产生的现金流量净额")
    private String netCashFlowsInvAct;

    /**
     * 吸收投资收到的现金
     */
    @ApiModelProperty("吸收投资收到的现金")
    private String cashRecpCapContrib;

    /**
     * 其中：子公司吸收少数股东投资收到的现金
     */
    @ApiModelProperty("其中：子公司吸收少数股东投资收到的现金")
    private String cashRecSaims;

    /**
     * 取得借款收到的现金
     */
    @ApiModelProperty("取得借款收到的现金")
    private String cashRecpBorrow;

    /**
     * 收到其他与筹资活动有关的现金
     */
    @ApiModelProperty("收到其他与筹资活动有关的现金")
    private String otherCashRecpRalFncAct;

    /**
     * 发行债券收到的现金
     */
    @ApiModelProperty("发行债券收到的现金")
    private String procIssueBonds;

    /**
     * 筹资活动现金流入差额（特殊报表科目）
     */
    @ApiModelProperty("筹资活动现金流入差额（特殊报表科目）")
    private String cashInflowsFncActGap;

    /**
     * 筹资活动现金流入差额（合计平衡项目）
     */
    @ApiModelProperty("筹资活动现金流入差额（合计平衡项目）")
    private String cashInflowsFncActNetting;

    /**
     * 筹资活动现金流入小计
     */
    @ApiModelProperty("筹资活动现金流入小计")
    private String stotCashInflowsFncAct;

    /**
     * 偿还债务支付的现金
     */
    @ApiModelProperty("偿还债务支付的现金")
    private String cashPrepayAmtBorr;

    /**
     * 分配股利、利润或偿付利息支付的现金
     */
    @ApiModelProperty("分配股利、利润或偿付利息支付的现金")
    private String cashPayDistDpcpIntExp;

    /**
     * 其中：子公司支付给少数股东的股利、利润
     */
    @ApiModelProperty("其中：子公司支付给少数股东的股利、利润")
    private String dvdProfitPaidScMs;

    /**
     * 支付其他与筹资活动有关的现金
     */
    @ApiModelProperty("支付其他与筹资活动有关的现金")
    private String otherCashPayRalFncAct;

    /**
     * 筹资活动现金流出差额（特殊报表科目）
     */
    @ApiModelProperty("筹资活动现金流出差额（特殊报表科目）")
    private String cashOutflowsFncActGap;

    /**
     * 筹资活动现金流出差额（合计平衡项目）
     */
    @ApiModelProperty("筹资活动现金流出差额（合计平衡项目）")
    private String cashOutflowsFncActNetting;

    /**
     * 筹资活动现金流出小计
     */
    @ApiModelProperty("筹资活动现金流出小计")
    private String stotCashOutflowsFncAct;

    /**
     * 筹资活动产生的现金流量净额差额（合计平衡项目）
     */
    @ApiModelProperty("筹资活动产生的现金流量净额差额（合计平衡项目）")
    private String cfFncActNetting;

    /**
     * 筹资活动产生的现金流量净额
     */
    @ApiModelProperty("筹资活动产生的现金流量净额")
    private String netCashFlowsFncAct;

    /**
     * 汇率变动对现金的影响
     */
    @ApiModelProperty("汇率变动对现金的影响")
    private String effFxFluCash;

    /**
     * 直接法-现金及现金等价物净增加额差额（特殊报表科目）
     */
    @ApiModelProperty("直接法-现金及现金等价物净增加额差额（特殊报表科目）")
    private String netIncrCashCashEquGap;

    /**
     * 直接法-现金及现金等价物净增加额差额（合计平衡项目）
     */
    @ApiModelProperty("直接法-现金及现金等价物净增加额差额（合计平衡项目）")
    private String netIncrCashCashEquNetting;

    /**
     * 现金及现金等价物净增加额
     */
    @ApiModelProperty("现金及现金等价物净增加额")
    private String netIncrCashCashEquDm;

    /**
     * 期初现金及现金等价物余额
     */
    @ApiModelProperty("期初现金及现金等价物余额")
    private String cashCashEquBegPeriod;

    /**
     * 期未现金及现金等价物余额
     */
    @ApiModelProperty("期未现金及现金等价物余额")
    private String cashCashEquEndPeriod;

    /**
     * 净利润
     */
    @ApiModelProperty("净利润")
    private String netProfitCs;

    /**
     * 加：资产减值准备
     */
    @ApiModelProperty("加：资产减值准备")
    private String provDeprAssets;

    /**
     * 信用减值损失
     */
    @ApiModelProperty("信用减值损失")
    private String creditImpairLoss2;

    /**
     * 固定资产折旧、油气资产折耗、生产性生物资产折引旧
     */
    @ApiModelProperty("固定资产折旧、油气资产折耗、生产性生物资产折引旧")
    private String deprFaCogaDpba;

    /**
     * 无形资产摊销
     */
    @ApiModelProperty("无形资产摊销")
    private String amortIntangAssets;

    /**
     * 使用权资产折旧
     */
    @ApiModelProperty("使用权资产折旧")
    private String deprePropRightUse;

    /**
     * 长期待摊费用摊销
     */
    @ApiModelProperty("长期待摊费用摊销")
    private String amortLtDeferredExp;

    /**
     * 待摊费用减少
     */
    @ApiModelProperty("待摊费用减少")
    private String decrDeferredExp;

    /**
     * 预提费用增加
     */
    @ApiModelProperty("预提费用增加")
    private String incrAccExp;

    /**
     * 处置固定资产、无形资产和其他长期资产的损失
     */
    @ApiModelProperty("处置固定资产、无形资产和其他长期资产的损失")
    private String lossDispFiolta;

    /**
     * 固定资产报废损失
     */
    @ApiModelProperty("固定资产报废损失")
    private String lossScrFa;

    /**
     * 公允价值变动损失
     */
    @ApiModelProperty("公允价值变动损失")
    private String lossFvChg;

    /**
     * 财务费用
     */
    @ApiModelProperty("财务费用")
    private String finExpCs;

    /**
     * 投资损失
     */
    @ApiModelProperty("投资损失")
    private String investLoss;

    /**
     * 递延所得税资产减少
     */
    @ApiModelProperty("递延所得税资产减少")
    private String decrDeferredIncTaxAssets;

    /**
     * 递延所得税负债增加
     */
    @ApiModelProperty("递延所得税负债增加")
    private String incrDeferredIncTaxLiab;

    /**
     * 存货的减少
     */
    @ApiModelProperty("存货的减少")
    private String decrInventories;

    /**
     * 经营性应收项目的减少
     */
    @ApiModelProperty("经营性应收项目的减少")
    private String decrOperPayable;

    /**
     * 经营性应付项目的增加
     */
    @ApiModelProperty("经营性应付项目的增加")
    private String incrOperPayable;

    /**
     * 未确认的投资损失
     */
    @ApiModelProperty("未确认的投资损失")
    private String unconfirmedInvestLossCs;

    /**
     * 其他
     */
    @ApiModelProperty("其他")
    private String others;

    /**
     * 间接法-经营活动现金流量净额差额（特殊报表科目）
     */
    @ApiModelProperty("间接法-经营活动现金流量净额差额（特殊报表科目）")
    private String imNetCashFlowsOperActGap;

    /**
     * 间接法-经营活动现金流量净额差额（合计平衡项目）
     */
    @ApiModelProperty("间接法-经营活动现金流量净额差额（合计平衡项目）")
    private String imNetCashFlowsOperActNetting;

    /**
     * 间接法-经营活动产生的现金流量净额
     */
    @ApiModelProperty("间接法-经营活动产生的现金流量净额")
    private String imNetCashFlowsOperAct;

    /**
     * 债务转为资本
     */
    @ApiModelProperty("债务转为资本")
    private String convDebtIntoCap;

    /**
     * 一年内到期的可转换公司债券
     */
    @ApiModelProperty("一年内到期的可转换公司债券")
    private String convCorpBondsDueWithin1y;

    /**
     * 融资租入固定资产
     */
    @ApiModelProperty("融资租入固定资产")
    private String faFncLeases;

    /**
     * 现金的期末余额
     */
    @ApiModelProperty("现金的期末余额")
    private String endBalCash;

    /**
     * 减：现金的期初余额
     */
    @ApiModelProperty("减：现金的期初余额")
    private String begBalCash;

    /**
     * 加：现金等价物的期末余额
     */
    @ApiModelProperty("加：现金等价物的期末余额")
    private String endBalCashEqu;

    /**
     * 减：现金等价物的期初余额
     */
    @ApiModelProperty("减：现金等价物的期初余额")
    private String begBalCashEqu;

    /**
     * 加：间接法-现金净增加额差额（特殊报表科目）
     */
    @ApiModelProperty("加：间接法-现金净增加额差额（特殊报表科目）")
    private String imNetIncrCashCashEquGap;

    /**
     * 间接法-现金净增加额差额（合计平衡项目）
     */
    @ApiModelProperty("间接法-现金净增加额差额（合计平衡项目）")
    private String imNetIncrCashCashEquNetting;

    /**
     * 间接法-现金及现金等价物净增加额
     */
    @ApiModelProperty("间接法-现金及现金等价物净增加额")
    private String netIncrCashCashEquIm;

    @ApiModelProperty("定期报告实际披露日期")
    private String stmIssuingdate;

    @ApiModelProperty("报告起始日期")
    private String stmRptS;

    @ApiModelProperty("报告截止日期")
    private String stmRptE;
}
