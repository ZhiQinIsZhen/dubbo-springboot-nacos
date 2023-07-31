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
 * @date 2023/2/23 9:47
 */
@Getter
@Setter
public class RaProjectWdHkProfitResponseVO implements Serializable {
    private static final long serialVersionUID = -7816289948930746590L;

    @ApiModelProperty("年份")
    private String showYear;

    @ApiModelProperty("财报类别 1：年报；2：中报 ；3：一季度；4:三季度")
    private Integer type;

    /**
     * 营业总收入
     */
    @ApiModelProperty("营业总收入")
    private String wgsdSales;

    /**
     * 主营业务收入
     */
    @ApiModelProperty("主营业务收入")
    private String wgsdSalesOper;

    /**
     * 其他营业收入
     */
    @ApiModelProperty("其他营业收入")
    private String wgsdSalesOth;

    /**
     * 营业总支出
     */
    @ApiModelProperty("营业总支出")
    private String wgsdOperExpTot;

    /**
     * 营业成本
     */
    @ApiModelProperty("营业成本")
    private String wgsdOperCost;

    /**
     * 营业开支
     */
    @ApiModelProperty("营业开支")
    private String wgsdOperExp;

    /**
     * 营业利润
     */
    @ApiModelProperty("营业利润")
    private String wgsdEbitOper;

    /**
     * 加：利息收入
     */
    @ApiModelProperty("加：利息收入")
    private String wgsdIntInc;

    /**
     * 减：利息支出
     */
    @ApiModelProperty("减：利息支出")
    private String wgsdIntExp;

    /**
     * 加：权益性投资损益
     */
    @ApiModelProperty("加：权益性投资损益")
    private String wgsdInvestGain;

    /**
     * 其他非经营性损益
     */
    @ApiModelProperty("其他非经营性损益")
    private String wgsdNoOperInc;

    /**
     * 非经常项目前利润
     */
    @ApiModelProperty("非经常项目前利润")
    private String wgsdEbtExclUnusualItems;

    /**
     * 加：非经常项目损益
     */
    @ApiModelProperty("加：非经常项目损益")
    private String wgsdUnusualItems;

    /**
     * 除税前利润
     */
    @ApiModelProperty("除税前利润")
    private String wgsdIncPretax;

    /**
     * 减：所得税
     */
    @ApiModelProperty("减：所得税")
    private String wgsdIncTax;

    /**
     * 少数股东损益
     */
    @ApiModelProperty("少数股东损益")
    private String wgsdMinIntExp;

    /**
     * 持续经营净利润
     */
    @ApiModelProperty("持续经营净利润")
    private String wgsdContinueOper;

    /**
     * 加：非持续经营净利润
     */
    @ApiModelProperty("加：非持续经营净利润")
    private String wgsdDiscOper;

    /**
     * 其他特殊项
     */
    @ApiModelProperty("其他特殊项")
    private String wgsdExod;

    /**
     * 净利润
     */
    @ApiModelProperty("净利润")
    private String wgsdNetInc;

    /**
     * 减：优先股利及其他调整项
     */
    @ApiModelProperty("减：优先股利及其他调整项")
    private String wgsdDvdPfdAdj;

    /**
     * 归属普通股东净利润
     */
    @ApiModelProperty("归属普通股东净利润")
    private String wgsdNetIncDil;

    /**
     * 综合收益
     */
    @ApiModelProperty("综合收益")
    private String wgsdComprInc;

    @ApiModelProperty("定期报告实际披露日期")
    private String stmIssuingdate;

    @ApiModelProperty("报告起始日期")
    private String stmRptS;

    @ApiModelProperty("报告截止日期")
    private String stmRptE;
}
