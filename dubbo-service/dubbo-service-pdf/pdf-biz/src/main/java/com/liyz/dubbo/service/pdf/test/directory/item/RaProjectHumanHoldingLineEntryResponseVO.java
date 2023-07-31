package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:链路路劲
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/18 16:46
 */
@Getter
@Setter
public class RaProjectHumanHoldingLineEntryResponseVO implements Serializable {
    private static final long serialVersionUID = -4579412005779526882L;

    /**
     * 类型：human、company、percent
     */
    @ApiModelProperty("类型：human、company、percent")
    private String type;

    /**
     * 公司名称、人名、占比
     */
    @ApiModelProperty("公司名称、人名、占比")
    private String value;
}
