package com.liyz.dubbo.service.pdf.utils;

import com.alibaba.fastjson.JSON;
import com.liyz.dubbo.common.util.DateUtil;
import com.liyz.dubbo.service.pdf.exception.PdfExceptionCodeEnum;
import com.liyz.dubbo.service.pdf.exception.RemotePdfServiceException;
import com.liyz.dubbo.service.pdf.svg.*;
import com.liyz.dubbo.service.pdf.svg.chinamap.ChinaMapChart;
import com.liyz.dubbo.service.pdf.test.directory.item.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Description
 * @Author ChenHao
 * @Date 2022/6/13 9:20
 */
@Slf4j
public class RapSvgUtils {
    private static final Color PURPLE = Color.decode("#776DE6");
    private static final Color SKY_BLUE = Color.decode("#4FA9EB");
    private static final Color SKY_BLUE2 = Color.decode("#33BED1");
    private static final Color RED = Color.decode("#FF8047");
    private static final Color RED2 = Color.decode("#F55B77");


    /**
     * @param regionIndicator
     * @return
     */
    public static String genRegionIndicatorSvg(RegionIndicator regionIndicator) {
        if (regionIndicator == null) {
            return "";
        }
        List<String> titleList = regionIndicator.getTitleList();

        List<BigDecimal> dataList = regionIndicator.getDataList();
        if (CollectionUtils.isEmpty(titleList) || CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            // 提取数据
            int size = titleList.size();
            // 主营业务收入（万元）
            BigDecimal[] mainBusiness = new BigDecimal[size];
            RegionIndicatorSvg.RegionIndicatorSvgData data = new RegionIndicatorSvg.RegionIndicatorSvgData();
            for (int i = 0; i < titleList.size(); i++) {
                BigDecimal main = Optional.ofNullable(dataList.get(i)).orElse(null);
                mainBusiness[i] = main;
                data.addBucket(mainBusiness[i], titleList.get(i));
            }

            RegionIndicatorSvg svgBuilder = new RegionIndicatorSvg(data, regionIndicator.getYLabels()[0]);
            regionIndicator.setTitleList(titleList);
            String svgInfo = svgBuilder.genSvg();
            regionIndicator.setSvg(svgInfo);
            return svgInfo;
//            NumberBarAndLineChartParam barAndLineChartParam = new NumberBarAndLineChartParam();
//            barAndLineChartParam.setTitle("");
//
//            barAndLineChartParam.setWidth((int) PdfUtils.pt2px(240));
//            barAndLineChartParam.setHeight((int) PdfUtils.pt2px(146));
//
//            barAndLineChartParam.setYLabels(regionIndicator.getYLabels());
//
//            // barAndLineChartParam.setXColumnLabels(new String[]{"2017年", "2018年", "2019年", "2020年", "2021年", "2022年"});
//            barAndLineChartParam.setXColumnLabels(titleList.toArray(new String[0]));
//            // 折线
//            barAndLineChartParam.setLineRowKeys(regionIndicator.getLineRowKeys());
//            barAndLineChartParam.setLineSerialsPaintColors(new Color[]{RED});
//            barAndLineChartParam.setLineDecimalData(new BigDecimal[][]{
//                    mainBusiness
//            });
//
//            String svgInfo = BarAndLineChartGenUtil.generateSVG(barAndLineChartParam);
//            regionIndicator.setTitleList(titleList);
//            regionIndicator.setSvg(svgInfo);
//            return svgInfo;
        } catch (Exception e) {
            log.error("生成区域指标SVG异常。参数:" + JSON.toJSONString(regionIndicator), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

    /**
     * 财务数据-营运能力指标
     */
    public static String genOcIndicatorSvg(TableVO<OperationYearDetailResponseVO> table) {
        if (table == null) {
            return "";
        }
        List<String> titleList = table.getTitleList();
        List<OperationYearDetailResponseVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(titleList) || CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            // 提取数据
            int size = titleList.size();
            // 主营业务收入（万元）
            BigDecimal[] mainBusiness = new BigDecimal[size];
            // 税前利润（万元）
            BigDecimal[] profitBeforeTax = new BigDecimal[size];
            // 净利润率
            BigDecimal[] operatingNetProfitRatio = new BigDecimal[size];
            OcIndicatorSvg.OcIndicatorSvgData data = new OcIndicatorSvg.OcIndicatorSvgData();
            for (int i = 0; i < titleList.size(); i++) {
                Optional<OperationYearDetailResponseVO> opt = Optional.ofNullable(dataList.get(i));
                // 主营业务收入
                BigDecimal main = opt.map(OperationYearDetailResponseVO::getBusinessIncome)
                        .orElse(null);
                mainBusiness[i] = main;
                // 税前利润
                profitBeforeTax[i] = opt.map(OperationYearDetailResponseVO::getProfitBeforeTax)
                        .orElse(null);
                // 净利润率
                operatingNetProfitRatio[i] = opt.map(OperationYearDetailResponseVO::getSaleNetProfitRatio)
                        .orElse(null);
                data.addBucket(mainBusiness[i], profitBeforeTax[i], operatingNetProfitRatio[i], titleList.get(i));
            }
            OcIndicatorSvg svgBuilder = new OcIndicatorSvg(data);
            String svgInfo = svgBuilder.genSvg();
            table.setSvg(svgInfo);
            return svgInfo;
//            if(!(isValidData(mainBusiness) || isValidData(operatingNetProfit) || isValidData(operatingNetProfitRatio))){
//                return "";
//            }
//            NumberBarAndLineChartParam barAndLineChartParam = new NumberBarAndLineChartParam();
//            barAndLineChartParam.setTitle("");
//            barAndLineChartParam.setWidth((int) PdfUtils.pt2px(747));
//            barAndLineChartParam.setHeight((int) PdfUtils.pt2px(289));
//            barAndLineChartParam.setYLabels(new String[]{"单位：万元", "单位：%"});
//            // barAndLineChartParam.setXColumnLabels(new String[]{"2017年", "2018年", "2019年", "2020年", "2021年", "2022年"});
//            barAndLineChartParam.setXColumnLabels(titleList.toArray(new String[0]));
//            // 柱状
//            barAndLineChartParam.setBarRowKeys(new String[]{"营业收入（万元）", "税前利润（万元）"});
//            barAndLineChartParam.setBarSerialsPaintColors(new Color[]{PURPLE, SKY_BLUE});
//            barAndLineChartParam.setBarDecimalData(new BigDecimal[][]{
//                    mainBusiness, profitBeforeTax
//            });
//            // 折线
//            barAndLineChartParam.setLineRowKeys(new String[]{"销售净利率（%）"});
//            barAndLineChartParam.setLineSerialsPaintColors(new Color[]{RED});
//            barAndLineChartParam.setLineDecimalData(new BigDecimal[][]{
//                    operatingNetProfitRatio
//            });
//
//            String svgInfo = BarAndLineChartGenUtil.generateSVG(barAndLineChartParam);
//            table.setSvg(svgInfo);
//            return svgInfo;
        } catch (Exception e) {
            log.error("生成运营能力指标SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

    /**
     * 财务数据-偿债能力指标
     */
    public static final String genSolvencyIndicatorSvg(TableVO<RepaymentYearDetailResponseVO> table) {
        if (table == null) {
            return "";
        }
        List<String> titleList = table.getTitleList();
        List<RepaymentYearDetailResponseVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(titleList) || CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            // 提取数据
            int size = titleList.size();
            // 流动比率
            BigDecimal[] currentRatio = new BigDecimal[size];
            // 速动比率
            BigDecimal[] quickRatio = new BigDecimal[size];
            // 资产负债率
            BigDecimal[] assetLiabilityRatio = new BigDecimal[size];
            //已获利息倍数
            BigDecimal[] interestEarned = new BigDecimal[size];
            SolvencyIndicatorSvg.SolvencyIndicatorSvgData data = new SolvencyIndicatorSvg.SolvencyIndicatorSvgData();
            for (int i = 0; i < titleList.size(); i++) {
                Optional<RepaymentYearDetailResponseVO> opt = Optional.ofNullable(dataList.get(i));
                currentRatio[i] = opt.map(RepaymentYearDetailResponseVO::getLiquidRatio)
                        .orElse(null)
//                        // 去掉%，除以100
//                        .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
                ;
                quickRatio[i] = opt.map(RepaymentYearDetailResponseVO::getQuickRatio)
                        .orElse(null)
//                        // 去掉%，除以100
//                        .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
                ;
                assetLiabilityRatio[i] = opt.map(RepaymentYearDetailResponseVO::getAssetLiabilityRatio)
                        .orElse(null)
                ;
                interestEarned[i] = opt.map(RepaymentYearDetailResponseVO::getInterestEarned)
                        .orElse(null)
                ;
                data.addBucket(currentRatio[i], quickRatio[i], assetLiabilityRatio[i], interestEarned[i], titleList.get(i));
            }
            SolvencyIndicatorSvg svgBuilder = new SolvencyIndicatorSvg(data);
            String svgInfo = svgBuilder.genSvg();
            table.setSvg(svgInfo);
            return svgInfo;
//            if(!(isValidData(currentRatio) || isValidData(quickRatio) || isValidData(assetLiabilityRatio))){
//                return "";
//            }
//            LineAndLineChartParam lineAndLineChartParam = new LineAndLineChartParam();
//            lineAndLineChartParam.setTitle("");
//            lineAndLineChartParam.setWidth((int) PdfUtils.pt2px(747));
//            lineAndLineChartParam.setHeight((int) PdfUtils.pt2px(289));
//            lineAndLineChartParam.setYLabels(new String[]{"比率", "单位：%"});
//            // barAndLineChartParam.setXColumnLabels(new String[]{"2017年", "2018年", "2019年", "2020年", "2021年", "2022年"});
//            lineAndLineChartParam.setXColumnLabels(titleList.toArray(new String[0]));
//            // 折线
//            lineAndLineChartParam.setLineLeftRowKeys(new String[]{"流动比率", "速动比率", "资产负债率"});
//            lineAndLineChartParam.setLineLeftSerialsPaintColors(new Color[]{SKY_BLUE2, SKY_BLUE, RED});
//            lineAndLineChartParam.setLineLeftData(new BigDecimal[][]{
//                    currentRatio, quickRatio, assetLiabilityRatio
//            });
//            // 折线
//            lineAndLineChartParam.setLineRightRowKeys(new String[]{"已获利息倍数"});
//            lineAndLineChartParam.setLineRightSerialsPaintColors(new Color[]{PURPLE});
//            lineAndLineChartParam.setLineRightData(new BigDecimal[][]{
//                    interestEarned
//            });
//
//            String svgInfo = LineAndLineChartGenUtil.generateSVG(lineAndLineChartParam);
//            table.setSvg(svgInfo);
//            return svgInfo;
        } catch (Exception e) {
            log.error("生成偿债能力指标SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

    /**
     * 生成授信图片svg
     *
     * @param table
     */
    public static final String genCompanyCreditLinesSvg(CreditGuarantee.CompanyCreditLines table) {

        if (table == null) {
            return "";
        }

        List<RaProjectCompanyCreditLineResponseVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            int size = dataList.size();
            List<String> titleList = new ArrayList<>(size);

            BigDecimal[] creditLine = new BigDecimal[size];
            BigDecimal[] creditLineUsed = new BigDecimal[size];
            BigDecimal[] creditLineUnused = new BigDecimal[size];
            CompanyCreditLinesSvg.CompanyCreditLinesSvgData data = new CompanyCreditLinesSvg.CompanyCreditLinesSvgData();
            for (int i = 0; i < size; i++) {
                Optional<RaProjectCompanyCreditLineResponseVO> opt = Optional.ofNullable(dataList.get(i));
                titleList.add(opt.map(RaProjectCompanyCreditLineResponseVO::getEndDate).orElse("--"));
                creditLine[i] = opt.map(RaProjectCompanyCreditLineResponseVO::getCreditLine)
                        .orElse(null)
                ;
                creditLineUsed[i] = opt.map(RaProjectCompanyCreditLineResponseVO::getCreditLineUsed)
                        .orElse(null)
                ;
                creditLineUnused[i] = opt.map(RaProjectCompanyCreditLineResponseVO::getCreditLineUnused)
                        .orElse(null)
                ;
                data.addBucket(creditLine[i], creditLineUsed[i], creditLineUnused[i], titleList.get(i));
            }
            CompanyCreditLinesSvg svgBuilder = new CompanyCreditLinesSvg(data);
            String svgInfo = svgBuilder.genSvg();
            table.setSvg(svgInfo);
            return svgInfo;
//            NumberBarAndLineChartParam barAndLineChartParam = new NumberBarAndLineChartParam();
//            barAndLineChartParam.setTitle("");
//            barAndLineChartParam.setWidth((int) PdfUtils.pt2px(747));
//            barAndLineChartParam.setHeight((int) PdfUtils.pt2px(322));
//            barAndLineChartParam.setYLabels(new String[]{"单位:亿元"});
//            barAndLineChartParam.setXColumnLabels(titleList.toArray(new String[0]));
//            // 柱状
//            barAndLineChartParam.setLineRowKeys(new String[]{"授信额度", "已使用", "未使用"});
//            barAndLineChartParam.setLineSerialsPaintColors(new Color[]{PURPLE, SKY_BLUE, RED});
//            barAndLineChartParam.setLineDecimalData(new BigDecimal[][]{
//                    creditLine, creditLineUsed, creditLineUnused
//            });
//            barAndLineChartParam.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
//
//            String svgInfo = BarAndLineChartGenUtil.generateSVG(barAndLineChartParam);
//            table.setSvg(svgInfo);
//            return svgInfo;
        } catch (Exception e) {
            log.error("生成授信指标SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

    /**
     * 队伍担保svg
     *
     * @param table
     */
    public static final String genGuaranteeOverviewSvg(CreditGuarantee.GuaranteeOverviews table) {


        if (table == null) {
            return "";
        }

        List<RaProjectGuaranteeOverviewResponseVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            int size = dataList.size();
            List<String> titleList = new ArrayList<>(size);

            BigDecimal[] amountSum = new BigDecimal[size];
            BigDecimal[] num = new BigDecimal[size];
            GuaranteeOverviewSvg.GuaranteeOverviewSvgData data = new GuaranteeOverviewSvg.GuaranteeOverviewSvgData();
            for (int i = 0; i < size; i++) {
                Optional<RaProjectGuaranteeOverviewResponseVO> opt = Optional.ofNullable(dataList.get(i));
                titleList.add(opt.map(RaProjectGuaranteeOverviewResponseVO::getFinanceName).orElse("--"));
                amountSum[i] = opt.map(RaProjectGuaranteeOverviewResponseVO::getAmountSum)
                        .orElse(null)
                ;
                num[i] = opt.map(RaProjectGuaranteeOverviewResponseVO::getNum)
                        .orElse(null)
                ;
                data.addBucket(amountSum[i], num[i], titleList.get(i));
            }
            GuaranteeOverviewSvg svgBuilder = new GuaranteeOverviewSvg(data);
            String svgInfo = svgBuilder.genSvg();
            table.setSvg(svgInfo);
            return svgInfo;
//            NumberBarAndLineChartParam barAndLineChartParam = new NumberBarAndLineChartParam();
//            barAndLineChartParam.setTitle("");
//            barAndLineChartParam.setWidth((int) PdfUtils.pt2px(747));
//            barAndLineChartParam.setHeight((int) PdfUtils.pt2px(289));
//            barAndLineChartParam.setYLabels(new String[]{"单位:亿元"});
//            barAndLineChartParam.setXColumnLabels(titleList.toArray(new String[0]));
//            // 柱状
//            barAndLineChartParam.setBarRowKeys(new String[]{"担保规模统计(亿元)"});
//            barAndLineChartParam.setBarSerialsPaintColors(new Color[]{PURPLE});
//            barAndLineChartParam.setBarDecimalData(new BigDecimal[][]{
//                    amountSum
//            });
//
////            // 折线
////            barAndLineChartParam.setLineRowKeys(new String[]{"担保规模统计(亿元)"});
////            barAndLineChartParam.setLineSerialsPaintColors(new Color[]{RED});
////            barAndLineChartParam.setLineData(new double[][]{
////                    amountSum
////            });
//
//            String svgInfo = BarAndLineChartGenUtil.generateSVG(barAndLineChartParam);
//            table.setSvg(svgInfo);
//            return svgInfo;
        } catch (Exception e) {
            log.error("生成对外担保指标SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }


    /**
     * @param table
     * @return
     */
    public static String genSocialStaffInfoSvg(TableVO<RaProjectSocialStaffCountResponseVO> table) {
        if (table == null) {
            return "";
        }

        List<RaProjectSocialStaffCountResponseVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            int size = dataList.size();
            List<String> titleList = new ArrayList<>(size);

            Integer[] socialStaffNums = new Integer[size];

            SocialStaffInfoSvg.SocialStaffInfoSvgData data = new SocialStaffInfoSvg.SocialStaffInfoSvgData();
            for (int i = 0; i < size; i++) {
                Optional<RaProjectSocialStaffCountResponseVO> opt = Optional.ofNullable(dataList.get(i));
                titleList.add(opt.map(RaProjectSocialStaffCountResponseVO::getYear).orElse("--"));
                socialStaffNums[i] = opt.map(RaProjectSocialStaffCountResponseVO::getSocialStaffNum)
                        .orElse(null)
                ;

                String title = titleList.get(i);
                if (Objects.isNull(socialStaffNums[i])) {
                    title = String.format("%s-%s", title, "未公示");
                }

                data.addBucket(socialStaffNums[i], title);
            }
            SocialStaffInfoSvg svgBuilder = new SocialStaffInfoSvg(data);
            table.setTitleList(titleList);
            String svgInfo = svgBuilder.genSvg();
            table.setSvg(svgInfo);
            return svgInfo;
        } catch (Exception e) {
            log.error("生成社保SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

//    /**
//     * 处理生成图片社保
//     *
//     * @param structure
//     */
//    public static String genSocialStaffInfoSvg(RaProjectSocialStaffInfoStructure structure) {
//
//
//        List<RaProjectSocialStaffCountResponseVO> ls = Optional.ofNullable(structure)
//                .map(RaProjectSocialStaffInfoResponseVO::getCountList).orElse(Lists.newArrayList());
//        if (CollectionUtils.isEmpty(ls)) {
//            return "";
//        }
//        try {
//            SocialSecurityRecords records = new SocialSecurityRecords();
//            for (RaProjectSocialStaffCountResponseVO l : ls) {
//                Optional<RaProjectSocialStaffCountResponseVO> opt = Optional.ofNullable(l);
//                Integer year = Integer.valueOf(l.getYear());
//                Integer personNum = opt.map(RaProjectSocialStaffCountResponseVO::getSocialStaffNum)
//                        .orElse(0);
//                BigDecimal yoyGrowthRate = opt.map(RaProjectSocialStaffCountResponseVO::getYoyGrowthRate)
//                        .orElse(BigDecimal.ZERO);
//                SocialSecurity ss1 = new SocialSecurity(year, personNum, yoyGrowthRate);
//                records.add(ss1);
//            }
//            SocialSecurityChart chart = new SocialSecurityChart("参保人数", "增长率");
//            SocialSecurityRecords records15 = records.stream().limit(15).collect(Collectors.toCollection(SocialSecurityRecords::new));
//            records15.setIfPassValue(true);
//            SocialSecurityRecords recordsAll = records;
//            recordsAll.setIfPassValue(true);
//            String svg15 = chart.createSvg(records15);
//            structure.setSvg(svg15);
//            if (!CollectionUtils.isEmpty(recordsAll) && recordsAll.size() > 15) {
//                String svgAll = chart.createSvg(recordsAll);
//                structure.setSvgAll(svgAll);
//            }
//            return svg15;
//        } catch (Exception e) {
//            log.error("生成社保指标SVG异常。参数:" + JSON.toJSONString(structure), e);
//            throw new BusinessException("", "生成社保指标SVG异常。");
//        }
//    }

    /**
     * @param table
     * @return
     */
    public static String genTableGovGrantsSvg(GovGrantsTableVO table) {

        if (table == null) {
            return "";
        }

        List<RaProjectBigDataGovGrantsResponseVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            int size = dataList.size();
            List<String> titleList = new ArrayList<>(size);

            BigDecimal[] totalIncome = new BigDecimal[size];
            BigDecimal[] totalProfitsLosses = new BigDecimal[size];
            BigDecimal[] totalTotal = new BigDecimal[size];
            GovGrantsSvg.GovGrantsSvgData data = new GovGrantsSvg.GovGrantsSvgData();
            for (int i = 0; i < size; i++) {
                Optional<RaProjectBigDataGovGrantsResponseVO> opt = Optional.ofNullable(dataList.get(i));
                titleList.add(opt.map(p -> String.format("%d%s", p.getYear(), p.getType())).orElse("--"));
                totalIncome[i] = opt.map(RaProjectBigDataGovGrantsResponseVO::getTotalIncome)
                        .orElse(null)
                ;
                totalProfitsLosses[i] = opt.map(RaProjectBigDataGovGrantsResponseVO::getTotalProfitsLosses)
                        .orElse(null)
                ;
                totalTotal[i] = opt.map(RaProjectBigDataGovGrantsResponseVO::getTotal)
                        .orElse(null)
                ;
                data.addBucket(totalIncome[i], totalProfitsLosses[i], totalTotal[i], titleList.get(i));
            }

            GovGrantsSvg svgBuilder = new GovGrantsSvg(data);
            table.setTitleList(titleList);
            String svgInfo = svgBuilder.genSvg();
            table.setSvg(svgInfo);
            return svgInfo;

//            NumberBarAndLineChartParam barAndLineChartParam = new NumberBarAndLineChartParam();
//            barAndLineChartParam.setTitle("");
//            barAndLineChartParam.setWidth((int) PdfUtils.pt2px(747));
//            barAndLineChartParam.setHeight((int) PdfUtils.pt2px(322));
//            barAndLineChartParam.setYLabels(new String[]{"单位:亿元", "单位:亿元"});
//            barAndLineChartParam.setXColumnLabels(titleList.toArray(new String[0]));
//            // 柱状
//            barAndLineChartParam.setBarRowKeys(new String[]{"计入递延收益", "计入当前损益"});
//            barAndLineChartParam.setBarSerialsPaintColors(new Color[]{PURPLE, SKY_BLUE});
//            barAndLineChartParam.setBarDecimalData(new BigDecimal[][]{
//                    totalIncome, totalProfitsLosses
//            });
//            // 线
//            barAndLineChartParam.setLineRowKeys(new String[]{"合计"});
//            barAndLineChartParam.setLineSerialsPaintColors(new Color[]{RED});
//            barAndLineChartParam.setLineDecimalData(new BigDecimal[][]{
//                    totalTotal
//            });
//
//            String svgInfo = BarAndLineChartGenUtil.generateSVG(barAndLineChartParam);
//            table.setTitleList(titleList);
//            table.setSvg(svgInfo);
//            return svgInfo;
        } catch (Exception e) {
            log.error("生成政府补助指标SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

    /**
     * @param table
     * @return
     */
    public static String genTableTnterestMarginsSvg(InterestMarginsTableVO table) {
        if (table == null) {
            return "";
        }

        List<RaProjectRegionInterestMarginResponseVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            dataList = dataList.stream().sorted(
                    Comparator.comparing(RaProjectRegionInterestMarginResponseVO::getInterestDate)
            ).collect(Collectors.toList());
            int size = dataList.size();
            List<String> titleList = new ArrayList<>(size);

            BigDecimal[] allBond = new BigDecimal[size];

            TnterestMarginsDurationsSvgData data = new TnterestMarginsDurationsSvgData();
            for (int i = 0; i < size; i++) {
                Optional<RaProjectRegionInterestMarginResponseVO> opt = Optional.ofNullable(dataList.get(i));
                titleList.add(opt.map(p -> DateUtil.formatDate(p.getInterestDate(), DateUtil.PATTERN_DATE)).orElse("--"));
                allBond[i] = opt.map(RaProjectRegionInterestMarginResponseVO::getAllBond)
                        .orElse(null);
                data.addBucket(allBond[i], titleList.get(i));
            }
            TnterestMarginsSvg svgBuilder = new TnterestMarginsSvg(data);
            String svgInfo = svgBuilder.genSvg();
            table.setTitleList(titleList);
            table.setSvg(svgInfo);
            return svgInfo;
//            NumberBarAndLineChartParam barAndLineChartParam = new NumberBarAndLineChartParam();
//            barAndLineChartParam.setTitle("");
//            barAndLineChartParam.setWidth((int) PdfUtils.pt2px(747));
//            barAndLineChartParam.setHeight((int) PdfUtils.pt2px(322));
//            barAndLineChartParam.setYLabels(new String[]{""});
//            barAndLineChartParam.setXColumnLabels(titleList.toArray(new String[0]));
//            // 线
//            barAndLineChartParam.setLineRowKeys(new String[]{"地域利差"});
//            barAndLineChartParam.setLineSerialsPaintColors(new Color[]{RED});
//            barAndLineChartParam.setLineDecimalData(new BigDecimal[][]{
//                    allBond
//            });
//            barAndLineChartParam.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
//            int step = size / 10;//保证有10个刻度尺
//            if (step > 1) {
//                barAndLineChartParam.setStep(step);
//            }
////            barAndLineChartParam.setItemLabelsVisible(false);
//            barAndLineChartParam.setRangeGridlinesVisible(true);
//            String svgInfo = BarAndLineChartGenUtil.generateSVG(barAndLineChartParam);
//            table.setTitleList(titleList);
//            table.setSvg(svgInfo);
//            return svgInfo;
        } catch (Exception e) {
            log.error("生成区域利差指标SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

    /**
     * @param table
     * @return
     */
    public static String genTableDurationsSvg(DurationsTableVO table) {
        if (table == null) {
            return "";
        }

        List<RaProjectRegionDurationResponseVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {

            dataList = dataList.stream().sorted(
                    Comparator.comparing(RaProjectRegionDurationResponseVO::getDurationDate)
            ).collect(Collectors.toList());
            int size = dataList.size();
            List<String> titleList = new ArrayList<>(size);

            BigDecimal[] duration = new BigDecimal[size];

            TnterestMarginsDurationsSvgData data = new TnterestMarginsDurationsSvgData();
            for (int i = 0; i < size; i++) {
                Optional<RaProjectRegionDurationResponseVO> opt = Optional.ofNullable(dataList.get(i));
                titleList.add(opt.map(p -> DateUtil.formatDate(p.getDurationDate(), DateUtil.PATTERN_DATE)).orElse("--"));
                duration[i] = opt.map(RaProjectRegionDurationResponseVO::getDuration)
                        .orElse(null)
                ;
                data.addBucket(duration[i], titleList.get(i));
            }
            DurationsSvg svgBuilder = new DurationsSvg(data);
            String svgInfo = svgBuilder.genSvg();
            table.setTitleList(titleList);
            table.setSvg(svgInfo);
            return svgInfo;
//            NumberBarAndLineChartParam barAndLineChartParam = new NumberBarAndLineChartParam();
//            barAndLineChartParam.setTitle("");
//            barAndLineChartParam.setWidth((int) PdfUtils.pt2px(747));
//            barAndLineChartParam.setHeight((int) PdfUtils.pt2px(322));
//            barAndLineChartParam.setYLabels(new String[]{""});
//            barAndLineChartParam.setXColumnLabels(titleList.toArray(new String[0]));
//            // 线
//            barAndLineChartParam.setLineRowKeys(new String[]{"地域久期"});
//            barAndLineChartParam.setLineSerialsPaintColors(new Color[]{RED});
//            barAndLineChartParam.setLineDecimalData(new BigDecimal[][]{
//                    duration
//            });
//            barAndLineChartParam.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
//            barAndLineChartParam.setItemLabelsVisible(false);
//            String svgInfo = BarAndLineChartGenUtil.generateSVG(barAndLineChartParam);
//            table.setTitleList(titleList);
//            table.setSvg(svgInfo);
//            return svgInfo;
        } catch (Exception e) {
            log.error("生成区域久期指标SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

    /**
     * @param table
     */
    public static String genGrowthIndicatorSvg(TableVO<GrowthYearDetailResponseVO> table) {

        if (table == null) {
            return "";
        }
        List<String> titleList = table.getTitleList();
        List<GrowthYearDetailResponseVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(titleList) || CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            // 提取数据
            int size = titleList.size();

            BigDecimal[] revenueRatio = new BigDecimal[size];

            BigDecimal[] momNetProfitRatio = new BigDecimal[size];

            BigDecimal[] deficitRatio = new BigDecimal[size];

            BigDecimal[] netCashRatio = new BigDecimal[size];
            GrowthIndicatorSvg.GrowthIndicatorSvgData data = new GrowthIndicatorSvg.GrowthIndicatorSvgData();
            for (int i = 0; i < titleList.size(); i++) {
                Optional<GrowthYearDetailResponseVO> opt = Optional.ofNullable(dataList.get(i));
                revenueRatio[i] = opt.map(GrowthYearDetailResponseVO::getRevenueRatio)
                        .orElse(null)
//                        // 去掉%，除以100
//                        .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
                ;
                momNetProfitRatio[i] = opt.map(GrowthYearDetailResponseVO::getMomNetProfitRatio)
                        .orElse(null)
//                        // 去掉%，除以100
//                        .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
                ;
                deficitRatio[i] = opt.map(GrowthYearDetailResponseVO::getDeficitRatio)
                        .orElse(null)
                ;
                netCashRatio[i] = opt.map(GrowthYearDetailResponseVO::getNetCashRatio)
                        .orElse(null)
                ;
                data.addBucket(revenueRatio[i], momNetProfitRatio[i], deficitRatio[i], netCashRatio[i], titleList.get(i));
            }

            GrowthIndicatorSvg svgBuilder = new GrowthIndicatorSvg(data);
            String svgInfo = svgBuilder.genSvg();
            table.setSvg(svgInfo);
            return svgInfo;
//            if(!(isValidData(currentRatio) || isValidData(quickRatio) || isValidData(assetLiabilityRatio))){
//                return "";
//            }
//            NumberBarAndLineChartParam barAndLineChartParam = new NumberBarAndLineChartParam();
//            barAndLineChartParam.setTitle("");
//            barAndLineChartParam.setWidth((int) PdfUtils.pt2px(602));
//            barAndLineChartParam.setHeight((int) PdfUtils.pt2px(228));
//            barAndLineChartParam.setYLabels(new String[]{"单位：%"});
//            // barAndLineChartParam.setXColumnLabels(new String[]{"2017年", "2018年", "2019年", "2020年", "2021年", "2022年"});
//            barAndLineChartParam.setXColumnLabels(titleList.toArray(new String[0]));
//
////            barAndLineChartParam.setLineRowKeys(new String[]{"归母公司净利润","营业总收入", "经营活动现金流量净额","归母公司净利润-扣除非经常损益"});
//            barAndLineChartParam.setLineRowKeys(new String[]{"1", "2", "3", "4"});
//            barAndLineChartParam.setLineSerialsPaintColors(new Color[]{PURPLE, SKY_BLUE2, RED2, SKY_BLUE});
//            barAndLineChartParam.setLineDecimalData(new BigDecimal[][]{
//                    momNetProfitRatio, revenueRatio, deficitRatio, netCashRatio
//            });
//
//
////            // 折线
////            barAndLineChartParam.setLineLeftRowKeys(new String[]{"归母公司净利润","营业总收入", "经营活动现金流量净额","归母公司净利润-扣除非经常损益"});
////            barAndLineChartParam.setLineLeftSerialsPaintColors(new Color[]{PURPLE,SKY_BLUE2, SKY_BLUE,RED});
////            barAndLineChartParam.setLineLeftData(new BigDecimal[][]{
////                    momNetProfitRatio, revenueRatio,netCashRatio,deficitRatio
////            });
//
//
//            String svgInfo = BarAndLineChartGenUtil.generateSVG(barAndLineChartParam);
//            table.setSvg(svgInfo);
//            return svgInfo;
        } catch (Exception e) {
            log.error("生成成长能力指标SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

    /**
     * @param table
     */
    public static String genSoilQuarterSvg(TableVO<SoilQuarterStatisticsVO> table) {
        if (table == null) {
            return "";
        }
        List<SoilQuarterStatisticsVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            int size = dataList.size();
            List<String> titleList = new ArrayList<>(size);

            BigDecimal[] amount = new BigDecimal[size];

            TakeLandAttitudeSvg.TakeLandAttitudeSvgData data = new TakeLandAttitudeSvg.TakeLandAttitudeSvgData();
            for (int i = 0; i < size; i++) {
                Optional<SoilQuarterStatisticsVO> opt = Optional.ofNullable(dataList.get(i));
                titleList.add(opt.map(p -> String.format("%d-%d季度", p.getSoilYear(), p.getSoilQuarter())).orElse("--"));

                amount[i] = opt.map(SoilQuarterStatisticsVO::getAmount)
                        .orElse(null);
                data.addBucket(amount[i], titleList.get(i));
            }
            TakeLandAttitudeSvg svgBuilder = new TakeLandAttitudeSvg(data);
            String svgInfo = svgBuilder.genSvg();
            table.setTitleList(titleList);
            table.setSvg(svgInfo);
            return svgInfo;
        } catch (Exception e) {
            log.error("生成拿地态度SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

    /**
     * @param table
     */
    public static String genSoilTakeMonthAreaSvg(TableVO<SoilTakeMonthAreaStatisticsVO> table) {
        if (table == null) {
            return "";
        }
        List<SoilTakeMonthAreaStatisticsVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            int size = dataList.size();
            List<String> titleList = new ArrayList<>(size);
            BigDecimal[] planningBuildingArea = new BigDecimal[size];
            BigDecimal[] sharePlanningArea = new BigDecimal[size];
            Integer[] soilCount = new Integer[size];
            TakeLandTrendSvg.TakeLandTrendSvgData data = new TakeLandTrendSvg.TakeLandTrendSvgData();
            for (int i = 0; i < size; i++) {
                Optional<SoilTakeMonthAreaStatisticsVO> opt = Optional.ofNullable(dataList.get(i));
                titleList.add(opt.map(p -> String.format("%s", p.getYearMonth())).orElse("--"));

                planningBuildingArea[i] = opt.map(SoilTakeMonthAreaStatisticsVO::getPlanningBuildingArea)
                        .orElse(null);
                sharePlanningArea[i] = opt.map(SoilTakeMonthAreaStatisticsVO::getSharePlanningArea)
                        .orElse(null);
                soilCount[i] = opt.map(SoilTakeMonthAreaStatisticsVO::getSoilCount)
                        .orElse(null);

                data.addBucket(planningBuildingArea[i], sharePlanningArea[i], soilCount[i], titleList.get(i));
            }
            TakeLandTrendSvg svgBuilder = new TakeLandTrendSvg(data);
            String svgInfo = svgBuilder.genSvg();
            table.setTitleList(titleList);
            table.setSvg(svgInfo);
            return svgInfo;
        } catch (Exception e) {
            log.error("生成拿地趋势SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

    /**
     * @param table
     */
    public static String genSoilTakeMonthAmountSvg(TableVO<SoilTakeMonthAmountStatisticsVO> table) {
        if (table == null) {
            return "";
        }
        List<SoilTakeMonthAmountStatisticsVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            int size = dataList.size();
            List<String> titleList = new ArrayList<>(size);
            BigDecimal[] closingCosts = new BigDecimal[size];
            BigDecimal[] shareClosingCosts = new BigDecimal[size];
            BigDecimal[] averagePremiumRates = new BigDecimal[size];
            TakeLandAmountSvg.TakeLandAmountSvgData data = new TakeLandAmountSvg.TakeLandAmountSvgData();
            for (int i = 0; i < size; i++) {
                Optional<SoilTakeMonthAmountStatisticsVO> opt = Optional.ofNullable(dataList.get(i));
                titleList.add(opt.map(p -> String.format("%s", p.getYearMonth())).orElse("--"));

                closingCosts[i] = opt.map(SoilTakeMonthAmountStatisticsVO::getClosingCost)
                        .orElse(null);
                shareClosingCosts[i] = opt.map(SoilTakeMonthAmountStatisticsVO::getShareClosingCost)
                        .orElse(null);
                averagePremiumRates[i] = opt.map(SoilTakeMonthAmountStatisticsVO::getAveragePremiumRate)
                        .orElse(null);

                data.addBucket(closingCosts[i], shareClosingCosts[i], averagePremiumRates[i], titleList.get(i));
            }
            TakeLandAmountSvg svgBuilder = new TakeLandAmountSvg(data);
            String svgInfo = svgBuilder.genSvg();
            table.setTitleList(titleList);
            table.setSvg(svgInfo);
            return svgInfo;
        } catch (Exception e) {
            log.error("生成拿地金额SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

    /**
     * @param table
     */
    public static String genSoilTakeQuarterCityLevelSvg(TableVO<SoilTakeQuarterCityLevelStatisticsVO> table) {
        if (table == null) {
            return "";
        }
        List<SoilTakeQuarterCityLevelStatisticsVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            int size = dataList.size();
            List<String> titleList = new ArrayList<>(size);
            Integer[] ones = new Integer[size];
            Integer[] newOnes = new Integer[size];
            Integer[] twos = new Integer[size];
            Integer[] threes = new Integer[size];
            Integer[] fours = new Integer[size];
            Integer[] fives = new Integer[size];
            DistributionTrendSvg.DistributionTrendSvgData data = new DistributionTrendSvg.DistributionTrendSvgData();
            for (int i = 0; i < size; i++) {
                Optional<SoilTakeQuarterCityLevelStatisticsVO> opt = Optional.ofNullable(dataList.get(i));
                titleList.add(opt.map(p -> String.format("%d-%d季度", p.getSoilYear(), p.getSoilQuarter())).orElse("--"));

                ones[i] = opt.map(SoilTakeQuarterCityLevelStatisticsVO::getOne)
                        .orElse(null);
                newOnes[i] = opt.map(SoilTakeQuarterCityLevelStatisticsVO::getNewOne)
                        .orElse(null);
                twos[i] = opt.map(SoilTakeQuarterCityLevelStatisticsVO::getTwo)
                        .orElse(null);
                threes[i] = opt.map(SoilTakeQuarterCityLevelStatisticsVO::getThree)
                        .orElse(null);
                fours[i] = opt.map(SoilTakeQuarterCityLevelStatisticsVO::getFour)
                        .orElse(null);
                fives[i] = opt.map(SoilTakeQuarterCityLevelStatisticsVO::getFive)
                        .orElse(null);
                data.addBucket(ones[i], newOnes[i], twos[i],
                        threes[i], fours[i], fives[i],
                        titleList.get(i));
            }
            DistributionTrendSvg svgBuilder = new DistributionTrendSvg(data);
            String svgInfo = svgBuilder.genSvg();
            table.setTitleList(titleList);
            table.setSvg(svgInfo);
            return svgInfo;
        } catch (Exception e) {
            log.error("生成分布趋势SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

    /**
     * @param table
     */
    public static String genSoilTakeProvinceSvg(TableVO<SoilTakeProvinceStatisticsVO> table) {
        if (table == null) {
            return "";
        }
        List<SoilTakeProvinceStatisticsVO> dataList = table.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "";
        }
        try {
            int size = dataList.size();
            List<String> titleList = new ArrayList<>(size);
            Integer[] soilCount = new Integer[size];
            Map<String, Double> provinceDataMap = new HashMap<>();
            for (int i = 0; i < size; i++) {
                Optional<SoilTakeProvinceStatisticsVO> opt = Optional.ofNullable(dataList.get(i));
                titleList.add(opt.map(p -> p.getProvince()).orElse("--"));
                soilCount[i] = opt.map(SoilTakeProvinceStatisticsVO::getSoilCount)
                        .orElse(0);
                provinceDataMap.put(titleList.get(i), soilCount[i].doubleValue());
            }
            ChinaMapChart svgBuilder = new ChinaMapChart();
            String svgInfo = svgBuilder.geChinaMap(ThymeleafUtils.newSpringTemplateEngine(), provinceDataMap);
            table.setTitleList(titleList);
            table.setSvg(svgInfo);
            return svgInfo;
        } catch (Exception e) {
            log.error("生成分布地区SVG异常。参数:" + JSON.toJSONString(table), e);
            throw new RemotePdfServiceException(PdfExceptionCodeEnum.SVG_ERROR);
        }
    }

}
