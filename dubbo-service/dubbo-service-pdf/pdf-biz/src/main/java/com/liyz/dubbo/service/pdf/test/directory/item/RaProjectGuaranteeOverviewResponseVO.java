package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author tangtang
 * @description：对外担保概览
 * @date Created in 2022/9/23 10:07
 * @version: 1.0.0
 */
@Data
public class RaProjectGuaranteeOverviewResponseVO implements Serializable {
    private static final long serialVersionUID = -2812882088005057902L;
    /**
     * 担保金额
     */
    @ApiModelProperty("总担保金额")
    private BigDecimal amountSum;


    /**
     * 担保类型（信托融资担保，租赁融资担保，银行借款担保）债券担保
     *
     */
    @ApiModelProperty("担保类型（信托融资担保，租赁融资担保，银行借款担保，债券担保）")
    private String financeName;

    /**
     * 担保类型（信托融资担保，租赁融资担保，银行借款担保）债券担保
     *
     */
    @ApiModelProperty("担保类型（信托融资担保，租赁融资担保，银行借款担保，债券担保）")
    private String financeNameCode;

    @ApiModelProperty("担保数量")
    private BigDecimal num;

    @ApiModelProperty("排序")
    private Integer sort;

}
