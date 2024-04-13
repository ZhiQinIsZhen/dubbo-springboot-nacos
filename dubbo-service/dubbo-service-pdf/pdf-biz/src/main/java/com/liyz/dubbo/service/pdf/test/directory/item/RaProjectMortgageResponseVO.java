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
 * @date 2022/5/20 9:38
 */
@Getter
@Setter
public class RaProjectMortgageResponseVO implements Serializable {
    private static final long serialVersionUID = -6912655888741023136L;

    /**
     * 关联公司名称
     */
    @ApiModelProperty("关联公司名称")
    private String relationCompanyName;
    //登记日期
    @ApiModelProperty("登记日期")
    private String regDate;

    //登记编号
    @ApiModelProperty("登记编号")
    private String regNum;

    //被担保债权种类
    @ApiModelProperty("被担保债权种类")
    private String type;

    @ApiModelProperty("被担保债权数额")
    private String amount;

    @ApiModelProperty("登记机关")
    private String regDepartment;

    @ApiModelProperty("状态")
    private String status;
}
