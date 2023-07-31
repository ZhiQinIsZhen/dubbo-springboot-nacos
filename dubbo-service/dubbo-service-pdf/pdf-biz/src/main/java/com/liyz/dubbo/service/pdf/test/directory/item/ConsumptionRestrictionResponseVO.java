package com.liyz.dubbo.service.pdf.test.directory.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 风险专家-企业消费限制
 * </p>
 *
 * @author MBP Generator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionRestrictionResponseVO implements Serializable {
    private static final long serialVersionUID = -2267674832400230446L;
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * ，1-个人 2-企业
     */
    @ApiModelProperty(value = "1-个人 2-企业")
    private Integer sourceType;
    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id")
    private Long corporationId;

    /**
     * 数据来源类型，0-天眼查 1-手动添加
     */
    @ApiModelProperty(value = "数据来源类型，0-天眼查 1-手动添加")
    private Integer dataSourceType;

    /**
     * 限制消费者名称
     */
    @ApiModelProperty(value = "限制消费者名称")
    private String xname;

    /**
     * 立案日期
     */
    @ApiModelProperty(value = "立案日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date caseCreateTime;

    /**
     * 企业信息
     */
    @ApiModelProperty(value = "企业信息")
    private String qyinfo;

    /**
     * 申请人信息
     */
    @ApiModelProperty(value = "申请人信息")
    private String applicant;

    /**
     * 案号
     */
    @ApiModelProperty(value = "案号")
    private String caseNo;

    /**
     * 发布日期
     */
    @ApiModelProperty(value = "发布日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date publishDate;

    @ApiModelProperty("是否违约 1:标记；0:无标记")
    private Integer riskMark;
}
