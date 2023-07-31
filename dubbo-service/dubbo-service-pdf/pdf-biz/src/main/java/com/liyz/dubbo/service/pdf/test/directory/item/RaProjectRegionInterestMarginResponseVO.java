package com.liyz.dubbo.service.pdf.test.directory.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/10/26 18:05
 */
@Getter
@Setter
public class RaProjectRegionInterestMarginResponseVO implements Serializable {
    private static final long serialVersionUID = 2285801147693620614L;

    @ApiModelProperty("日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date interestDate;

    @ApiModelProperty("全部债券")
    private BigDecimal allBond;
}
