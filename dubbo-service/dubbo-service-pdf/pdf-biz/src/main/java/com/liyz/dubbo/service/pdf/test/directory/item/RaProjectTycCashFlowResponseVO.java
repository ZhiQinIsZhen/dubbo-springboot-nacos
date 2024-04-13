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
public class RaProjectTycCashFlowResponseVO implements Serializable {
    private static final long serialVersionUID = 6342644032938652143L;

    @ApiModelProperty("年份")
    private String showYear;

    @ApiModelProperty("销售商品、提供劳务收到的现金")
    private String cashReceivedOfSalesService;

    @ApiModelProperty("收到其他与经营活动有关的现金")
    private String cashReceivedOfOtherFa;

    @ApiModelProperty("经营活动现金流入小计")
    private String subTotalOfCiFromOa;

    @ApiModelProperty("购买商品、接受劳务支付的现金")
    private String goodsBuyAndServiceCashPay;

    @ApiModelProperty("支付给职工以及为职工支付的现金")
    private String cashPaidToStaffEtc;

    @ApiModelProperty("支付的各项税费")
    private String paymentsOfAllTaxes;

    @ApiModelProperty("支付其他与经营活动有关的现金")
    private String otherCashPaidRelatedToOa;

    @ApiModelProperty("经营活动现金流出小计")
    private String subTotalOfCosFromOa;

    @ApiModelProperty("经营活动产生的现金流量净额")
    private String ncfFromOa;

    @ApiModelProperty("收回投资收到的现金")
    private String cashReceivedOfDspslInvest;

    @ApiModelProperty("取得投资收益收到的现金")
    private String investIncomeCashReceived;

    @ApiModelProperty("处置固定资产、无形资产和其他长期资产收回的现金净额")
    private String netCashOfDisposalAssets;

    @ApiModelProperty("收到其他与筹资活动有关的现金")
    private String cashReceivedOfOthrFa;

    @ApiModelProperty("收到其他与投资活动有关的现金")
    private String cashReceivedOfOtherIa;

    @ApiModelProperty("处置子公司及其他营业单位收到的现金净额")
    private String netCashOfDisposalBranch;

    @ApiModelProperty("投资活动现金流入小计")
    private String subTotalOfCiFromIa;

    @ApiModelProperty("购建固定资产、无形资产和其他长期资产支付的现金")
    private String cashPaidForAssets	;

    @ApiModelProperty("投资支付的现金")
    private String investPaidCash	;

    @ApiModelProperty("取得子公司及其他营业单位支付的现金净额")
    private String netCashAmtFromBranch;

    @ApiModelProperty("投资活动现金流出小计")
    private String subTotalOfCosFromIa;

    @ApiModelProperty("投资活动产生的现金流量净额")
    private String ncfFromIa;

    @ApiModelProperty("吸收投资收到的现金")
    private String cashReceivedOfAbsorbInvest;

    @ApiModelProperty("子公司吸收少数股东投资收到的现金")
    private String cashReceivedFromInvestor;

    @ApiModelProperty("取得借款收到的现金")
    private String cashReceivedOfBorrowing;

    @ApiModelProperty("发行债券收到的现金")
    private String cashReceivedFromBondIssue;

    @ApiModelProperty("筹资活动现金流入小计")
    private String subTotalOfCiFromFa;

    @ApiModelProperty("偿还债务支付的现金")
    private String cashPayForDebt;

    @ApiModelProperty("分配股利、利润或偿付利息支付的现金")
    private String cashPaidOfDistribution;

    @ApiModelProperty("子公司支付给少数股东的股利、利润")
    private String branchPaidToMinorityHolder;

    @ApiModelProperty("支付其他与筹资活动有关的现金")
    private String otherCashPaidRelatingToFa;

    @ApiModelProperty("筹资活动现金流出小计")
    private String subTotalOfCosFromFa;

    @ApiModelProperty("筹资活动产生的现金流量净额")
    private String ncfFromFa;

    @ApiModelProperty("汇率变动对现金及现金等价物的影响")
    private String effectOfExchangeChgOnCce;

    @ApiModelProperty("现金及现金等价物净增加额")
    private String netIncreaseInCce;

    @ApiModelProperty("加:期初现金及现金等价物余额")
    private String initialBalanceOfCce;

    @ApiModelProperty("期末现金及现金等价物余额")
    private String finalBalanceOfCce;
}
