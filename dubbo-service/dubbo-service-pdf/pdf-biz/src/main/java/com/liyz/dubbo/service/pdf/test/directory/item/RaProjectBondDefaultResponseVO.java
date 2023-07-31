package com.liyz.dubbo.service.pdf.test.directory.item;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 债券信息-违约信息
 * @author tangtang
 * @date 2022-05-23
 */
@Data
public class RaProjectBondDefaultResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
    * 简称
    */
    @ApiModelProperty("简称")
    private String abbreviation;

    @ApiModelProperty("债券代码")
    private String code;

    @ApiModelProperty("剩余期限（年）")
    private String surplusYear;

    /**
    * 违约日期
    */
    @ApiModelProperty("违约日期")
    private String firstDefaultDate;

    /**
    * 发行人
    */
    @ApiModelProperty("发行人")
    private String issuer;

    /**
    * 违约类型
    */
    @ApiModelProperty("违约类型")
    private String type;

    /**
    * 最新状态
    */
    @ApiModelProperty("最新状态")
    private String latestStatus;

    /**
    * 发行规模
    */
    @ApiModelProperty("发行规模")
    private String scale;

    /**
    * 违约日债券余额
    */
    @ApiModelProperty("违约日债券余额")
    private String balance;

    /**
    * 逾期本金
    */
    @ApiModelProperty("逾期本金")
    private String defaultPrincipal;

    /**
    * 逾期利息
    */
    @ApiModelProperty("逾期利息")
    private String defaultInterest;

    /**
    * 到期日期
    */
    @ApiModelProperty("到期日期")
    private String issuanceEndDate;

    /**
    * 起息日期
    */
    @ApiModelProperty("起息日期")
    private String interestStartDate;

    /**
    * 公司名称
    */
    @ApiModelProperty("公司名称")
    private String companyName;

    /**
    * 流程id
    */
    @ApiModelProperty("流程id")
    private String processId;


    public RaProjectBondDefaultResponseVO() {}
}