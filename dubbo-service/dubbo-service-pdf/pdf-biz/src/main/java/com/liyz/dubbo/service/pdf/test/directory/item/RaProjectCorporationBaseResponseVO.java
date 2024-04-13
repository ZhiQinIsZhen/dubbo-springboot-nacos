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
 * @date 2022/8/19 14:57
 */
@Getter
@Setter
public class RaProjectCorporationBaseResponseVO implements Serializable {
    private static final long serialVersionUID = 2817872860915281803L;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("社会统一信用代码")
    private String creditCode;

    @ApiModelProperty("公司标签")
    private List<String> labels;

    @ApiModelProperty("公司简称")
    private String abbreviation;

    @ApiModelProperty("人员信息")
    private List<RaProjectPersonBaseResponseVO> personList;
}
