package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/28 15:04
 */
@Data
public class RaProjectRiskScanObjectResponseVO implements Serializable {

    @ApiModelProperty("总数")
    private Integer riskQuan;

    @ApiModelProperty("列表")
    private List<RaProjectRiskScanResponseVO> riskScans;
}
