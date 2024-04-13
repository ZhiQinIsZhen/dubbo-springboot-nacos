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
 * @date 2023/2/22 17:35
 */
@Getter
@Setter
public class RaProjectWdFinancialResponseVO implements Serializable {
    private static final long serialVersionUID = 99535427287988185L;

    @ApiModelProperty("年份")
    private String showYear;

    @ApiModelProperty("财报类别 1：年报；2：中报 ；3：一季度；4:三季度")
    private Integer type;

    /**
     * 货币资金
     */
    @ApiModelProperty("货币资金")
    private String monetaryCap;

    /**
     * 交易性金融资产
     */
    @ApiModelProperty("交易性金融资产")
    private String tradableFinAssets;

    /**
     * 衍生金融资产
     */
    @ApiModelProperty("衍生金融资产")
    private String derivativeFinAssets;

    /**
     * 应收票据及应收账款
     */
    @ApiModelProperty("应收票据及应收账款")
    private String acctandnotesRcv;

    /**
     * 应收票据
     */
    @ApiModelProperty("应收票据")
    private String notesRcv;

    /**
     * 应收账款
     */
    @ApiModelProperty("应收账款")
    private String acctRcv;

    /**
     * 应收款项融资
     */
    @ApiModelProperty("应收款项融资")
    private String financingA;

    /**
     * 预付款项
     */
    @ApiModelProperty("预付款项")
    private String prepay;

    /**
     * 其他应收款（合计）
     */
    @ApiModelProperty("其他应收款（合计）")
    private String othRcvTot;

    /**
     * 应收股利
     */
    @ApiModelProperty("应收股利")
    private String dvdRcv;

    /**
     * 应收利息
     */
    @ApiModelProperty("应收利息")
    private String intRcv;

    /**
     * 其他应收款
     */
    @ApiModelProperty("其他应收款")
    private String othRcv;

    /**
     * 买入返售金融资产
     */
    @ApiModelProperty("买入返售金融资产")
    private String redMonetaryCapForSale;

    /**
     * 存货
     */
    @ApiModelProperty("存货")
    private String inventories;

    /**
     * 其中：消耗性生物资产
     */
    @ApiModelProperty("其中：消耗性生物资产")
    private String consumptiveBioAssets;

    /**
     * 合同资产
     */
    @ApiModelProperty("合同资产")
    private String contAssets;

    /**
     * 划分为持有待售的资产
     */
    @ApiModelProperty("划分为持有待售的资产")
    private String hfsAssets;

    /**
     * 一年内到期的非流动资产
     */
    @ApiModelProperty("一年内到期的非流动资产")
    private String nonCurAssetsDueWithin1y;

    /**
     * 待摊费用
     */
    @ApiModelProperty("待摊费用")
    private String deferredExp;

    /**
     * 其他流动资产
     */
    @ApiModelProperty("其他流动资产")
    private String othCurAssets;

    /**
     * 其他金融类流动资产
     *
     * @return 结算备付金 + 拆出资金 + 应收保费 + 应收分保账款 + 应收分保合同准备金
     */
    @ApiModelProperty("其他金融类流动资产")
    public String otherFinancialCurrentAssets;

    /**
     * 结算备付金
     */
    @ApiModelProperty("结算备付金")
    private String settleRsrv;

    /**
     * 拆出资金
     */
    @ApiModelProperty("拆出资金")
    private String loansToOthBanks;

    /**
     * 应收保费
     */
    @ApiModelProperty("应收保费")
    private String premRcv;

    /**
     * 应收分保账款
     */
    @ApiModelProperty("应收分保账款")
    private String rcvFromReinsurer;

    /**
     * 应收分保合同准备金
     */
    @ApiModelProperty("应收分保合同准备金")
    private String rcvFromCededInsurContRsrv;

    /**
     * 流动资产差额（特殊报表科目）
     */
    @ApiModelProperty("流动资产差额（特殊报表科目）")
    private String curAssetsGap;

    /**
     * 流动资产差额（合计平衡项目）
     */
    @ApiModelProperty("流动资产差额（合计平衡项目）")
    private String curAssetsNetting;

    /**
     * 流动资产合计
     */
    @ApiModelProperty("流动资产合计")
    private String totCurAssets;

    /**
     * 发放贷款及垫款
     */
    @ApiModelProperty("发放贷款及垫款")
    private String loansAndAdvGranted;

    /**
     * 以公允价值且其变动计入其他综合收益的金融资产
     */
    @ApiModelProperty("以公允价值且其变动计入其他综合收益的金融资产")
    private String finAssetsChgComprehInc;

