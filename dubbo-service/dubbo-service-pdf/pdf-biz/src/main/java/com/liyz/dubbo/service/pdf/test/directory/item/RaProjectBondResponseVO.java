package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:债券信息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 18:05
 */
@Getter
@Setter
public class RaProjectBondResponseVO implements Serializable {
    private static final long serialVersionUID = -1147788933507741788L;

    @ApiModelProperty("公司ID")
    private Long corporationId;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司名称")
    private List<String> labels;

    @ApiModelProperty("1信息描述")
    private String contentInfo;

    @ApiModelProperty("是否违约 1:标记；0:无标记")
    private Integer riskMark;

    @ApiModelProperty("2违约描述")
    private String contentDefault;

    @ApiModelProperty("3机构评级")
    private String agencyRating;

    @ApiModelProperty("债券信息-违约信息")
    private List<RaProjectBondDefaultResponseVO> raProjectBondDefaultResponses;

    @ApiModelProperty("债券信息-债券信息detail")
    private List<RaProjectBondDetailResponseVO> raProjectBondDetailResponses;

    @ApiModelProperty("债券信息-信用评级")
    private List<RaProjectCreditRatingResponseVO> raProjectCreditRatingResponses;



}
