package com.liyz.dubbo.service.pdf.test.directory.item;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description 商票信息-概览
 * @author tangtang
 * @date 2022-05-23
 */
@Data
public class RaProjectCommercialTicketOverviewResponseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司名称")
    private List<String> labels;

    @ApiModelProperty("是否违约 1:标记；0:无标记")
    private Integer riskMark;

    /**
     * 商票信息
     */
    @ApiModelProperty("商票信息")
    private String billDiscountInfo;
    /**
    * 流程id
    */
    @ApiModelProperty("流程id")
    private String processId;

    /**
    * 市场交易年利率low
    */
    @ApiModelProperty("市场交易年利率low")
    private BigDecimal interestRateLow;

    /**
    * 市场交易年利率high
    */
    @ApiModelProperty("市场交易年利率high")
    private BigDecimal interestRateHigh;

    /**
    * 风险等级
    */
    @ApiModelProperty("风险等级")
    private String riskLevel;

    @ApiModelProperty("风险等级标记")
    private Integer riskLevelMark;

    public RaProjectCommercialTicketOverviewResponseVO() {}
}