    /**
     * 以摊余成本计量的金融资产
     */
    @ApiModelProperty("以摊余成本计量的金融资产")
    private String finAssetsAmortizedcost;

    /**
     * 债权投资
     */
    @ApiModelProperty("债权投资")
    private String debtInvest;

    /**
     * 其他债权投资
     */
    @ApiModelProperty("其他债权投资")
    private String othDebtInvest;

    /**
     * 可供出售金融资产
     */
    @ApiModelProperty("可供出售金融资产")
    private String finAssetsAvailForSale;

    /**
     * 其他权益工具投资
     */
    @ApiModelProperty("其他权益工具投资")
    private String othEqyInstrumentsInvest;

    /**
     * 持有至到期投资
     */
    @ApiModelProperty("持有至到期投资")
    private String heldToMtyInvest;

    /**
     * 其他非流动金融资产
     */
    @ApiModelProperty("其他非流动金融资产")
    private String othNonCurFinaAsset;

    /**
     * 长期应收款
     */
    @ApiModelProperty("长期应收款")
    private String longTermRec;

    /**
     * 长期股权投资
     */
    @ApiModelProperty("长期股权投资")
    private String longTermEqyInvest;

    /**
     * 投资性房地产
     */
    @ApiModelProperty("投资性房地产")
    private String investRealEstate;

    /**
     * 固定资产（合计）
     */
    @ApiModelProperty("固定资产（合计）")
    private String fixAssetsTot;

    /**
     * 固定资产
     */
    @ApiModelProperty("固定资产")
    private String fixAssets;

    /**
     * 固定资产清理
     */
    @ApiModelProperty("固定资产清理")
    private String fixAssetsDisp;

    /**
     * 在建工程（合计）
     */
    @ApiModelProperty("在建工程（合计）")
    private String constInProgTot;

    /**
     * 在建工程
     */
    @ApiModelProperty("在建工程")
    private String constInProg;

    /**
     * 工程物资
     */
    @ApiModelProperty("工程物资")
    private String projMatl;

    /**
     * 生产性生物资产
     */
    @ApiModelProperty("生产性生物资产")
    private String productiveBioAssets;

    /**
     * 油气资产
     */
    @ApiModelProperty("油气资产")
    private String oilAndNaturalGasAssets;

    /**
     * 使用权资产
     */
    @ApiModelProperty("使用权资产")
    private String propRightUse;

    /**
     * 无形资产
     */
    @ApiModelProperty("无形资产")
    private String intangAssets;

    /**
     * 开发支出
     */
    @ApiModelProperty("开发支出")
    private String rCosts;

    /**
     * 商誉
     */
    @ApiModelProperty("商誉")
    private String goodwill;

    /**
     * 长期待摊费用
     */
    @ApiModelProperty("长期待摊费用")
    private String longTermDeferredExp;

    /**
     * 递延所得税资产
     */
    @ApiModelProperty("递延所得税资产")
    private String deferredTaxAssets;

    /**
     * 其他非流动资产
     */
    @ApiModelProperty("其他非流动资产")
    private String othNonCurAssets;

    /**
     * 非流动资产差额（特殊报表科目）
     */
    @ApiModelProperty("非流动资产差额（特殊报表科目）")
    private String nonCurAssetsGap;

    /**
     * 非流动资产差额（合计平衡项目）
     */
    @ApiModelProperty("非流动资产差额（合计平衡项目）")
    private String nonCurAssetsNetting;

    /**
     * 非流动资产合计
     */
    @ApiModelProperty("非流动资产合计")
    private String totNonCurAssets;

    /**
     * 资产差额（特殊报表科目）
     */
    @ApiModelProperty("资产差额（特殊报表科目）")
    private String assetsGap;

    /**
     * 资产差额（合计平衡衡项目）
     */
    @ApiModelProperty("资产差额（合计平衡衡项目）")
    private String assetsNetting;

    /**
     * 资产总计
     */
    @ApiModelProperty("资产总计")
    private String totAssets;

    /**
     * 短期借款
     */
    @ApiModelProperty("短期借款")
    private String stBorrow;

    /**
     * 交易性金融负债
     */
    @ApiModelProperty("交易性金融负债")
    private String tradableFinLiab;

    /**
     * 衍生金融负债
     */
    @ApiModelProperty("衍生金融负债")
    private String derivativeFinLiab;

    /**
     * 应付票据及应付账款
     */
    @ApiModelProperty("应付票据及应付账款")
    private String acctandnotesPayable;

    /**
     * 应付票据
     */
    @ApiModelProperty("应付票据")
    private String notesPayable;

