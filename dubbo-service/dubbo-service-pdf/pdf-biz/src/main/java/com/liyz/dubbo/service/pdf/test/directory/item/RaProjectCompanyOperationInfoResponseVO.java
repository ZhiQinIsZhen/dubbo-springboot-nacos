package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:经营信息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/20 11:27
 */
@Getter
@Setter
public class RaProjectCompanyOperationInfoResponseVO implements Serializable {
    private static final long serialVersionUID = 4447634626102438064L;

    @ApiModelProperty("三条红线、关键财务指标、备注信息")
    private RaProjectThreeRedLineResponseVO threeRedLine;

    @ApiModelProperty("天眼查-资产负债表")
    private List<RaProjectTycFinancialResponseVO> tycFinancial;

    @ApiModelProperty("excel(港版)-资产负债表")
    private List<RaProjectExcelHkFinancialResponseVO> excelHkFinancial;

    @ApiModelProperty("excel(国版)-资产负债表")
    private List<RaProjectExcelFinancialResponseVO> excelFinancial;

    @ApiModelProperty("万德(国版)-资产负债表")
    private List<RaProjectWdFinancialResponseVO> wdFinancial;

    @ApiModelProperty("万德(港版)-资产负债表")
    private List<RaProjectWdHkFinancialResponseVO> wdHkFinancial;

    @ApiModelProperty("天眼查-利润表")
    private List<RaProjectTycProfitResponseVO> tycProfit;

    @ApiModelProperty("excel(港版)-利润表")
    private List<RaProjectExcelHkProfitResponseVO> excelHkProfit;

    @ApiModelProperty("excel(国版)-利润表")
    private List<RaProjectExcelProfitResponseVO> excelProfit;

    @ApiModelProperty("万德(国版)-利润表")
    private List<RaProjectWdProfitResponseVO> wdProfit;

    @ApiModelProperty("万德(港版)-利润表")
    private List<RaProjectWdHkProfitResponseVO> wdHkProfit;

    @ApiModelProperty("天眼查-现金流量表")
    private List<RaProjectTycCashFlowResponseVO> tycCashFlow;

    @ApiModelProperty("excel(港版)-现金流量表")
    private List<RaProjectExcelHkCashFlowResponseVO> excelHkCashFlow;

    @ApiModelProperty("excel(国版)-现金流量表")
    private List<RaProjectExcelCashFlowResponseVO> excelCashFlow;

    @ApiModelProperty("万德(港版)-现金流量表")
    private List<RaProjectWdHkCashFlowResponseVO> wdHkCashFlow;

    @ApiModelProperty("万德(国版)-现金流量表")
    private List<RaProjectWdCashFlowResponseVO> wdCashFlow;

    @ApiModelProperty("财务数据excel文件以及是否是从三方获取")
    private RaProjectFinancialExtResponseVO financialExt;

    /**
     * 欠税公告
     */
    @ApiModelProperty("欠税公告")
    private List<SaProjectGroupTable<RaProjectMrOwnTaxResponseVO>> mrOwnTaxes;

    /**
     * 税收违法
     */
    @ApiModelProperty("税收违法")
    private List<SaProjectGroupTable<RaProjectTaxContraventionResponseVO>> taxContraventions;

    /**
     * 简易注销
     */
    @ApiModelProperty("简易注销")
    private List<SaProjectGroupTable<RaProjectBriefCancelResponseVO>> briefCancels;

    /**
     * 司法拍卖
     */
    @ApiModelProperty("司法拍卖")
    private List<SaProjectGroupTable<RaProjectJudicialSaleResponseVO>> judicialSales;

    /**
     * 动产抵押
     */
    @ApiModelProperty("动产抵押")
    private List<SaProjectGroupTable<RaProjectMortgageResponseVO>> mortgages;

    /**
     * 土地抵押
     */
    @ApiModelProperty("土地抵押")
    private List<SaProjectGroupTable<RaProjectLandMortgageResponseVO>> landMortgages;

    /**
     * 股权出资
     */
    @ApiModelProperty("股权出资")
    private List<SaProjectGroupTable<RaProjectEquityResponseVO>> equities;

    /**
     * 股东质押明细
     */
    @ApiModelProperty("股东质押明细")
    private List<SaProjectGroupTable<RaProjectHolderPledgeResponseVO>> holderPledges;

    /**
     * 融资及担保信息描述
     */
    @ApiModelProperty("融资及担保信息描述")
    private RaProjectFinancingGuaranteeResponseVO financingGuarantee;

    /**
     * 公司授信额度
     */
    @ApiModelProperty("公司授信额度")
    private List<RaProjectCompanyCreditLineResponseVO> companyCreditLines;


    @ApiModelProperty("债券担保")
    private List<RaProjectBondGuaranteeDataResponseVO> bondGuaranteeDatas;


    @ApiModelProperty("银行借款担保")
    private List<RaProjectGuaranteeDataResponseVO> bankGuaranteeDatas;

    @ApiModelProperty("融资租赁担保")
    private List<RaProjectGuaranteeDataResponseVO> leaseGuaranteeDatas;

    @ApiModelProperty("信托融资担保")
    private List<RaProjectGuaranteeDataResponseVO> trustGuaranteeDatas;

    @ApiModelProperty("对外担保概览")
    private List<RaProjectGuaranteeOverviewResponseVO> guaranteeOverviews;

    @ApiModelProperty("土储分析")
    private RaProjectSoilStorageAnalysisResponseVO soilStorageAnalysis;
}
