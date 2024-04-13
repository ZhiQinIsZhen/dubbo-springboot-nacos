package com.liyz.dubbo.service.pdf.test.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import com.liyz.dubbo.service.pdf.test.directory.item.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/17 10:26
 */
@Getter
@Setter
@JsonIgnoreProperties
public class TestBO extends ProjectPdfBaseVO implements Serializable {
    private static final long serialVersionUID = 1173715877598416861L;

    private RapPdfRiskScanVO riskScan;

    /**
     * 评估结果
     */
    private ResultBO result;

    /**
     * 项目方信息
     */
    private ProjectPartyInfoBO projectPartyInfo;

    /**
     * 项目信息
     */
    private RapPdfProjectInformationVO projectInfo;

    /**
     * 经营状况
     */
    private RapPdfOcVO oc;

    /**
     * 商票情况
     */
    private RapPdfCommercialTicketVO commercialTicket;

    /**
     * 债券及信用评级信息
     */
    private RapPdfBondVO bond;

    /**
     * 法律风险
     */
    private RapPdfLegalRiskVO legalRisk;

    /**
     * 关联风险
     */
    private RapPdfAssociatedRiskVO associatedRisk;

    /**
     * 城投分析
     */
    private RapPdfCityAnalysisVO cityAnalysis;

    /**
     * 新闻舆情
     */
    private RapPdfNewsNoticeVO newsNotice;

    /**
     * @param result
     * @param complexList
     */
    public void putResult(RaProjectEvaluationResponseVO result, List<ComprehensiveAnalysisBO> complexList) {
        this.result = new ResultBO();
        if (Objects.isNull(result) && CollectionUtils.isEmpty(complexList)) {
            return;
        }
        if (Objects.nonNull(result)) {
            this.result.setReportNo(result.getTaskNumber());
            this.result.setCorpName(result.getName());
            this.result.setSocialCode(result.getUniformCreditCode());
            this.result.setOriginateCorpName(result.getSupplierName());
            FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd");
            this.result.setApplyDate(
                    Optional.ofNullable(result.getStartTime()).map(fastDateFormat::format).orElse("")
            );
            this.result.setGenDate(
                    Optional.ofNullable(result.getResultTime()).map(fastDateFormat::format).orElse("")
            );
            this.result.setResult(result.getResult());
        }
        if (!CollectionUtils.isEmpty(complexList)) {

            ComplexStructureBO complexStructure = new ComplexStructureBO();
            complexStructure.putComplexStructure(complexList);
            this.result.setComplexStructure(complexStructure);
        }
    }

    /**
     * @param in
     */
    public void putCommercialTicket(RaProjectCommercialTicketInfoResponseVO in) {
        this.commercialTicket = new RapPdfCommercialTicketVO();
        if (Objects.isNull(in)) {
            return;
        }
        this.commercialTicket.setLabels(in.getLabels());
        this.commercialTicket.setCompanyName(in.getCompanyName());
        this.commercialTicket.setOverView(
                new RapPdfCommercialTicketVO.Overview().put(in.getRaProjectCommercialTicketOverview())
        );
        this.commercialTicket.setCensusList(in.getCommercialTicketResponses());

    }

    /**
     * @param in
     */
    public void putBond(RaProjectBondResponseVO in) {
        this.bond = new RapPdfBondVO();
        if (Objects.isNull(in)) {
            return;
        }
        this.bond.setBondInfo(in);
        this.bond.setCompanyName(in.getCompanyName());
        this.bond.setLabels(in.getLabels());
        this.bond.setContentInfo(in.getContentInfo());
        this.bond.setBondDetailList(in.getRaProjectBondDetailResponses());
        this.bond.setContentDefault(in.getContentDefault());
        this.bond.setDefaultList(in.getRaProjectBondDefaultResponses());
        this.bond.setCreditRatingList(in.getRaProjectCreditRatingResponses());
    }

