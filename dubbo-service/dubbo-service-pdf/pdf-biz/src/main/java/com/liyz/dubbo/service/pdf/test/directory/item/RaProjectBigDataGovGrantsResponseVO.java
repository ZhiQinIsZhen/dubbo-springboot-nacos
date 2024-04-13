package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/10/27 10:22
 */
@Getter
@Setter
public class RaProjectBigDataGovGrantsResponseVO implements Serializable {
    private static final long serialVersionUID = -3971920380160961715L;

    @ApiModelProperty("年份")
    private Integer year;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("计入递延收益")
    private BigDecimal totalIncome;

    @ApiModelProperty("计入当前损益")
    private BigDecimal totalProfitsLosses;

    public BigDecimal getTotal() {
        return (Objects.nonNull(totalIncome) ? totalIncome : BigDecimal.ZERO).add(Objects.nonNull(totalProfitsLosses) ? totalProfitsLosses : BigDecimal.ZERO);
    }
}