    /**
     * 应付账款
     */
    @ApiModelProperty("应付账款")
    private String acctPayable;

    /**
     * 预收款项
     */
    @ApiModelProperty("预收款项")
    private String advFromCust;

    /**
     * 合同负债
     */
    @ApiModelProperty("合同负债")
    private String contLiab;

    /**
     * 应付手续费及佣金
     */
    @ApiModelProperty("应付手续费及佣金")
    private String handlingChargesCommPayable;

    /**
     * 应付职工薪酬
     */
    @ApiModelProperty("应付职工薪酬")
    private String emplBenPayable;

    /**
     * 应交税费
     */
    @ApiModelProperty("应交税费")
    private String taxesSurchargesPayable;

    /**
     * 其他应付款（合计）
     */
    @ApiModelProperty("其他应付款（合计）")
    private String othPayableTot;

    /**
     * 应付利息
     */
    @ApiModelProperty("应付利息")
    private String intPayable;

    /**
     * 应付股利
     */
    @ApiModelProperty("应付股利")
    private String dvdPayable;

    /**
     * 其他应付款
     */
    @ApiModelProperty("其他应付款")
    private String othPayable;

    /**
     * 划分为持有待售的负债
     */
    @ApiModelProperty("划分为持有待售的负债")
    private String hfsLiab;

    /**
     * 一年内到期的非流动负债
     */
    @ApiModelProperty("一年内到期的非流动负债")
    private String nonCurLiabDueWithin1y;

    /**
     * 预提费用
     */
    @ApiModelProperty("预提费用")
    private String accExp;

    /**
     * 递延收益-流动负债
     */
    @ApiModelProperty("递延收益-流动负债")
    private String deferredIncCurLiab;

    /**
     * 应付短期债券
     */
    @ApiModelProperty("应付短期债券")
    private String stBondsPayable;

    /**
     * 其他流动负债
     */
    @ApiModelProperty("其他流动负债")
    private String othCurLiab;

    /**
     * 其他金融类流动负债
     *
     * @return 向中央银行借款 + 吸收存款及同业存放 + 拆入资金 + 卖出回购金融资产款 + 应付分保账款 + 保险合同准备金 + 代理买卖证券款 + 代理承销证券款
     */
    @ApiModelProperty("其他金融类流动负债")
    public String otherFinancialCurrentLiabilities;

    /**
     * 向中央银行借款
     */
    @ApiModelProperty("向中央银行借款")
    private String borrowCentralBank;

    /**
     * 吸收存款及同业存放
     */
    @ApiModelProperty("吸收存款及同业存放")
    private String depositReceivedIbDeposits;

    /**
     * 拆入资金
     */
    @ApiModelProperty("拆入资金")
    private String loansOthBanks;

    /**
     * 卖出回购金融资产款
     */
    @ApiModelProperty("卖出回购金融资产款")
    private String fundSalesFinAssetsRp;

    /**
     * 应付分保账款
     */
    @ApiModelProperty("应付分保账款")
    private String payableToReinsurer;

    /**
     * 保险合同准备金
     */
    @ApiModelProperty("保险合同准备金")
    private String rsrvInsurCont;

    /**
     * 代理买卖证券款
     */
    @ApiModelProperty("代理买卖证券款")
    private String actingTradingSec;

    /**
     * 代理承销证券款
     */
    @ApiModelProperty("代理承销证券款")
    private String actingUwSec;

    /**
     * 流动负债差额（特殊报表科目）
     */
    @ApiModelProperty("流动负债差额（特殊报表科目）")
    private String curLiabGap;

    /**
     * 流动负债差额（合计平衡项目）
     */
    @ApiModelProperty("流动负债差额（合计平衡项目）")
    private String curLiabNetting;

    /**
     * 流动负债合计
     */
    @ApiModelProperty("流动负债合计")
    private String totCurLiab;

    /**
     * 长期借款
     */
    @ApiModelProperty("长期借款")
    private String ltBorrow;

    /**
     * 应付债券
     */
    @ApiModelProperty("应付债券")
    private String bondsPayable;

    /**
     * 租赁负债
     */
    @ApiModelProperty("租赁负债")
    private String leaseObligation;

    /**
     * 长期应付款（合计）
     */
    @ApiModelProperty("长期应付款（合计）")
    private String ltPayableTot;

    /**
     * 长期应付款
     */
    @ApiModelProperty("长期应付款")
    private String ltPayable;

    /**
     * 专项应付款
     */
    @ApiModelProperty("专项应付款")
    private String specificItemPayable;

    /**
     * 长期应付职工薪酬
     */
    @ApiModelProperty("长期应付职工薪酬")
    private String ltEmplBenPayable;

