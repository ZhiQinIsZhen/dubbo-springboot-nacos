package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2023/1/30 15:37
 */
@Getter
@Setter
public class RaProjectSoilStorageAnalysisResponseVO implements Serializable {
    private static final long serialVersionUID = 4155639795064136254L;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司标签")
    private List<String> labels;

    @ApiModelProperty("是否跳过 0-未跳过，1-已跳过")
    private Boolean soilSkip;

    @ApiModelProperty("最一年土储统计")
    private SoilTakeLastYearStatisticsVO soilTakeLastYear;

    @ApiModelProperty("近一年新增城市top3")
    private List<String> lastYearTopCity;

    @ApiModelProperty("近一年新拓展城市")
    private List<String> lastYearNewCity;

    @ApiModelProperty("拿地统计")
    private SoilTakeStatisticsVO soilTakeStatistics;

    @ApiModelProperty("拿地态度")
    private List<SoilQuarterStatisticsVO> soilQuarter;

    @ApiModelProperty("拿地金额")
    private List<SoilTakeMonthAmountStatisticsVO> soilTakeMonthAmount;

    @ApiModelProperty("拿地趋势")
    private List<SoilTakeMonthAreaStatisticsVO> soilTakeMonthArea;

    @ApiModelProperty("拿地明细")
    private List<SoilTakeMonthStatisticsVO> soilTakeMonthDetails;

    @ApiModelProperty("分布趋势")
    private List<SoilTakeQuarterCityLevelStatisticsVO> soilTakeQuarterCityLevel;

    @ApiModelProperty("分布地区")
    private List<SoilTakeProvinceStatisticsVO> soilTakeProvince;

    @ApiModelProperty("地区明细")
    private List<SoilTakeTypeStatisticsVO> soilTakeType;
}
