package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description 公司授信额度
 * @author tangtang
 * @date 2022-09-21
 */

@Data
public class RaProjectCompanyCreditLineResponseVO implements Serializable{

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司标签(多个英文,分隔) 0:项目公司,1:实际控股公司,2:其他关联公司")
    private String companyLabel;

    /**
     * 公司名称
     */
    @ApiModelProperty("公司名称")
    private String entityName;


    /**
    * 授信额度(亿元)
    */
    @ApiModelProperty("授信额度(亿元)")
    private BigDecimal creditLine;

    /**
    * 未使用
    */
    @ApiModelProperty("未使用")
    private BigDecimal creditLineUnused;

    /**
    * 已使用
    */
    @ApiModelProperty("已使用")
    private BigDecimal creditLineUsed;


    /**
    * 披露日期
    */
    @ApiModelProperty("披露日期")
    private String discloureDate;

    /**
    * 截止日期
    */
    @ApiModelProperty("截止日期")
    private String endDate;


    public RaProjectCompanyCreditLineResponseVO() {}
}