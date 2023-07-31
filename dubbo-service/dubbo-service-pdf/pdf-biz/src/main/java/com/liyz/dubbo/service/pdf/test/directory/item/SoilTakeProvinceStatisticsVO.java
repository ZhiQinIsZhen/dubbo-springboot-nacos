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
 * @date 2023/2/1 17:44
 */
@Getter
@Setter
public class SoilTakeProvinceStatisticsVO implements Serializable {
    private static final long serialVersionUID = -8638488739671405732L;

    @ApiModelProperty("地块数量")
    private Integer soilCount;

    @ApiModelProperty("省份")
    private String province;
}
