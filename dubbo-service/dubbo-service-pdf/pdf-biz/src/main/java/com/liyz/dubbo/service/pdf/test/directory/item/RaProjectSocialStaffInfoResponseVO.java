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
 * @date 2022/10/17 9:38
 */
@Getter
@Setter
public class RaProjectSocialStaffInfoResponseVO implements Serializable {
    private static final long serialVersionUID = -5592580892573078616L;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("公司标签")
    private List<String> labels;

    @ApiModelProperty("预警信息")
    private String remark;

    @ApiModelProperty("参保人数")
    private List<RaProjectSocialStaffCountResponseVO> countList;
}
