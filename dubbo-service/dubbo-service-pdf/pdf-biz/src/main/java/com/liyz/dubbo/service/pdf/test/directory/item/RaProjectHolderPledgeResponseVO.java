package com.liyz.dubbo.service.pdf.test.directory.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:股东质押明细
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/20 10:08
 */
@Getter
@Setter
public class RaProjectHolderPledgeResponseVO implements Serializable {
    private static final long serialVersionUID = -6038406741572074598L;

    /**
     * 公告日期
     */
    @ApiModelProperty("公告日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date annDate;

    /**
     * 股东名称
     */
    @ApiModelProperty("股东名称")
    private String shareHolder;

    /**
     * 质押股份数量
     */
    @ApiModelProperty("质押股份数量")
    private String pledgeAmount;

    /**
     * 质押股份市值（元）
     */
    @ApiModelProperty("质押股份市值（元）")
    private String lastValue;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private String status;
}
