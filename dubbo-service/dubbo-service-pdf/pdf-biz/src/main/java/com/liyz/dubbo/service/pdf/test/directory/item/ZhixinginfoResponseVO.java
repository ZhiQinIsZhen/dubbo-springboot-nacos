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
 * 风险专家-企业被执行人信息
 * </p>
 *
 * @author MBP Generator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZhixinginfoResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 被执行人名称
     */
    @ApiModelProperty(value = "被执行人名称")
    private String pname;

    /**
     * 立案日期
     */
    @ApiModelProperty(value = "立案日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date caseCreateTime;

    /**
     * 执行标的（元）
     */
    @ApiModelProperty(value = "执行标的（元）")
    private String execMoney;

    /**
     * 案号
     */
    @ApiModelProperty(value = "案号")
    private String caseNo;

    /**
     * 执行法院
     */
    @ApiModelProperty(value = "执行法院")
    private String court;


    @ApiModelProperty("是否违约 1:标记；0:无标记")
    private Integer riskMark;

}