    /**
     * @param in
     */
    public void putLegalRisk(RaProjectJudicialRiskResponseVO in) {
        this.legalRisk = new RapPdfLegalRiskVO();
        if (Objects.isNull(in)) {
            return;
        }

        this.legalRisk.setLawSuitList(in.getLawSuitResponseList());
        this.legalRisk.setKtannouncementList(in.getKtannouncementResponseList());
        this.legalRisk.setZhixinginfoList(in.getZhixinginfoResponseList());
        this.legalRisk.setDishonestList(in.getDishonestResponseList());
        this.legalRisk.setConsumptionRestrictionList(in.getConsumptionRestrictionResponseList());
        this.legalRisk.setJudicialRecord(in.getJudicialRecord());
    }


    /**
     * @param in
     */
    public void putNewsNotice(RaProjectNewsNoticeResponseVO in) {
        this.newsNotice = new RapPdfNewsNoticeVO();
        if (Objects.isNull(in)) {
            return;
        }
        this.newsNotice.setNewsList(in.getRaProjectNewsResponses());
        this.newsNotice.setNoticeList(in.getRaProjectNoticeResponses());
        this.newsNotice.setIndustryTrendsList(in.getRaProjectIndustryTrendsResponses());
    }

    /**
     * @param urbanInvestment
     */
    public void putUrbanInvestment(RaProjectUrbanInvestmentResponseVO urbanInvestment) {

        this.cityAnalysis = new RapPdfCityAnalysisVO();
        if (Objects.isNull(urbanInvestment)) {
            return;
        }

        this.cityAnalysis.setRegionStructure(RaProjectRegionStructure.buildRaProjectRegionStructure(urbanInvestment.getRegionInfo(), urbanInvestment.getRegionDetails()));

        GovGrantsTableVO grantsTableVO = new GovGrantsTableVO();
        grantsTableVO.put(urbanInvestment.getGovGrants());
        this.cityAnalysis.setTableGovGrants(grantsTableVO);

        InterestMarginsTableVO interestMarginsTableVO = new InterestMarginsTableVO();
        interestMarginsTableVO.put(urbanInvestment.getInterestMargins());
        this.cityAnalysis.setTableTnterestMargins(interestMarginsTableVO);

        DurationsTableVO durationsTableVO = new DurationsTableVO();
        durationsTableVO.put(urbanInvestment.getDurations());
        this.cityAnalysis.setTableDurations(durationsTableVO);

    }



    /**
     * @return
     */
    public Integer riskCheckRestriction() {
        Integer ifCheck = 0;
        List<SaProjectGroupTable<ConsumptionRestrictionResponseVO>> ls = Optional.ofNullable(this.legalRisk).map(RapPdfLegalRiskVO::getConsumptionRestrictionList)
                .orElse(Lists.newArrayList());
        for (SaProjectGroupTable<ConsumptionRestrictionResponseVO> aa : ls) {
            List<ConsumptionRestrictionResponseVO> gTableList = aa.getGTableList();
            if (!CollectionUtils.isEmpty(gTableList)) {
                ConsumptionRestrictionResponseVO vo = gTableList.stream().filter(a -> Integer.valueOf(1).equals(a.getRiskMark())).findFirst().orElse(null);
                if (Objects.nonNull(vo)) {
                    ifCheck = 1;
                    break;
                }
            }
        }
        return ifCheck;
    }

    /**
     * @return
     */
    public Integer riskCheckDishonest() {
        Integer ifCheck = 0;
        List<SaProjectGroupTable<DishonestResponseVO>> ls = Optional.ofNullable(this.legalRisk).map(RapPdfLegalRiskVO::getDishonestList)
                .orElse(Lists.newArrayList());
        for (SaProjectGroupTable<DishonestResponseVO> aa : ls) {
            List<DishonestResponseVO> gTableList = aa.getGTableList();
            if (!CollectionUtils.isEmpty(gTableList)) {
                DishonestResponseVO vo = gTableList.stream().filter(a -> Integer.valueOf(1).equals(a.getRiskMark())).findFirst().orElse(null);
                if (Objects.nonNull(vo)) {
                    ifCheck = 1;
                    break;
                }
            }
        }
        return ifCheck;
    }

