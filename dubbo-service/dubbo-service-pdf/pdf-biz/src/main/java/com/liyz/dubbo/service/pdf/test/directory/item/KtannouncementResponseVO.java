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
 * 风险专家-企业开庭公告
 * </p>
 *
 * @author MBP Generator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KtannouncementResponseVO implements Serializable {

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
     * 原告/上诉人，多个逗号拼接
     */
    @ApiModelProperty(value = "原告/上诉人，多个逗号拼接")
    private String plaintiff;

    /**
     * 被告/被上诉人，多个逗号拼接
     */
    @ApiModelProperty(value = "被告/被上诉人，多个逗号拼接")
    private String defendant;

    /**
     * 审判长/主审人
     */
    @ApiModelProperty(value = "审判长/主审人")
    private String judge;

    /**
     * 法庭
     */
    @ApiModelProperty(value = "法庭")
    private String courtroom;

    /**
     * 法院
     */
    @ApiModelProperty(value = "法院")
    private String court;

    /**
     * 承办部门
     */
    @ApiModelProperty(value = "承办部门")
    private String contractors;

    /**
     * 案由
     */
    @ApiModelProperty(value = "案由")
    private String caseReason;

    /**
     * 案号
     */
    @ApiModelProperty(value = "案号")
    private String caseNo;

    /**
     * 开庭日期
     */
    @ApiModelProperty(value = "开庭日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    /**
     * 天眼查数据id
     */
    @ApiModelProperty(value = "天眼查数据id")
    private String tycId;

}
