package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description 担保信息
 * @author tangtang
 * @date 2022-09-21
 */
@Data
public class RaProjectGuaranteeDataResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司标签(多个英文,分隔) 0:项目公司,1:实际控股公司,2:其他关联公司")
    private String companyLabel;

    /**
    * 担保金额
    */
    @ApiModelProperty("担保金额")
    private BigDecimal amount;

    /**
    * 起始日期
    */
    @ApiModelProperty("起始日期")
    private String bdate;

    /**
    * 截止日期
    */
    @ApiModelProperty("截止日期")
    private String edate;

    /**
    * 担保类型（信托融资担保，租赁融资担保，银行借款担保）
    */
    @ApiModelProperty("担保类型（信托融资担保，租赁融资担保，银行借款担保）")
    private String financeName;

    /**
    * 被担保方
    */
    @ApiModelProperty("被担保方")
    private String guaranteedName;

    /**
    * 担保方
    */
    @ApiModelProperty("担保方")
    private String itname;

    public RaProjectGuaranteeDataResponseVO() {}
}