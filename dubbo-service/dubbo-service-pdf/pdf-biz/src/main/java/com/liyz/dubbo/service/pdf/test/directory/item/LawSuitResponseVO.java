package com.liyz.dubbo.service.pdf.test.directory.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 风险专家-企业法律诉讼
 * </p>
 *
 * @author MBP Generator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "风险专家-法律诉讼-响应对象")
public class LawSuitResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;
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
     * 案件身份
     */
    @ApiModelProperty(value = "案件身份")
    private String role;

    /**
     * 案由
     */
    @ApiModelProperty(value = "案由")
    private String caseReason;

    /**
     * 案件金额
     */
    @ApiModelProperty(value = "案件金额")
    private String caseMoney;

    /**
     * 案号
     */
    @ApiModelProperty(value = "案号")
    private String caseNo;

    /**
     * 案件名称
     */
    @ApiModelProperty(value = "案件名称")
    private String title;

    /**
     * 裁判日期，例：“2021-04-29”
     */
    @ApiModelProperty(value = "裁判日期，例：“2021-04-29”")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date judgeTime;

    /**
     * 天眼查UUID
     */
    @ApiModelProperty(value = "天眼查UUID")
    private String tycUuid;


}
