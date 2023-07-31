package com.liyz.dubbo.service.pdf.test.directory.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 注释:三条红线
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/18 20:46
 */
@Getter
@Setter
public class RaProjectThreeRedLineResponseVO implements Serializable {
    private static final long serialVersionUID = -3484444954207711444L;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司标签")
    private List<String> labels;

    /**
     * 剔除预收款后的资产负债率
     */
    @ApiModelProperty("剔除预收款后的资产负债率")
    private String ebtRatioAfterEliminateDepositReceived;

    /**
     * 净负债率
     */
    @ApiModelProperty("净负债率")
    private String netDebtRatio;

    /**
     * 现金短债比
     */
    @ApiModelProperty("现金短债比")
    private String cashFlowsCoverageRatio;

    @ApiModelProperty("档位")
    private String gear;

    /**
     * 财务信息
     */
    @ApiModelProperty("财务信息")
    private String financialInfo;

    /**
     * 三条红线信息
     */
    @ApiModelProperty("三条红线信息")
    @Deprecated
    private String redThreadInfo;

    @Deprecated
    @ApiModelProperty("财务数据的排序内容")
    private String sortItems;

    @ApiModelProperty("截止时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date deadline;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("各种能力")
    private IndicatorStructureResponseVO indicator;
}
