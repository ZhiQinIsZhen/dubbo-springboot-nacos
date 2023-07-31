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
 * @date 2023/2/22 13:31
 */
@Getter
@Setter
public class RaProjectWdHkCashFlowResponseVO implements Serializable {
    private static final long serialVersionUID = 5173696700065731025L;

    @ApiModelProperty("年份")
    private String showYear;

    @ApiModelProperty("财报类别 1：年报；2：中报 ；3：一季度；4:三季度")
    private Integer type;

    /**
     * 净利润
     */
    @ApiModelProperty("净利润")
    private String wgsdNetInc;

    /**
     * 加: 折旧与摊销
     */
    @ApiModelProperty("加: 折旧与摊销")
    private String wgsdDepExpCf;

    /**
     * 营运资本变动
     */
    @ApiModelProperty("营运资本变动")
    private String wgsdWkcapChg;

    /**
     * 其他非现金调整
     */
    @ApiModelProperty("其他非现金调整")
    private String wgsdNonCashChg;

    /**
     * 经营活动产生的现金流量净额
     */
    @ApiModelProperty("经营活动产生的现金流量净额")
    private String wgsdOperCf;

    /**
     * 出售固定资产收到的现金
     */
    @ApiModelProperty("出售固定资产收到的现金")
    private String wgsdAssetsBusCf;

    /**
     * 减: 资本性支出
     */
    @ApiModelProperty("减: 资本性支出")
    private String wgsdCapexFf;

    /**
     * 投资减少
     */
    @ApiModelProperty("投资减少")
    private String wgsdInvestSaleCf;

    /**
     * 减: 投资增加
     */
    @ApiModelProperty("减: 投资增加")
    private String wgsdInvestPurchCf;

    /**
     * 其他投资活动产生的现金流量净额
     */
    @ApiModelProperty("其他投资活动产生的现金流量净额")
    private String wgsdInvestOthCf;

    /**
     * 投资活动产生的现金流量净额
     */
    @ApiModelProperty("投资活动产生的现金流量净额")
    private String wgsdInvestCf;

    /**
     * 债务增加
     */
    @ApiModelProperty("债务增加")
    private String wgsdDebtIssCf;

    /**
     * 减: 债务减少
     */
    @ApiModelProperty("减: 债务减少")
    private String wgsdDebtReductCf;

    /**
     * 股本增加
     */
    @ApiModelProperty("股本增加")
    private String wgsdStkPurchCf;

    /**
     * 减: 股本减少
     */
    @ApiModelProperty("减: 股本减少")
    private String wgsdStkSaleCf;

    /**
     * 支付的股利合计
     */
    @ApiModelProperty("支付的股利合计")
    private String wgsdDivCf;

    /**
     * 其他筹资活动产生的现金流量净额
     */
    @ApiModelProperty("其他筹资活动产生的现金流量净额")
    private String wgsdFinOthCf;

    /**
     * 筹资活动产生的现金流量净额
     */
    @ApiModelProperty("筹资活动产生的现金流量净额")
    private String wgsdFinCf;

    /**
     * 汇率变动影响
     */
    @ApiModelProperty("汇率变动影响")
    private String wgsdForExchCf;

    /**
     * 其他现金流量调整
     */
    @ApiModelProperty("其他现金流量调整")
    private String wgsdCashBalChgCf;

    /**
     * 现金及现金等价物净增加额
     */
    @ApiModelProperty("现金及现金等价物净增加额")
    private String wgsdChgCashCf;

    /**
     * 现金及现金等价物期初余额
     */
    @ApiModelProperty("现金及现金等价物期初余额")
    private String wgsdCashBegBalCf;

    /**
     * 现金及现金等价物期末余额
     */
    @ApiModelProperty("现金及现金等价物期末余额")
    private String wgsdCashEndBalCf;

    @ApiModelProperty("区间截止日")
    private String intervalEndDate;

    @ApiModelProperty("定期报告实际披露日期")
    private String stmIssuingdate;

    @ApiModelProperty("报告起始日期")
    private String stmRptS;

    @ApiModelProperty("报告截止日期")
    private String stmRptE;
}
