package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/23 14:56
 */
@Getter
@Setter
public class IndicatorDataResponseVO implements Serializable  {


    //数据类型
    @ApiModelProperty("数据类型")
    private  Integer type;

    //所属对象名称
    @ApiModelProperty("公司名称")
    private String name;

    //名称
    @ApiModelProperty("公司名称-LabelName")
    private  String companyLabelName;

    @ApiModelProperty("风险数量")
    private Integer  riskQuan;

    @ApiModelProperty("风险类型")
    private HashMap<String,Integer> riskTypes;
}
