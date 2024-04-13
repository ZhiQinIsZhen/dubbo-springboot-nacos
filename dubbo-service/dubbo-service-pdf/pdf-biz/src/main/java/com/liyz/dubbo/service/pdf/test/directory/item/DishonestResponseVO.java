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
 * 风险专家-企业失信人信息
 * </p>
 *
 * @author MBP Generator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishonestResponseVO implements Serializable {

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
     * 失信人名称
     */
    @ApiModelProperty(value = "失信人名称")
    private String iname;

    /**
     * 立案日期
     */
    @ApiModelProperty(value = "立案日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date caseCreateTime;

    /**
     * 履行情况
     */
    @ApiModelProperty(value = "履行情况")
    private String performance;

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

    /**
     * 执行依据文号
     */
    @ApiModelProperty(value = "执行依据文号")
    private String gistid;

    /**
     * 做出执行的依据单位
     */
    @ApiModelProperty(value = "做出执行的依据单位")
    private String gistunit;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date publishdate;

    /**
     * 省份地区
     */
    @ApiModelProperty(value = "省份地区")
    private String areaname;

    /**
     * 法人、负责人姓名
     */
    @ApiModelProperty(value = "法人、负责人姓名")
    private String businessentity;

    /**
     * 身份证号码/组织机构代码
     */
    @ApiModelProperty(value = "身份证号码/组织机构代码")
    private String cardnum;

    /**
     * 法定负责人/主要负责人信息，多个逗号分隔
     */
    @ApiModelProperty(value = "法定负责人/主要负责人信息，多个逗号分隔")
    private String staff;

    /**
     * 失信被执行人行为具体情形
     */
    @ApiModelProperty(value = "失信被执行人行为具体情形")
    private String disrupttypename;

    /**
     * 生效法律文书确定的义务
     */
    @ApiModelProperty(value = "生效法律文书确定的义务")
    private String duty;

    /**
     * 已履行部分
     */
    @ApiModelProperty(value = "已履行部分")
    private String performedPart;

    /**
     * 未履行部分
     */
    @ApiModelProperty(value = "未履行部分")
    private String unperformPart;

    @ApiModelProperty("是否违约 1:标记；0:无标记")
    private Integer riskMark;
}
