package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/11/1 13:44
 */
@Getter
@Setter
public class RaProjectUrbanInvestmentResponseVO implements Serializable {
    private static final long serialVersionUID = -2228792787843278699L;

    @ApiModelProperty("区域指标")
    private RaProjectRegionResponseVO regionInfo;

    @ApiModelProperty("区域指标明细数据")
    private List<RaProjectRegionDetailResponseVO> regionDetails;

    @ApiModelProperty("政府补助")
    private RaProjectGovGrantsResponseVO govGrants;

    @ApiModelProperty("区域利差")
    private List<RaProjectRegionInterestMarginResponseVO> interestMargins;

    @ApiModelProperty("区域久期")
    private List<RaProjectRegionDurationResponseVO> durations;
}
