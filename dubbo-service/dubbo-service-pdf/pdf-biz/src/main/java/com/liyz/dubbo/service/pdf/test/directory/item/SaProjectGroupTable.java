package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SaProjectGroupTable<T> implements Serializable {

    @ApiModelProperty("流程id")
    private String processId;

    @ApiModelProperty("公司ID")
    private Long corporationId;

    @ApiModelProperty("企业名称")
    private String companyName;

    @ApiModelProperty("企业标签")
    private List<String> labels;

    @ApiModelProperty("分组表格信息")
    private List<T> gTableList;

}
