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
 * @date 2022/10/27 10:33
 */
@Getter
@Setter
public class RaProjectGovGrantsResponseVO implements Serializable {
    private static final long serialVersionUID = 223450920395739189L;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司标签")
    private List<String> labels;

    @ApiModelProperty("明细数据")
    private List<RaProjectBigDataGovGrantsResponseVO> items;
}