    /**
     * 预计负债
     */
    @ApiModelProperty("预计负债")
    private String provisions;

    /**
     * 递延所得税负债
     */
    @ApiModelProperty("递延所得税负债")
    private String deferredTaxLiab;

    /**
     * 递延收益-非流动负债
     */
    @ApiModelProperty("递延收益-非流动负债")
    private String deferredIncNonCurLiab;

    /**
     * 其他非流动负债
     */
    @ApiModelProperty("其他非流动负债")
    private String othNonCurLiab;

    /**
     * 非流动负债差额（特殊报表科目）
     */
    @ApiModelProperty("非流动负债差额（特殊报表科目）")
    private String nonCurLiabGap;

    /**
     * 非流动负债差额（合计平衡项目）
     */
    @ApiModelProperty("非流动负债差额（合计平衡项目）")
    private String nonCurLiabNetting;

    /**
     * 非流动负债合计
     */
    @ApiModelProperty("非流动负债合计")
    private String totNonCurLiab;

    /**
     * 负债差额（特殊报表科目）
     */
    @ApiModelProperty("负债差额（特殊报表科目）")
    private String liabGap;

    /**
     * 负债差额（合计平衡项目）
     */
    @ApiModelProperty("负债差额（合计平衡项目）")
    private String liabNetting;

    /**
     * 负债合计
     */
    @ApiModelProperty("负债合计")
    private String totLiab;

    /**
     * 实收资本（或股本）
     */
    @ApiModelProperty("实收资本（或股本）")
    private String capStk;

    /**
     * 其它权益工具
     */
    @ApiModelProperty("其它权益工具")
    private String otherEquityInstruments;

    /**
     * 其它：优先股
     */
    @ApiModelProperty("其它：优先股")
    private String otherEquityInstrumentsPre;

    /**
     * 永续债
     */
    @ApiModelProperty("永续债")
    private String perpetualDebt;

    /**
     * 资本公积金
     */
    @ApiModelProperty("资本公积金")
    private String capRsrv;

    /**
     * 减：库存股
     */
    @ApiModelProperty("减：库存股")
    private String tsyStk;

    /**
     * 其它综合收益
     */
    @ApiModelProperty("其它综合收益")
    private String otherComprehIncBs;

    /**
     * 专项储备
     */
    @ApiModelProperty("专项储备")
    private String specialRsrv;

    /**
     * 盈余公积金
     */
    @ApiModelProperty("盈余公积金")
    private String surplusRsrv;

    /**
     * 一般风险准备
     */
    @ApiModelProperty("一般风险准备")
    private String provNomRisks;

    /**
     * 未分配利润
     */
    @ApiModelProperty("未分配利润")
    private String undistributedProfit;

    /**
     * 外币报表折算差额
     */
    @ApiModelProperty("外币报表折算差额")
    private String cnvdDiffForeignCurrStat;

    /**
     * 未确认的投资损失
     */
    @ApiModelProperty("未确认的投资损失")
    private String unconfirmedInvestLossBs;

    /**
     * 股东权益差额（特殊报表科目）
     */
    @ApiModelProperty("股东权益差额（特殊报表科目）")
    private String shrhldrEqyGap;

    /**
     * 股权权益差额（合计平衡项目）
     */
    @ApiModelProperty("股权权益差额（合计平衡项目）")
    private String shrhldrEqyNetting;

    /**
     * 归属于母公司所有者权益合计
     */
    @ApiModelProperty("归属于母公司所有者权益合计")
    private String eqyBelongtoParcomsh;

    /**
     * 少数股东权益
     */
    @ApiModelProperty("少数股东权益")
    private String minorityInt;

    /**
     * 所有者权益合计
     */
    @ApiModelProperty("所有者权益合计")
    private String totEquity;

    /**
     * 负债及股东权益差额（特殊报表项目）
     */
    @ApiModelProperty("负债及股东权益差额（特殊报表项目）")
    private String liabShrhldrEqyGap;

    /**
     * 负债及股东权益差额（合计平衡项目）
     */
    @ApiModelProperty("负债及股东权益差额（合计平衡项目）")
    private String liabShrhldrEqyNetting;

    /**
     * 负债及股东权益总计
     */
    @ApiModelProperty("负债及股东权益总计")
    private String totLiabShrhldrEqy;

    @ApiModelProperty("定期报告实际披露日期")
    private String stmIssuingdate;

    @ApiModelProperty("报告起始日期")
    private String stmRptS;

    @ApiModelProperty("报告截止日期")
    private String stmRptE;
}
