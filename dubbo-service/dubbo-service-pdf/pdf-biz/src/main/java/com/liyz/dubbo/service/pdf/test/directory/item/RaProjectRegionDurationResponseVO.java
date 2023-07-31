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
 * @date 2022/10/28 10:17
 */
@Getter
@Setter
public class RaProjectRegionDurationResponseVO implements Serializable {
    private static final long serialVersionUID = 1966203741338557879L;

    @ApiModelProperty("日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date durationDate;

    @ApiModelProperty("久期")
    private BigDecimal duration;
}
