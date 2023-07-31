package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:司法拍卖
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/20 9:28
 */
@Getter
@Setter
public class RaProjectJudicialSaleResponseVO implements Serializable {
    private static final long serialVersionUID = -4023927460290922011L;

    /**
     * 关联公司名称
     */
    @ApiModelProperty("关联公司名称")
    private String relationCompanyName;
    /**
     * 拍卖时间
     */
    @ApiModelProperty("拍卖时间")
    private String scopeDate;

    /**
     * 拍卖公告
     */
    @ApiModelProperty("拍卖公告")
    private String title;

    /**
     * 拍卖标的
     */
    @ApiModelProperty("拍卖标的")
    private String targetObject;

    /**
     * 起拍价
     */
    @ApiModelProperty("起拍价")
    private String startingPrice;

    /**
     * 评估价
     */
    @ApiModelProperty("评估价")
    private String evaluationPrice;

    /**
     * 拍卖阶段
     */
    @ApiModelProperty("拍卖阶段")
    private String auctionType;

    /**
     * 执行法院
     */
    @ApiModelProperty("执行法院")
    private String court;
}
