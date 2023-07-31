package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:税收违法
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 17:50
 */
@Getter
@Setter
public class RaProjectTaxContraventionResponseVO implements Serializable {
    private static final long serialVersionUID = -4193589219752601835L;

    /**
     * 关联公司名称
     */
    @ApiModelProperty("关联公司名称")
    private String relationCompanyName;
    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private String publishTime;
    /**
     * 案件性质
     */
    @ApiModelProperty("案件性质")
    private String caseType;
    /**
     * 所属税务机关
     */
    @ApiModelProperty("所属税务机关")
    private String department;
    /**
     * 纳税人名称
     */
    @ApiModelProperty("纳税人名称")
    private String taxpayerName;
}
