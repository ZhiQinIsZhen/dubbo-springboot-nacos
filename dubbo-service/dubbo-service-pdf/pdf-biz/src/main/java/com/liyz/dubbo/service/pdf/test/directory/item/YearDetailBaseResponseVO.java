package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/23 15:02
 */
@Data
public class YearDetailBaseResponseVO implements Serializable  {

    //年份
    @ApiModelProperty("年份")
    private Integer year;

    //财报类别 1：年报；2：中报 ；3：一季度；4:三季度
    @ApiModelProperty("财报类别 1：年报；2：中报 ；3：一季度；4:三季度")
    private Integer type;
}
