package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/23 14:55
 */
@Getter
@Setter
public class DescribeDataResponseVO extends  IndicatorDataResponseVO implements Serializable  {


    /**
     * 描述文本
     */
    @ApiModelProperty("描述文本")
    private String describeInfo;
}
