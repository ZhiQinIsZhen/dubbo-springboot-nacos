package com.liyz.dubbo.api.open.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.liyz.dubbo.common.excel.service.AbstractExcelReadRowService;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/26 9:28
 */
@Getter
@Setter
public class FinancialSheetExcel extends AbstractExcelReadRowService {
    private static final long serialVersionUID = -2266288970178883046L;

    @ExcelProperty("报告期")
    @ApiModelProperty("报告期")
    private String year;

    @ExcelProperty("报表类型")
    @ApiModelProperty("报表类型")
    private String type;

    //new
    @ExcelProperty("现金及现金等价物")
    @ApiModelProperty("现金及现金等价物")
    private String cashEquivalents;

    @ExcelProperty("交易性金融资产")
    @ApiModelProperty("交易性金融资产")
    private String tradableFnnclAssets;

    //new
    @ExcelProperty("其他短期投资")
    @ApiModelProperty("其他短期投资")
    private String otherShortTermInvestments;

    //new
    @ExcelProperty("应收账款及票据")
    @ApiModelProperty("应收账款及票据")
    private String accountBillsReceivable;

    @ExcelProperty("其他应收款")
    @ApiModelProperty("其他应收款")
    private String othrReceivables;

    @ExcelProperty("存货")
    @ApiModelProperty("存货")
    private String inventory;

    @ExcelProperty("其他流动资产")
    @ApiModelProperty("其他流动资产")
    private String othrCurrentAssets;

    @ExcelProperty("流动资产合计")
    @ApiModelProperty("流动资产合计")
    private String totalCurrentAssets;

    @ExcelProperty("固定资产净值")
    @ApiModelProperty("固定资产净值")
    private String fixedAsset;

    //new
    @ExcelProperty("权益性投资")
    @ApiModelProperty("权益性投资")
    private String equityInvestment;

    //new
    @ExcelProperty("持有至到期投资")
    @ApiModelProperty("持有至到期投资")
    private String heldToMaturityInvestments;

    //new
    @ExcelProperty("可供出售投资")
    @ApiModelProperty("可供出售投资")
    private String salableAssets;

    //new
    @ExcelProperty("其他长期投资")
    @ApiModelProperty("其他长期投资")
    private String otherLongTermInvestments;

    //new
    @ExcelProperty("商誉及无形资产")
    @ApiModelProperty("商誉及无形资产")
    private String goodwillIntangibleAssets;

    //new
    @ExcelProperty("土地使用权")
    @ApiModelProperty("土地使用权")
    private String landUseRights;

    //new
    @ExcelProperty("非流动资产合计")
    @ApiModelProperty("非流动资产合计")
    private String totalNoncurrentAssets;

    //new
    @ExcelProperty("总资产")
    @ApiModelProperty("总资产")
    private String totalAssets;

    //new
    @ExcelProperty("应付账款及票据")
    @ApiModelProperty("应付账款及票据")
    private String accountBillsPayable;

    //new
    @ExcelProperty("应交税金")
    @ApiModelProperty("应交税金")
    private String taxPayable;

    //new
    @ExcelProperty("交易性金融负债")
    @ApiModelProperty("交易性金融负债")
    private String transactionalFnnclLiab;

    //new
    @ExcelProperty("短期借贷及长期借贷当期到期部分")
    @ApiModelProperty("短期借贷及长期借贷当期到期部分")
    private String ShortLongTermLoansCurrentPeriod;

    //new
    @ExcelProperty("其他流动负债")
    @ApiModelProperty("其他流动负债")
    private String otherNoncurrentLiab;

    //new
    @ExcelProperty("流动负债合计")
    @ApiModelProperty("流动负债合计")
    private String totalNoncurrentLiab;


    //new
    @ExcelProperty("长期借贷")
    @ApiModelProperty("长期借贷")
    private String longTermLoan;

    //new
    @ExcelProperty("其他非流动负债")
    @ApiModelProperty("其他非流动负债")
    private String othrNonCurrentLiab;

    //new
    @ExcelProperty("非流动负债合计")
    @ApiModelProperty("非流动负债合计")
    private String totalOthrNonCurrentLiab;

    //new
    @ExcelProperty("总负债")
    @ApiModelProperty("总负债")
    private String totalLiab;

    //new
    @ExcelProperty("优先股")
    @ApiModelProperty("优先股")
    private String preferredStock;

    //new
    @ExcelProperty("普通股股本")
    @ApiModelProperty("普通股股本")
    private String commonStock;

    //new
    @ExcelProperty("储备")
    @ApiModelProperty("储备")
    private String reserve;

    //new
    @ExcelProperty("库存股")
    @ApiModelProperty("库存股")
    private String treasuryStock;

    //new
    @ExcelProperty("其他综合性收益")
    @ApiModelProperty("其他综合性收益")
    private String otherComprehensiveIncome;

    //new
    @ExcelProperty("普通股权益总额")
    @ApiModelProperty("普通股权益总额")
    private String totalCommonEquity;

    //new
    @ExcelProperty("归属母公司股东权益")
    @ApiModelProperty("归属母公司股东权益")
    private String parentCompanyEquity;

    //new
    @ExcelProperty("少数股东权益")
    @ApiModelProperty("少数股东权益")
    private String minorityInterests;

    //new
    @ExcelProperty("股东权益合计")
    @ApiModelProperty("股东权益合计")
    private String totalShareholdersEquity;

    //new
    @ExcelProperty("总负债及总权益")
    @ApiModelProperty("总负债及总权益")
    private String totalLiabilitiesEquity;

    //new
    @ExcelProperty("显示币种")
    @ApiModelProperty("显示币种")
    private String displayCurrency;


    //new
    @ExcelProperty("原始币种")
    @ApiModelProperty("原始币种")
    private String originalCurrency;

    //new
    @ExcelProperty("转换汇率")
    @ApiModelProperty("转换汇率")
    private String convertExchangeRate;

    //new
    @ExcelProperty("利率类型")
    @ApiModelProperty("利率类型")
    private String rateType;

    //new
    @ExcelProperty("区间起始日")
    @ApiModelProperty("区间起始日")
    private String intervalStartDate;

    //new
    @ExcelProperty("区间截止日")
    @ApiModelProperty("区间截止日")
    private String intervalEndDate;

    //new
    @ExcelProperty("报告期跨度")
    @ApiModelProperty("报告期跨度")
    private String spanReportingPeriod;

    //new
    @ExcelProperty("公告日期")
    @ApiModelProperty("公告日期")
    private String announcementDate;

    //new
    @ExcelProperty("会计准则")
    @ApiModelProperty("会计准则")
    private String accountingStandards;

    //new
    @ExcelProperty("审计意见")
    @ApiModelProperty("审计意见")
    private String auditOpinions;

    //new
    @ExcelProperty("核数师")
    @ApiModelProperty("核数师")
    private String auditor;

    //new
    @ExcelProperty("原始报表")
    @ApiModelProperty("原始报表")
    private String originalReport;
}
