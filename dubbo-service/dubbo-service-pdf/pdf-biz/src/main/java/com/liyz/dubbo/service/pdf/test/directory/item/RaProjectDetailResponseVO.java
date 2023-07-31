package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 注释:项目信息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 13:24
 */
@Getter
@Setter
public class RaProjectDetailResponseVO implements Serializable {
    private static final long serialVersionUID = -8218044777595362262L;

    @ApiModelProperty("是否楼盘情况跳过")
    private Boolean estateSkip;

    @ApiModelProperty("项目名称")
    private String name;

    @ApiModelProperty("项目类型:1居住建筑，2市政建筑，3企事业建筑，4商业娱乐建筑，5生产性建筑")
    private Integer projectType;

    @ApiModelProperty("标群体 1：刚需；2：学区；3：改善")
    private Integer targetGroup;

    @ApiModelProperty("省份")
    private String province;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("地区")
    private String area;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("价格是否待定")
    private Boolean pricePend;

    @ApiModelProperty("均价")
    private BigDecimal averagePrice;

    @ApiModelProperty("销售情况 1：待销售；2：销售中；3：已售罄")
    private Integer saleSituation;
}
