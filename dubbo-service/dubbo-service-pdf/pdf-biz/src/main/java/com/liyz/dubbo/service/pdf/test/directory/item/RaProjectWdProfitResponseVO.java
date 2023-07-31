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
public class RaProjectWdProfitResponseVO implements Serializable {
    private static final long serialVersionUID = -7816289948930746590L;

    @ApiModelProperty("年份")
    private String showYear;

    @ApiModelProperty("财报类别 1：年报；2：中报 ；3：一季度；4:三季度")
    private Integer type;

    /**
     * 营业总收入
     */
    @ApiModelProperty("营业总收入")
    private String totOperRev;

    /**
     * 营业收入
     */
    @ApiModelProperty("营业收入")
    private String operRev;


    /**
     * 其他类金融业务收入
     *
     * @return 利息收入 + 已赚保费 + 手续费及佣金收入
     */
    @ApiModelProperty("其他类金融业务收入")
    public String otherFinancialBusinessIncome;

    /**
     * 利息收入
     */
    @ApiModelProperty("利息收入")
    private String intInc;

    /**
     * 已赚保费
     */
    @ApiModelProperty("已赚保费")
    private String insurPremUnearned;

    /**
     * 手续费及佣金收入
     */
    @ApiModelProperty("手续费及佣金收入")
    private String handlingChrgCommInc;

    /**
     * 营业总成本
     */
    @ApiModelProperty("营业总成本")
    private String operatingCost2;


    /**
     * 营业成本
     */
    @ApiModelProperty("营业成本")
    private String operCost;

    /**
     * 税金及附加
     */
    @ApiModelProperty("税金及附加")
    private String taxesSurchargesOps;

    /**
     * 销售费用
     */
    @ApiModelProperty("销售费用")
    private String sellingDistExp;

    /**
     * 管理费用
     */
    @ApiModelProperty("管理费用")
    private String gerlAdminExp;

    /**
     * 研发费用
     */
    @ApiModelProperty("研发费用")
    private String rdExp;

    /**
     * 财务费用
     */
    @ApiModelProperty("财务费用")
    private String finExpIs;

    /**
     * 其中：利息费用
     */
    @ApiModelProperty("其中：利息费用")
    private String finIntExp;

    /**
     * 减：利息收入
     */
    @ApiModelProperty("减：利息收入")
    private String finIntInc;


    /**
     * 其他业务成本（金融类）
     *
     * @return 利息支出 + 手续费及佣金支出 + 退保金 + 赔付支出净额 + 提取保险合同准备金净额 + 保单红利支出 + 分保费用
     */
    @ApiModelProperty("其他业务成本（金融类）")
    public String otherBusinessCosts;

    /**
     * 利息支出
     */
    @ApiModelProperty("利息支出")
    private String intExp;

    /**
     * 手续费及佣金支出
     */
    @ApiModelProperty("手续费及佣金支出")
    private String handlingChrgCommExp;

    /**
     * 退保金
     */
    @ApiModelProperty("退保金")
    private String prepaySurr;

    /**
     * 赔付支出净额
     */
    @ApiModelProperty("赔付支出净额")
    private String netClaimExp;

    /**
     * 提取保险合同准备金净额
     */
    @ApiModelProperty("提取保险合同准备金净额")
    private String netInsurContRsrv;

    /**
     * 保单红利支出
     */
    @ApiModelProperty("保单红利支出")
    private String dvdExpInsured;

    /**
     * 分保费用
     */
    @ApiModelProperty("分保费用")
    private String reinsuranceExp;

    /**
     * 加：其他收益
     */
    @ApiModelProperty("加：其他收益")
    private String otherGrantsInc;

    /**
     * 投资净收益
     */
    @ApiModelProperty("投资净收益")
    private String netInvestInc;

    /**
     * 其中：对联营企业和合营企业的投资收益
     */
    @ApiModelProperty("其中：对联营企业和合营企业的投资收益")
    private String incInvestAssocJvEntp;

    /**
     * 以摊余成本计量的金融资产终止确认收益
     */
    @ApiModelProperty("以摊余成本计量的金融资产终止确认收益")
    private String terFinAssIncome;

    /**
     * 净敞口套期收益
     */
    @ApiModelProperty("净敞口套期收益")
    private String netExposureHedgeBen;

