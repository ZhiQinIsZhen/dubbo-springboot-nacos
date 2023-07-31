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
 * @date 2023/2/21 14:16
 */
@Getter
@Setter
public class RaProjectExcelHkFinancialResponseVO implements Serializable {
    private static final long serialVersionUID = 5138914655857279651L;

    @ApiModelProperty("报告期")
    private String year;

    @ApiModelProperty("报表类型")
    private String type;

    @ApiModelProperty("现金及现金等价物")
    private String cashEquivalents;

    @ApiModelProperty("交易性金融资产")
    private String tradableFnnclAssets;

    @ApiModelProperty("其他短期投资")
    private String otherShortTermInvestments;

    @ApiModelProperty("应收款项合计")
    private String totalReceivables;

    @ApiModelProperty("应收账款及票据")
    private String accountBillsReceivable;

    @ApiModelProperty("其他应收款")
    private String othrReceivables;

    @ApiModelProperty("存货")
    private String inventory;

    @ApiModelProperty("其他流动资产")
    private String othrCurrentAssets;

    @ApiModelProperty("其他非流动资产")
    private String othrNonCurrentAssets;

    @ApiModelProperty("流动资产合计")
    private String totalCurrentAssets;

    @ApiModelProperty("固定资产净值")
    private String fixedAsset;

    @ApiModelProperty("权益性投资")
    private String equityInvestment;

    @ApiModelProperty("持有至到期投资")
    private String heldToMaturityInvestments;

    @ApiModelProperty("可供出售投资")
    private String salableAssets;

    @ApiModelProperty("其他长期投资")
    private String otherLongTermInvestments;

    @ApiModelProperty("商誉及无形资产")
    private String goodwillIntangibleAssets;

    @ApiModelProperty("土地使用权")
    private String landUseRights;

    @ApiModelProperty("非流动资产合计")
    private String totalNoncurrentAssets;

    @ApiModelProperty("总资产")
    private String totalAssets;

    @ApiModelProperty("应付账款及票据")
    private String accountBillsPayable;

    @ApiModelProperty("应交税金")
    private String taxPayable;

    @ApiModelProperty("交易性金融负债")
    private String transactionalFnnclLiab;

    @ApiModelProperty("短期借贷及长期借贷当期到期部分")
    private String shortLongTermLoansCurrentPeriod;

    @ApiModelProperty("其他流动负债")
    private String otherNoncurrentLiab;

    @ApiModelProperty("流动负债合计")
    private String totalNoncurrentLiab;

    @ApiModelProperty("长期借贷")
    private String longTermLoan;

    @ApiModelProperty("其他非流动负债")
    private String othrNonCurrentLiab;

    @ApiModelProperty("非流动负债合计")
    private String totalOthrNonCurrentLiab;

    @ApiModelProperty("总负债")
    private String totalLiab;

    @ApiModelProperty("优先股")
    private String preferredStock;

    @ApiModelProperty("普通股股本")
    private String commonStock;

    @ApiModelProperty("储备")
    private String reserve;

    @ApiModelProperty("库存股")
    private String treasuryStock;

    @ApiModelProperty("其他综合性收益")
    private String otherComprehensiveIncome;

    @ApiModelProperty("普通股权益总额")
    private String totalCommonEquity;

    @ApiModelProperty("归属母公司股东权益")
    private String parentCompanyEquity;

    @ApiModelProperty("少数股东权益")
    private String minorityInterests;

    @ApiModelProperty("股东权益合计")
    private String totalShareholdersEquity;

    @ApiModelProperty("总负债及总权益")
    private String totalLiabilitiesEquity;

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
