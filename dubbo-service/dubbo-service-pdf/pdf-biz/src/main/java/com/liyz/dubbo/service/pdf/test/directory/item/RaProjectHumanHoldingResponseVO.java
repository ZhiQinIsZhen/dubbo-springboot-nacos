package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:最终受益人
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/18 16:43
 */
@Getter
@Setter
public class RaProjectHumanHoldingResponseVO implements Serializable {
    private static final long serialVersionUID = -8922376881812941031L;

    /**
     * 受益人名称
     */
    @ApiModelProperty("受益人名称")
    private String name;

    /**
     * 受益人类型:human;company
     */
    @ApiModelProperty("受益人类型:human;company")
    private String type;
    /**
     * 持股比例
     */
    @ApiModelProperty("持股比例")
    private String percent;

    /**
     * 持股公司
     */
    @ApiModelProperty("持股公司")
    private String companyName;

    /**
     * 链路item
     */
    @ApiModelProperty("链路item")
    private List<List<RaProjectHumanHoldingLineEntryResponseVO>> lineItems;
}
