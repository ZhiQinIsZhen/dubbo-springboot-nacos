package com.liyz.dubbo.service.pdf.test.directory.item;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 债券信息detail
 * @author tangtang
 * @date 2022-05-23
 */
@Data
public class RaProjectBondDetailResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
    * 债券代码
    */
    @ApiModelProperty("债券代码")
    private String code;

    /**
    * 简称
    */
    @ApiModelProperty("简称")
    private String abbreviation;

    /**
    * 类别
    */
    @ApiModelProperty("类别")
    private String type;

    /**
    * 发行日期
    */
    @ApiModelProperty("发行日期")
    private String issuanceStartDate;

    /**
    * 起息日期
    */
    @ApiModelProperty("起息日期")
    private String interestStartDate;

    /**
    * 到期日期
    */
    @ApiModelProperty("到期日期")
    private String issuanceEndDate;

    /**
    * 发行期限
    */
    @ApiModelProperty("发行期限")
    private String term;

    /**
    * 剩余期限
    */
    @ApiModelProperty("剩余期限")
    private String surplusTerm;

    /**
    * 票面利率
    */
    @ApiModelProperty("票面利率")
    private String couponRate;

    @ApiModelProperty("是否违约 1:标记；0:无标记")
    private Integer riskMark;

    /**
    * 利率类型
    */
    @ApiModelProperty("利率类型")
    private String couponRateType;

    /**
    * 币种
    */
    @ApiModelProperty("币种")
    private String currency;

    /**
    * 当前余额
    */
    @ApiModelProperty("当前余额")
    private String balance;

    /**
    * 发行规模
    */
    @ApiModelProperty("发行规模")
    private String scale;

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

    public RaProjectBondDetailResponseVO() {}
}