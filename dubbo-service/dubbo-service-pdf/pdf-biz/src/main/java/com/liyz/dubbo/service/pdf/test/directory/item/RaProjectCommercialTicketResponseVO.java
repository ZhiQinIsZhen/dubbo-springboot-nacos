package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 商票信息-兑付统计
 * @author tangtang
 * @date 2022-05-23
 */
@Data
public class RaProjectCommercialTicketResponseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 公司名
     */
    @ApiModelProperty("公司名称")
    private String companyName;

    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private Date publishTime;

    /**
     * 承兑总金额
     */
    @ApiModelProperty("承兑总金额")
    private BigDecimal acceptedAmount;

    @ApiModelProperty("承兑人开户机构名称")
    private String dimAcptBranch;

    @ApiModelProperty("承兑余额（元）")
    private BigDecimal acptOver;

    @ApiModelProperty("累计逾期发生额（元）")
    private BigDecimal totalOverdueAmount;

    @ApiModelProperty("逾期余额（元）")
    private BigDecimal overdueOver;

    /**
    * 标题
    */
    @Deprecated
    @ApiModelProperty("标题")
    private String title;

    /**
    * 已承兑票据，即承兑总笔数
    */
    @Deprecated
    @ApiModelProperty("已承兑票据，即承兑总笔数")
    private String accepted;

    /**
    * 已结清总笔数
    */
    @Deprecated
    @ApiModelProperty("已结清总笔数")
    private String cleared;

    /**
    * 未结清总笔数
    */
    @Deprecated
    @ApiModelProperty("未结清总笔数")
    private String uncleared;

    /**
    * 拒付总笔数
    */
    @Deprecated
    @ApiModelProperty("拒付总笔数")
    private String refusePayment;

    /**
    * 拒付比例
    */
    @Deprecated
    @ApiModelProperty("拒付比例")
    private BigDecimal refusePaymentRatio;

    /**
    * 拒付总金额
    */
    @Deprecated
    @ApiModelProperty("拒付总金额")
    private BigDecimal refusePaymentAmount;

    /**
    * 已结清总金额
    */
    @Deprecated
    @ApiModelProperty("已结清总金额")
    private String clearedAmount;

    /**
    * 未结清总金额
    */
    @Deprecated
    @ApiModelProperty("未结清总金额")
    private String unclearedAmount;

    /**
    * 周贴现率
    */
    @Deprecated
    @ApiModelProperty("周贴现率")
    private BigDecimal weekdealAveragerate;
}