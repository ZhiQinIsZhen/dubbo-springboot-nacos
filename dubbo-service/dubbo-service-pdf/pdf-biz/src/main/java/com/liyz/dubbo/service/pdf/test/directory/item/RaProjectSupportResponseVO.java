package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/19 16:33
 */
@Getter
@Setter
public class RaProjectSupportResponseVO implements Serializable {
    private static final long serialVersionUID = 2189994163089643804L;

    @ApiModelProperty("是否周边配套信息跳过 | 0-未跳过，1-已跳过")
    private Boolean supportSkip;

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

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("直径(km)")
    private BigDecimal diameter;

    @ApiModelProperty("经度")
    private BigDecimal longitude;

    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    @ApiModelProperty("公交数量")
    private Integer transitCount;

    @ApiModelProperty("地铁数量")
    private Integer metroCount;

    @ApiModelProperty("医疗数量")
    private Integer hospitalCount;

    @ApiModelProperty("教育数量")
    private Integer educationCount;

    @ApiModelProperty("购物数量")
    private Integer shoppingCount;

    @ApiModelProperty("生活数量")
    private Integer lifeCount;

    @ApiModelProperty("图片文件key")
    private String picFileKey;

    @ApiModelProperty("地图对应url")
    private String mapUrl;
}
