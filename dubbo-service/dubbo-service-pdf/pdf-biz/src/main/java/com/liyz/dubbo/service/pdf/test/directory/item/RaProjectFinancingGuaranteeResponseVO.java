package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author tangtang
 * @description：
 * @date Created in 2022/9/20 13:45
 * @version: 1.0.0
 */
@Setter
@Getter
public class RaProjectFinancingGuaranteeResponseVO implements Serializable {
    private static final long serialVersionUID = -3916141879495592599L;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司标签")
    private List<String> labels;

    /**
     * 流程id
     */
    @ApiModelProperty("流程id")
    private String processId;

    /**
     * 公司id
     */
    @ApiModelProperty("公司id")
    private Long corporationId;

    /**
     * 描述内容
     */
    @ApiModelProperty("描述内容")
    private String describe;
}
