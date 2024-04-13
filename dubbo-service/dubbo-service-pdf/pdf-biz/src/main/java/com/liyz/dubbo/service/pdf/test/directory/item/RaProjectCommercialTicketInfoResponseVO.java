package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author tangtang
 * @description：商票情况
 * @date Created in 2022/5/25 10:41
 * @version: 1.0.0
 */
@Data
public class RaProjectCommercialTicketInfoResponseVO implements Serializable {
    private static final long serialVersionUID = 1418249126878956309L;

    @ApiModelProperty("流程ID")
    private String processId;

    @ApiModelProperty("选中公司")
    private Long corporationId;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司名称")
    private List<String> labels;

    @ApiModelProperty("商票情况")
    private RaProjectCommercialTicketOverviewResponseVO raProjectCommercialTicketOverview;

    @ApiModelProperty("公司列表")
    private List<String> companyNameList;

    @ApiModelProperty("商票信息-兑付统计")
    private List<SaProjectGroupTable<RaProjectCommercialTicketResponseVO>> commercialTicketResponses;



}
