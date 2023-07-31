package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author tangtang
 * @description：
 * @date Created in 2022/9/21 16:20
 * @version: 1.0.0
 */
@Data
public class RaProjectBondGuaranteeDataResponseVO implements Serializable {
    private static final long serialVersionUID = -1044001325298473571L;


    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司标签(多个英文,分隔) 0:项目公司,1:实际控股公司,2:其他关联公司")
    private String companyLabel;
    /**
     * 债券简称
     */
    @ApiModelProperty("债券简称")
    private String bondName;


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
     * 债券类型
     */
    @ApiModelProperty("债券类型")
    private String bondType;


    /**
     * 担保规模
     */
    @ApiModelProperty("担保规模")
    private BigDecimal amount;


    /**
     * 发债人
     */
    @ApiModelProperty("发债人")
    private String issuer;


    /**
     * 债券代码
     */
    @ApiModelProperty("债券代码")
    private String symbol;
}