    /**
     * @return
     */
    public Integer riskCheckZhixingin() {
        Integer ifCheck = 0;
        List<SaProjectGroupTable<ZhixinginfoResponseVO>> ls = Optional.ofNullable(this.legalRisk).map(RapPdfLegalRiskVO::getZhixinginfoList)
                .orElse(Lists.newArrayList());
        for (SaProjectGroupTable<ZhixinginfoResponseVO> aa : ls) {
            List<ZhixinginfoResponseVO> gTableList = aa.getGTableList();
            if (!CollectionUtils.isEmpty(gTableList)) {
                ZhixinginfoResponseVO vo = gTableList.stream().filter(a -> Integer.valueOf(1).equals(a.getRiskMark())).findFirst().orElse(null);
                if (Objects.nonNull(vo)) {
                    ifCheck = 1;
                    break;
                }
            }
        }
        return ifCheck;
    }

    /**
     * @return
     */
    public Integer riskCheckNews() {
        Integer ifCheck = 0;
        List<SaProjectGroupTable<RaProjectNewsResponseVO>> ls = Optional.ofNullable(this.newsNotice).map(RapPdfNewsNoticeVO::getNewsList).orElse(Lists.newArrayList());
        for (SaProjectGroupTable<RaProjectNewsResponseVO> aa : ls) {
            List<RaProjectNewsResponseVO> gTableList = aa.getGTableList();
            if (!CollectionUtils.isEmpty(gTableList)) {
                RaProjectNewsResponseVO vo = gTableList.stream().filter(a -> Integer.valueOf(1).equals(a.getRiskMark())).findFirst().orElse(null);
                if (Objects.nonNull(vo)) {
                    ifCheck = 1;
                    break;
                }
            }
            return ifCheck;
        }


        return ifCheck;
    }

    /**
     * @return
     */
    public Integer riskCheckNotice() {
        Integer ifCheck = 0;
        List<SaProjectGroupTable<RaProjectNoticeResponseVO>> ls = Optional.ofNullable(this.newsNotice).map(RapPdfNewsNoticeVO::getNoticeList)
                .orElse(Lists.newArrayList());
        for (SaProjectGroupTable<RaProjectNoticeResponseVO> aa : ls) {
            List<RaProjectNoticeResponseVO> gTableList = aa.getGTableList();
            if (!CollectionUtils.isEmpty(gTableList)) {
                RaProjectNoticeResponseVO vo = gTableList.stream().filter(a -> Integer.valueOf(1).equals(a.getRiskMark())).findFirst().orElse(null);
                if (Objects.nonNull(vo)) {
                    ifCheck = 1;
                    break;
                }
            }
        }
        return ifCheck;
    }

    /**
     * @param riskType
     * @param riskTypeTarget
     * @return
     */
    public Integer riskCheckWithType(String riskType, String riskTypeTarget) {

        Integer ifCheck = Optional.ofNullable(this.riskScan).map(p -> p.withTypeRisk(riskType))
                .map(RaProjectRiskScanResponseVO::getDetails).map(
                        p -> p.stream().filter(a -> riskTypeTarget.equals(a.getRiskTypeTarget())).findFirst().orElse(null)
                )
                .map(RaProjectRiskScanResponseVO.RaProjectRiskDetailResponseVO::getRiskQuan)
                .orElse(0);

        return ifCheck;
    }

    @Override
    public String getCorpName() {
        return result.getCorpName();
    }

    @Override
    public String getReportNo() {
        return result.getReportNo();
    }

    @Override
    public String getOriginateCorpName() {
        return result.getOriginateCorpName();
    }

    @Override
    public String getOriginateCorpLogo() {
        return result.getOriginateCorpLogo();
    }

    @Override
    public String getGenDate() {
        return result.getGenDate();
    }

    @Override
    public String getApplyDate() {
        return result.getApplyDate();
    }
}
