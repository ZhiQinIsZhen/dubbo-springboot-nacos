package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 注释:司法记录表（老流程适配）
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/30 15:47
 */
@Getter
@Setter
public class RaProjectJudicialRecordResponseVO implements Serializable {
    private static final long serialVersionUID = -2341652399054309431L;

    /**
     * 原告数量
     */
    @ApiModelProperty("原告数量")
    private Integer plaintiffCount;

    /**
     * 原告涉及金额（万元）
     */
    @ApiModelProperty("原告涉及金额（万元）")
    private BigDecimal plaintiffAmount;

    /**
     * 被告数量
     */
    @ApiModelProperty("被告数量")
    private Integer defendantCount;

    /**
     * 被告涉及金额（万元）
     */
    @ApiModelProperty("被告涉及金额（万元）")
    private BigDecimal defendantAmount;

    /**
     * 近两年执行记录
     */
    @ApiModelProperty("近两年执行记录")
    private Integer recentTwoYearsImplementationCount;

    /**
     * 近两年执行金额（万元）
     */
    @ApiModelProperty("近两年执行金额（万元）")
    private BigDecimal recentTwoYearsImplementationAmount;

    /**
     * 失信次数
     */
    @ApiModelProperty("失信次数")
    private Integer dishonestyCount;

    /**
     * 失信金额
     */
    @ApiModelProperty("失信金额")
    private BigDecimal dishonestyAmount;
}
