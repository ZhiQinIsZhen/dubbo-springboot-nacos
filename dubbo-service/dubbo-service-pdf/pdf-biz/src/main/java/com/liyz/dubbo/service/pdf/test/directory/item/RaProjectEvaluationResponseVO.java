package com.liyz.dubbo.service.pdf.test.directory.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 注释:项目评估-主表
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/19 11:33
 */
@Getter
@Setter
public class RaProjectEvaluationResponseVO implements Serializable {
    private Long id;
    @ApiModelProperty("厂家id")
    private String supplierId;

    @ApiModelProperty("用户corporation_id")
    private String  corporationId;

    @ApiModelProperty("厂家名称")
    private String supplierName;

    @ApiModelProperty("customer账户ID")
    private String memberId;

    @ApiModelProperty("任务编号")
    private String taskNumber;

    @ApiModelProperty("流程id")
    private String processId;

    @ApiModelProperty("项目方公司名称")
    private String name;

    @ApiModelProperty("项目公司的统一信用代码")
    private String uniformCreditCode;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("项目方类型:1直签项目，2总包项目，3挂靠项目")
    private Integer projectPartyType;

    @ApiModelProperty("是否有合同附件 0：没有；1：有")
    private Boolean isContractAttachment;

    @ApiModelProperty("评估模式,1快速评估，2深度评估, 3大客户评估")
    private Integer type;

    @ApiModelProperty("评估状态，1待提交，2评估中，3完成，4已取消")
    private Integer status;

    @ApiModelProperty("评估结果")
    private String result;

    @ApiModelProperty("发起人")
    private String starter;

    @ApiModelProperty("评估发起时间")
    private Date startTime;

    @ApiModelProperty("评估完成时间")
    private Date resultTime;

    @ApiModelProperty("取消时间")
    private Date cancelDatetime;

    @ApiModelProperty("快速评估报告文件地址")
    private String fileKey;

    @ApiModelProperty("深度评估报告文件pdf地址")
    private String pdfFileKey;

    @ApiModelProperty("0未读 1已读")
    private Boolean isRead;

    @ApiModelProperty("评估编码")
    private String evaluationSn;

    @ApiModelProperty("发起人账号")
    private String memberMobile;

    @ApiModelProperty("作用域")
    private Long actionScopeId;

    @ApiModelProperty("'企业编号 | 对应cr_corporation_base的id")
    private Long corporationBaseId;

    @ApiModelProperty(value = "版本号")
    private Integer optVersion;


    @ApiModelProperty(value = "报告类型，房开：ROOM_DEV，城投：URBAN_INVESTMENT")
    private String reportType;

    @ApiModelProperty("数据令牌,用来访问项目信息的时候加固数据安全,在免登录的时候用")
    private String token;
}
