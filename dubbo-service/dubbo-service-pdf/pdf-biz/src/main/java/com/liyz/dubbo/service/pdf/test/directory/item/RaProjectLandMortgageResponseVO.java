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
 * @date 2022/5/20 9:47
 */
@Getter
@Setter
public class RaProjectLandMortgageResponseVO implements Serializable {
    private static final long serialVersionUID = -8684164938775010611L;

    /**
     * 关联公司名称
     */
    @ApiModelProperty("关联公司名称")
    private String relationCompanyName;
    //土地坐落
    @ApiModelProperty("土地坐落")
    private String landLoc;

    //开始时间
    @ApiModelProperty("开始时间")
    private String startDate;

    //结束时间
    @ApiModelProperty("结束时间")
    private String endDate;

    //行政区
    @ApiModelProperty("行政区")
    private String landAministrativeArea;

    //抵押面积（公顷）
    @ApiModelProperty("抵押面积（公顷）")
    private String mortgageArea;

    //抵押土地用途
    @ApiModelProperty("抵押土地用途")
    private String mortgageToUser;
}
