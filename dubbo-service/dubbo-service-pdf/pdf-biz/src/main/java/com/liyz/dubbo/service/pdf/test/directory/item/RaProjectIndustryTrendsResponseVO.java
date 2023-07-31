package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 行业趋势
 * @author tangtang
 * @date 2022-05-23
 */
@Data
public class RaProjectIndustryTrendsResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;


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



    public RaProjectIndustryTrendsResponseVO() {}
}