    /**
     * 公允价值变动净收益
     */
    @ApiModelProperty("公允价值变动净收益")
    private String netGainChgFv;

    /**
     * 资产减值损失
     */
    @ApiModelProperty("资产减值损失")
    private String impairLossAssets;

    /**
     * 信用减值损失
     */
    @ApiModelProperty("信用减值损失")
    private String creditImpairLoss;

    /**
     * 资产处置收益
     */
    @ApiModelProperty("资产处置收益")
    private String gainAssetDispositions;

    /**
     * 汇兑净收益
     */
    @ApiModelProperty("汇兑净收益")
    private String netGainFxTrans;

    /**
     * 加：营业利润差额（特殊报表科目）
     */
    @ApiModelProperty("加：营业利润差额（特殊报表科目）")
    private String opprofitGap;

    /**
     * 营业利润差额（合计平衡项目）
     */
    @ApiModelProperty("营业利润差额（合计平衡项目）")
    private String opprofitNetting;

    /**
     * 营业利润
     */
    @ApiModelProperty("营业利润")
    private String opprofit;

    /**
     * 加：营业外收入
     */
    @ApiModelProperty("加：营业外收入")
    private String nonOperRev;

    /**
     * 减：营业外支出
     */
    @ApiModelProperty("减：营业外支出")
    private String nonOperExp;

    /**
     * 其中：非流动资产处置净损失
     */
    @ApiModelProperty("其中：非流动资产处置净损失")
    private String netLossDispNoncurAsset;

    /**
     * 加：利润总额差额（特殊报表科目）
     */
    @ApiModelProperty("加：利润总额差额（特殊报表科目）")
    private String profitGap;

    /**
     * 利润总额差额（合计平衡项目）
     */
    @ApiModelProperty("利润总额差额（合计平衡项目）")
    private String profitNetting;

    /**
     * 利润总额
     */
    @ApiModelProperty("利润总额")
    private String totProfit;

    /**
     * 减：所得税
     */
    @ApiModelProperty("减：所得税")
    private String tax;

    /**
     * 加：未确认的投资损失
     */
    @ApiModelProperty("加：未确认的投资损失")
    private String unconfirmedInvestLossIs;

    /**
     * 加：净利润差额（特殊报表科目）
     */
    @ApiModelProperty("加：净利润差额（特殊报表科目）")
    private String netProfitIsGap;

    /**
     * 净利润差额（合计平衡项目）
     */
    @ApiModelProperty("净利润差额（合计平衡项目）")
    private String netProfitIsNetting;

    /**
     * 净利润
     */
    @ApiModelProperty("净利润")
    private String netProfitIs;

    /**
     * 持续经营净利润
     */
    @ApiModelProperty("持续经营净利润")
    private String netProfitContinued;

    /**
     * 终止经营净利润
     */
    @ApiModelProperty("终止经营净利润")
    private String netProfitDiscontinued;

    /**
     * 减：少数股东损益
     */
    @ApiModelProperty("减：少数股东损益")
    private String minorityIntInc;

    /**
     * 归属于母公司所有者的净利润
     */
    @ApiModelProperty("归属于母公司所有者的净利润")
    private String npBelongtoParcomsh;

    /**
     * 加：其他综合收益
     */
    @ApiModelProperty("加：其他综合收益")
    private String otherComprehInc;

    /**
     * 综合收益总额
     */
    @ApiModelProperty("综合收益总额")
    private String totComprehInc;

    /**
     * 减：归属于少数股东的综合收益总额
     */
    @ApiModelProperty("减：归属于少数股东的综合收益总额")
    private String totComprehIncMinShrhldr;

    /**
     * 归属于母公司普通股东综合收益总额
     */
    @ApiModelProperty("归属于母公司普通股东综合收益总额")
    private String totComprehIncParentComp;

    /**
     * 基本每股收益
     */
    @ApiModelProperty("基本每股收益")
    private String epsBasicIs;

    /**
     * 稀释每股收益
     */
    @ApiModelProperty("稀释每股收益")
    private String epsDilutedIs;

    @ApiModelProperty("定期报告实际披露日期")
    private String stmIssuingdate;

    @ApiModelProperty("报告起始日期")
    private String stmRptS;

    @ApiModelProperty("报告截止日期")
    private String stmRptE;
}
