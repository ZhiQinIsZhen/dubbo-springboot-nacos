package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * description: TODO
 * author: huanglb
 * date 2022/11/23 14:55
 */
@Getter
@Setter
public class OperationDataResponseVO extends  IndicatorDataResponseVO implements Serializable  {

    /**
     * 数据明细
     */
    @ApiModelProperty("数据明细")
    private List<OperationYearDetailResponseVO> yearDetailList;
}
