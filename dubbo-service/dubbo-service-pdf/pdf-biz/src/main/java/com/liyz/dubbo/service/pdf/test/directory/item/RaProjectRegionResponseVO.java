package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/22 19:19
 */
@Getter
@Setter
public class RaProjectRegionResponseVO implements Serializable {
    private static final long serialVersionUID = -7831957234235140128L;

    @ApiModelProperty("是否区域信息跳过 | 0-未跳过，1-已跳过")
    private Boolean areaSkip;

    @ApiModelProperty("省份code")
    private String province;

    @ApiModelProperty("城市code")
    private String city;

    @ApiModelProperty("地区code")
    private String area;

    @ApiModelProperty("省份名称")
    private String provinceName;

    @ApiModelProperty("城市名称")
    private String cityName;

    @ApiModelProperty("地区名称")
    private String areaName;

    @ApiModelProperty("全国比较")
    private String nationalComparison;

    @ApiModelProperty("全省比较")
    private String provincialComparison;

    @ApiModelProperty("城投分析描述")
    private String uiDesc;

    @ApiModelProperty("利差文件key")
    private String interestMarginFileKey;

    @ApiModelProperty("久期文件key")
    private String durationFileKey;
}
