package com.liyz.dubbo.service.pdf.test.directory.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/20 9:56
 */
@Getter
@Setter
public class RaProjectEquityResponseVO implements Serializable {
    private static final long serialVersionUID = 480890319839637005L;

    /**
     * 关联公司名称
     */
    @ApiModelProperty("关联公司名称")
    private String relationCompanyName;

    //股权出质设立发布日期
    @ApiModelProperty("股权出质设立发布日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date putDate;

    //登记编号
    @ApiModelProperty("登记编号")
    private String regNumber;

    //出质人
    @ApiModelProperty("出质人")
    private String pledgor;

    //质权人
    @ApiModelProperty("质权人")
    private String pledgee;

    //出质股权数额
    @ApiModelProperty("出质股权数额")
    private String equityAmount;
    //状态
    @ApiModelProperty("state")
    private String state;
}
