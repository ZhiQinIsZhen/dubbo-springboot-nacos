package com.liyz.dubbo.service.pdf.test.directory.item;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 债券信息-信用评级
 * @author tangtang
 * @date 2022-05-23
 */
@Data
public class RaProjectCreditRatingResponseVO  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 机构名称
    */
    @ApiModelProperty("机构名称")
    private String name;

    /**
    * 等级
    */
    @ApiModelProperty("等级")
    private String level;

    /**
    * 评级更新时间
    */
    @ApiModelProperty("评级更新时间")
    private String updateDate;

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


    public RaProjectCreditRatingResponseVO() {}
}