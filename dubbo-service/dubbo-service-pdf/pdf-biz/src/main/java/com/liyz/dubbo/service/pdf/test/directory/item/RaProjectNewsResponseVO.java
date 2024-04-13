package com.liyz.dubbo.service.pdf.test.directory.item;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 新闻舆情
 * @author tangtang
 * @date 2022-05-23
 */
@Data
public class RaProjectNewsResponseVO  implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
    * 新闻标题
    */
    @ApiModelProperty("新闻标题")
    private String title;

    /**
    * 来源
    */
    @ApiModelProperty("来源")
    private String newsSource;

    /**
    * 时间
    */
    @ApiModelProperty("时间")
    private String publishTime;

    /**
    * 新闻地址
    */
    @ApiModelProperty("新闻地址")
    private String url;

    /**
    * saas是否显示 | 0、不显示 1、显示
    */
    @ApiModelProperty("saas是否显示 | 0、不显示 1、显示")
    private int isShow;

    /**
    * 公司名称
    */
    @ApiModelProperty("(废弃字段 - 1。9.0)公司名称")
    @Deprecated
    private String companyName;

    @ApiModelProperty("是否违约 1:标记；0:无标记")
    private Integer riskMark;

    /**
    * 流程id
    */
    @ApiModelProperty("流程id")
    private String processId;



    public RaProjectNewsResponseVO() {}
}