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
 * @date 2022/8/19 14:57
 */
@Getter
@Setter
public class RaProjectPersonBaseResponseVO implements Serializable {
    private static final long serialVersionUID = 2817872860915281803L;

    @ApiModelProperty("人员名称")
    private String personName;

    @ApiModelProperty("人员角色")
    private String personRole;

    
}
