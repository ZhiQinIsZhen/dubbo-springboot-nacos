package com.liyz.dubbo.service.pdf.test.directory.item;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * description: TODO 土储分析结构体
 * author: huanglb
 * date 2023/2/2 16:04
 */
@Slf4j
@Data
public class SoilStorageAnalysisStructure extends RaProjectSoilStorageAnalysisResponseVO implements Serializable {

    /**
     * 拿地态度图表
     */
    private TableVO<SoilQuarterStatisticsVO> soilQuarterTable;
    /**
     * 拿地趋势
     */
    private TableVO<SoilTakeMonthAreaStatisticsVO> soilTakeMonthAreaTable;
    /**
     * 拿地金额
     */
    private TableVO<SoilTakeMonthAmountStatisticsVO> soilTakeMonthAmountTable;

    /**
     * 分布趋势
     */
    private TableVO<SoilTakeQuarterCityLevelStatisticsVO> soilTakeQuarterCityLevelTable;

    /**
     * 分布地区
     */
    private TableVO<SoilTakeProvinceStatisticsVO> soilTakeProvinceTable;


    public void putSoilStorageAnalysis(RaProjectSoilStorageAnalysisResponseVO in) {
        if (Objects.isNull(in)) {
            return;
        }
        Boolean soilSkip = in.getSoilSkip();
        if (Boolean.TRUE.equals(soilSkip)) {
            return;
        }

        BeanUtils.copyProperties(in, this);

        inItTables();
    }

    /**
     *
     */
    private void inItTables() {
        if (!CollectionUtils.isEmpty(super.getSoilQuarter())) {
            soilQuarterTable = new TableVO<>();
            soilQuarterTable.setDataList(super.getSoilQuarter());
        }
        if (!CollectionUtils.isEmpty(super.getSoilTakeMonthArea())) {
            soilTakeMonthAreaTable = new TableVO<>();
            soilTakeMonthAreaTable.setDataList(super.getSoilTakeMonthArea());
        }
        if (!CollectionUtils.isEmpty(super.getSoilTakeMonthAmount())) {
            soilTakeMonthAmountTable = new TableVO<>();
            soilTakeMonthAmountTable.setDataList(super.getSoilTakeMonthAmount());
        }
        if (!CollectionUtils.isEmpty(super.getSoilTakeQuarterCityLevel())) {
            soilTakeQuarterCityLevelTable = new TableVO<>();
            soilTakeQuarterCityLevelTable.setDataList(super.getSoilTakeQuarterCityLevel());
        }
        if (!CollectionUtils.isEmpty(super.getSoilTakeProvince())) {
            soilTakeProvinceTable = new TableVO<>();
            soilTakeProvinceTable.setDataList(super.getSoilTakeProvince());
        }
    }
}
