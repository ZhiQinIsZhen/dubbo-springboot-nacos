package com.liyz.dubbo.service.pdf.test.directory.item;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 重点公告
 * @author tangtang
 * @date 2022-05-23
 */

@Data
public class RaProjectNoticeResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
    * 时间
    */
    @ApiModelProperty("时间")
    private String publishTime;

    /**
    * 内容
    */
    @ApiModelProperty("内容")
    private String content;

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

    @ApiModelProperty("是否违约 1:标记；0:无标记")
    private Integer riskMark;

    public RaProjectNoticeResponseVO() {}
}