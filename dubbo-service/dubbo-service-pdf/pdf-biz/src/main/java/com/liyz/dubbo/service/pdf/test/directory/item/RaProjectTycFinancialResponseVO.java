package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:资产负债详情
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 15:34
 */
@ApiModel("资产负债详情 -三方接口")
@Getter
@Setter
public class RaProjectTycFinancialResponseVO implements Serializable {
    private static final long serialVersionUID = 2126421559819544784L;

    @ApiModelProperty("年")
    private String showYear;

    @ApiModelProperty("货币资金")
    private String currencyFunds;

    @ApiModelProperty("以公允价值计量且其变动计入当期损益的金融资产")
    private String specifiedFnnclAssetsAtFv;

    @ApiModelProperty("其中:交易性金融资产")
    private String tradableFnnclAssets;

    @ApiModelProperty("应收票据")
    private String billsReceivable;

    @ApiModelProperty("应收账款")
    private String accountReceivable;

    @ApiModelProperty("预付款项")
    private String prePayment;

    @ApiModelProperty("其他应收款")
    private String othrReceivables;

    @ApiModelProperty("存货")
    private String inventory;

    @ApiModelProperty("其他流动资产")
    private String othrCurrentAssets;

    @ApiModelProperty("流动资产合计")
    private String totalCurrentAssets;

    @ApiModelProperty("可供出售金融资产")
    private String salableFinancialAssets;

    @ApiModelProperty("长期股权投资")
    private String ltEquityInvest;

    @ApiModelProperty("投资性房地产")
    private String investProperty;

    @ApiModelProperty("固定资产")
    private String fixedAsset;

    @ApiModelProperty("在建工程")
    private String constructionInProcess;

    @ApiModelProperty("无形资产")
    private String intangibleAssets;

    @ApiModelProperty("商誉")
    private String goodwill;

    @ApiModelProperty("长期待摊费用")
    private String ltDeferredExpense;

    @ApiModelProperty("递延所得税资产")
    private String dtAssets;

    @ApiModelProperty("其他非流动资产")
    private String othrNoncurrentAssets;

    @ApiModelProperty("非流动资产合计")
    private String totalNoncurrentAssets;

    @ApiModelProperty("资产总计")
    private String totalAssets;

    @ApiModelProperty("短期借款")
    private String stLoan;

    @ApiModelProperty("应付票据")
    private String billPayable;

    @ApiModelProperty("应付账款")
    private String accountsPayable;

    @ApiModelProperty("预收款项")
    private String preReceivable;

    @ApiModelProperty("应付职工薪酬")
    private String payrollPayable;

    @ApiModelProperty("应交税费")
    private String taxPayable;

    @ApiModelProperty("应付利息")
    private String interestPayable;

    @ApiModelProperty("应付股利")
    private String dividendPayable;

    @ApiModelProperty("其他应付款")
    private String othrPayables;

    @ApiModelProperty("一年内到期的非流动负债")
    private String noncurrentLiabDueIn1y;

    @ApiModelProperty("其他流动负债")
    private String othrCurrentLiab;

    @ApiModelProperty("流动负债合计")
    private String totalCurrentLiab;

    @ApiModelProperty("长期借款")
    private String ltLoan;

    @ApiModelProperty("应付债券")
    private String bondPayable;

    @ApiModelProperty("预计负债")
    private String estimatedLiab;

    @ApiModelProperty("递延所得税负债")
    private String dtLiab;

    @ApiModelProperty("其他非流动负债")
    private String othrNonCurrentLiab;

    @ApiModelProperty("非流动负债合计")
    private String totalNoncurrentLiab;

    @ApiModelProperty("负债合计")
    private String totalLiab;

    @ApiModelProperty("实收资本（或股本）")
    private String shares;

    @ApiModelProperty("盈余公积")
    private String earnedSurplus;

    @ApiModelProperty("资本公积")
    private String capitalReserve;

    @ApiModelProperty("未分配利润")
    private String undstrbtdProfit;

    @ApiModelProperty("归属于母公司股东权益合计")
    private String totalQuityAtsopc;

    @ApiModelProperty("少数股东权益")
    private String minorityEquity;

    @ApiModelProperty("股东权益合计")
    private String totalHoldersEquity	;

    @ApiModelProperty("负债和股东权益合计")
    private String totalLiabAndHoldersEquity;
}
