package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:资产负债(excel)
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 16:42
 */
@Getter
@Setter
@ApiModel("资产负债详情 -excel")
public class RaProjectFinancialSheetExcelResponseVO implements Serializable {
    private static final long serialVersionUID = -3796412128203082393L;

    @ApiModelProperty("报告期")
    private String year;

    @ApiModelProperty("报表类型")
    private String type;

    //new
    @ApiModelProperty("现金及现金等价物")
    private String cashEquivalents;

    @ApiModelProperty("交易性金融资产")
    private String tradableFnnclAssets;

    //new
    @ApiModelProperty("其他短期投资")
    private String otherShortTermInvestments;

    @ApiModelProperty("应收款项合计")
    private String totalReceivables;

    //new
    @ApiModelProperty("应收账款及票据")
    private String accountBillsReceivable;

    @ApiModelProperty("其他应收款")
    private String othrReceivables;

    @ApiModelProperty("存货")
    private String inventory;

    @ApiModelProperty("其他流动资产")
    private String othrCurrentAssets;

    @ApiModelProperty("流动资产合计")
    private String totalCurrentAssets;

    @ApiModelProperty("固定资产净值")
    private String fixedAsset;

    //new
    @ApiModelProperty("权益性投资")
    private String equityInvestment;

    //new
    @ApiModelProperty("持有至到期投资")
    private String heldToMaturityInvestments;

    //new
    @ApiModelProperty("可供出售投资")
    private String salableAssets;

    //new
    @ApiModelProperty("其他长期投资")
    private String otherLongTermInvestments;

    //new
    @ApiModelProperty("商誉及无形资产")
    private String goodwillIntangibleAssets;

    //new
    @ApiModelProperty("土地使用权")
    private String landUseRights;

    //new
    @ApiModelProperty("非流动资产合计")
    private String totalNoncurrentAssets;

    //new
    @ApiModelProperty("总资产")
    private String totalAssets;

    //new
    @ApiModelProperty("应付账款及票据")
    private String accountBillsPayable;

    //new
    @ApiModelProperty("应交税金")
    private String taxPayable;

    //new
    @ApiModelProperty("交易性金融负债")
    private String transactionalFnnclLiab;

    //new
    @ApiModelProperty("短期借贷及长期借贷当期到期部分")
    private String shortLongTermLoansCurrentPeriod;

    //new
    @ApiModelProperty("其他流动负债")
    private String otherNoncurrentLiab;

    //new
    @ApiModelProperty("流动负债合计")
    private String totalNoncurrentLiab;


    //new
    @ApiModelProperty("长期借贷")
    private String longTermLoan;

    //new
    @ApiModelProperty("其他非流动负债")
    private String othrNonCurrentLiab;

    //new
    @ApiModelProperty("非流动负债合计")
    private String totalOthrNonCurrentLiab;

    //new
    @ApiModelProperty("总负债")
    private String totalLiab;

    //new
    @ApiModelProperty("优先股")
    private String preferredStock;

    //new
    @ApiModelProperty("普通股股本")
    private String commonStock;

    //new
    @ApiModelProperty("储备")
    private String reserve;

    //new
    @ApiModelProperty("库存股")
    private String treasuryStock;

    //new
    @ApiModelProperty("其他综合性收益")
    private String otherComprehensiveIncome;

    //new
    @ApiModelProperty("普通股权益总额")
    private String totalCommonEquity;

    //new
    @ApiModelProperty("归属母公司股东权益")
    private String parentCompanyEquity;

    //new
    @ApiModelProperty("少数股东权益")
    private String minorityInterests;

    //new
    @ApiModelProperty("股东权益合计")
    private String totalShareholdersEquity;

    //new
    @ApiModelProperty("总负债及总权益")
    private String totalLiabilitiesEquity;

    //new
    @ApiModelProperty("显示币种")
    private String displayCurrency;


    //new
    @ApiModelProperty("原始币种")
    private String originalCurrency;

    //new
    @ApiModelProperty("转换汇率")
    private String convertExchangeRate;

    //new
    @ApiModelProperty("利率类型")
    private String rateType;

    //new
    @ApiModelProperty("区间起始日")
    private String intervalStartDate;

    //new
    @ApiModelProperty("区间截止日")
    private String intervalEndDate;

    //new
    @ApiModelProperty("报告期跨度")
    private String spanReportingPeriod;

    //new
    @ApiModelProperty("公告日期")
    private String announcementDate;

    //new
    @ApiModelProperty("会计准则")
    private String accountingStandards;

    //new
    @ApiModelProperty("审计意见")
    private String auditOpinions;

    //new
    @ApiModelProperty("核数师")
    private String auditor;

    //new
    @ApiModelProperty("原始报表")
    private String originalReport;

    @ApiModelProperty("其他非流动资产")
    private String othrNonCurrentAssets;
}
