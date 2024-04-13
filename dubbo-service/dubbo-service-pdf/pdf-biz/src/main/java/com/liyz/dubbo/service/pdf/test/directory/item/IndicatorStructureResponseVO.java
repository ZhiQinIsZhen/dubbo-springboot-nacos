package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/23 14:54
 */
@Getter
@Setter
public class IndicatorStructureResponseVO implements Serializable  {

    @ApiModelProperty("财务数据")
    private List<FinanceDataResponseVO> lsFinanceData;

    @ApiModelProperty("运营能力指标")
    private List<OperationDataResponseVO> lsOperationData;

    @ApiModelProperty("债偿能力")
    private List<RepaymentDataResponseVO> lsRepaymentData;

    @ApiModelProperty("描述文本")
    private List<DescribeDataResponseVO> lsDescribeData;

    @ApiModelProperty("成长能力")
    private List<GrowthDataResponseVO> lsGrowthData;
}
