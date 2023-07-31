package com.liyz.dubbo.service.pdf.test.directory.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/3 10:45
 */
@Data
public class RaProjectRegionStructure implements Serializable {


    private RaProjectRegionResponseVO regionInfo;

    private List<RaProjectRegionDetailResponseVO> regionDetails;

    /**
     * 区域名称
     */
    private String regionAllName;
    /**
     *
     */
    private List<String> yaerTitleList;

    /**
     *
     */
    private List<RegionIndicator> regionIndicatorList;


    public static RaProjectRegionStructure buildRaProjectRegionStructure(RaProjectRegionResponseVO regionInfo, List<RaProjectRegionDetailResponseVO> regionDetails) {
        RaProjectRegionStructure raProjectRegionStructure = null;
        if (null == regionInfo && CollectionUtils.isEmpty(regionDetails)) {
            return raProjectRegionStructure;
        }
        Boolean areaSkip = regionInfo.getAreaSkip();
        if (Boolean.TRUE.equals(areaSkip)) {
            return raProjectRegionStructure;
        }
        raProjectRegionStructure = new RaProjectRegionStructure();
        if (regionInfo != null) {
            StringJoiner stringJoiner = new StringJoiner("-");
            if (StringUtils.isNotBlank(regionInfo.getProvinceName())) {
                stringJoiner.add(regionInfo.getProvinceName());
            }
            if (StringUtils.isNotBlank(regionInfo.getCityName())) {
                stringJoiner.add(regionInfo.getCityName());
            }
            if (StringUtils.isNotBlank(regionInfo.getAreaName())) {
                stringJoiner.add(regionInfo.getAreaName());
            }
            raProjectRegionStructure.setRegionAllName(stringJoiner.toString());

            raProjectRegionStructure.setRegionInfo(regionInfo);
        }
        if (!CollectionUtils.isEmpty(regionDetails)) {
            raProjectRegionStructure.putRegionIndicatorList(regionDetails);
        }
        return raProjectRegionStructure;
    }

    @JsonIgnore
    public List<RegionIndicator> getEconomics() {
        if (CollectionUtils.isEmpty(regionIndicatorList)) {
            return Lists.newArrayList();
        }
        List<RegionIndicator> ls = regionIndicatorList.stream().filter(p -> p.getType().compareTo(5) <= 0).collect(Collectors.toList());
        return ls;
    }

    @JsonIgnore
    public List<RegionIndicator> getLiabilities() {
        if (CollectionUtils.isEmpty(regionIndicatorList)) {
            return Lists.newArrayList();
        }
        List<RegionIndicator> ls = regionIndicatorList.stream().filter(p -> p.getType().compareTo(8) <= 0 &&
                p.getType().compareTo(5) > 0).collect(Collectors.toList());
        return ls;
    }

    @JsonIgnore
    public List<RegionIndicator> getFinances() {
        if (CollectionUtils.isEmpty(regionIndicatorList)) {
            return Lists.newArrayList();
        }
        List<RegionIndicator> ls = regionIndicatorList.stream().filter(p -> p.getType().compareTo(8) > 0).collect(Collectors.toList());
        return ls;
    }


    @JsonIgnore
    public RegionIndicator getIndicatorByType(Integer type) {
        if (CollectionUtils.isEmpty(regionIndicatorList)) {
            return null;
        }
        RegionIndicator ob = regionIndicatorList.stream().filter(p -> p.getType().compareTo(type) == 0).findFirst().orElse(null);
        return ob;
    }

    /**
     * @param regionDetails
     */
    public void putRegionIndicatorList(List<RaProjectRegionDetailResponseVO> regionDetails) {
        this.yaerTitleList = Lists.newArrayList();
        this.regionIndicatorList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(regionDetails)) {
            return;
        }
        this.regionDetails = regionDetails;
        List<BigDecimal> dataList1 = Lists.newArrayList();
        List<BigDecimal> dataList2 = Lists.newArrayList();
        List<BigDecimal> dataList3 = Lists.newArrayList();
        List<BigDecimal> dataList4 = Lists.newArrayList();
        List<BigDecimal> dataList5 = Lists.newArrayList();
        List<BigDecimal> dataList6 = Lists.newArrayList();
        List<BigDecimal> dataList7 = Lists.newArrayList();
        List<BigDecimal> dataList8 = Lists.newArrayList();
        List<BigDecimal> dataList9 = Lists.newArrayList();
        List<BigDecimal> dataList10 = Lists.newArrayList();
        List<BigDecimal> dataList11 = Lists.newArrayList();
        for (RaProjectRegionDetailResponseVO regionDetail : regionDetails) {

            /**
             *  GDP(亿元)   GDP增速(%) 人均GDP(元) 人口(万人)  固定资产投资(亿元)
             * 地方政府债务余额(亿元)  财政自给率(%)  债务率(宽口径)(%)
             * 一般公共预算收入(亿元)  政府性基金收入(亿元)  一般公共预算支出(亿元)
             */
            yaerTitleList.add(regionDetail.getEndDate());
            dataList1.add(regionDetail.getGdpData());
            dataList2.add(regionDetail.getGdpIncrease());
            dataList3.add(regionDetail.getPerGdp());
            dataList4.add(regionDetail.getPopulation());
            dataList5.add(regionDetail.getInvestmentFixedAssets());

            dataList6.add(regionDetail.getLocalGovDebt());
            dataList7.add(regionDetail.getFinancialRate());
            dataList8.add(regionDetail.getDebtRatio());

            dataList9.add(regionDetail.getPubBudgetRevenue());
            dataList10.add(regionDetail.getGovFundIncome());
            dataList11.add(regionDetail.getPubBudgetExpend());
        }

        this.regionIndicatorList.add(new RegionIndicator(1, yaerTitleList, dataList1));
        this.regionIndicatorList.add(new RegionIndicator(2, yaerTitleList, dataList2));
        this.regionIndicatorList.add(new RegionIndicator(3, yaerTitleList, dataList3));
        this.regionIndicatorList.add(new RegionIndicator(4, yaerTitleList, dataList4));
        this.regionIndicatorList.add(new RegionIndicator(5, yaerTitleList, dataList5));
        this.regionIndicatorList.add(new RegionIndicator(6, yaerTitleList, dataList6));
        this.regionIndicatorList.add(new RegionIndicator(7, yaerTitleList, dataList7));
        this.regionIndicatorList.add(new RegionIndicator(8, yaerTitleList, dataList8));
        this.regionIndicatorList.add(new RegionIndicator(9, yaerTitleList, dataList9));
        this.regionIndicatorList.add(new RegionIndicator(10, yaerTitleList, dataList10));
        this.regionIndicatorList.add(new RegionIndicator(11, yaerTitleList, dataList11));
    }
}