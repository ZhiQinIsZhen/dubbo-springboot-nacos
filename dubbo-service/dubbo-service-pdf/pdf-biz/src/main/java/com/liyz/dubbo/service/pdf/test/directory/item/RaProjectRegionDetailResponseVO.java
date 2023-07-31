package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/22 19:21
 */
@Getter
@Setter
public class RaProjectRegionDetailResponseVO implements Serializable {
    private static final long serialVersionUID = 295897868717664825L;

    @ApiModelProperty("发布年份")
    private String endDate;
    @ApiModelProperty("GDP(亿元)")
    private BigDecimal gdpData;
    @ApiModelProperty("GDP增速(%)")
    private BigDecimal gdpIncrease;
    @ApiModelProperty("人口(万人)")
    private BigDecimal population;
    @ApiModelProperty("地区评分")
    private BigDecimal regionalRating;
    @ApiModelProperty("人均GDP(元)")
    private BigDecimal perGdp;
    @ApiModelProperty("固定资产投资(亿元)")
    private BigDecimal investmentFixedAssets;
    @ApiModelProperty("一般公共预算收入(亿元)")
    private BigDecimal pubBudgetRevenue;
    @ApiModelProperty("政府性基金收入(亿元)")
    private BigDecimal govFundIncome;
    @ApiModelProperty("一般公共预算支出(亿元)")
    private BigDecimal pubBudgetExpend;
    @ApiModelProperty("地方政府债务余额(亿元)")
    private BigDecimal localGovDebt;
    @ApiModelProperty("财政自给率(%)")
    private BigDecimal financialRate;
    @ApiModelProperty("债务率(宽口径)(%)")
    private BigDecimal debtRatio;
    @ApiModelProperty("地区名称（市）")
    private String regionName;
    @ApiModelProperty("地区名称（区）")
    private String regionName2;
    @ApiModelProperty("地区等级：0 省及直辖市，1：市，2：区")
    private String regionLevel;
    @ApiModelProperty("唯一值")
    private String uniqueId;
}
