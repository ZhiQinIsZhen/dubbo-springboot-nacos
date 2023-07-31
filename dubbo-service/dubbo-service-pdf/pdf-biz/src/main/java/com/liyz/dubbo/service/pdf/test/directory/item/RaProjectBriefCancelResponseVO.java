package com.liyz.dubbo.service.pdf.test.directory.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:简易注销
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 18:05
 */
@Getter
@Setter
public class RaProjectBriefCancelResponseVO implements Serializable {
    private static final long serialVersionUID = -1147788933507741788L;

    /**
     * 登记机关
     */
    @ApiModelProperty("登记机关")
    private String regAuthority;

    /**
     * 公告期
     */
    @ApiModelProperty("公告期")
    private String announcementTerm;

    /**
     * 异议申请人
     */
    @ApiModelProperty("异议申请人")
    private String objectionApplyPerson;

    @ApiModelProperty("异议内容")
    private String objectionContent;

    /**
     * 异议时间
     */
    @ApiModelProperty("异议时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date objectionDate;

    /**
     * 简易注销结果
     */
    @ApiModelProperty("简易注销结果")
    private String briefCancelResult;

    /**
     * 公告申请日期
     */
    @ApiModelProperty("公告申请日期")
    private String announcementApplyDate;
}
