package com.liyz.dubbo.api.web.vo.search;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/6/15 15:58
 */
@Getter
@Setter
public class JudgementResultVO implements Serializable {
    private static final long serialVersionUID = 4409406614081850959L;

    @ApiModelProperty(value = "信息id", example = "2012")
    private String id;

    @ApiModelProperty(value = "法院所在城市", example = "杭州")
    private String courtCity;

    private String city;

    @ApiModelProperty(value = "法院所在省份", example = "浙江省")
    private String courtProvince;

    @ApiModelProperty(value = "涉案金额", example = "12.12")
    private String amountOfFund;

    @ApiModelProperty(value = "关键词", example = "啦啦啦")
    private String keyWord;

    @ApiModelProperty(value = "案件原由", example = "经济纠纷")
    private String caseReason2;

    @ApiModelProperty(value = "案件来源", example = "裁判文书网")
    private String source;

    @ApiModelProperty(value = "案件标题", example = "阿里爸爸和马爸爸的纠纷")
    private String title;

    @ApiModelProperty(value = "案件内容", example = "浙江省高级人民法院。。。。")
    private String content;

    @ApiModelProperty(value = "案件创建时间", example = "2020-07-16")
    private String createTime;

    private String updateTime;

    @ApiModelProperty(value = "案件发布时间", example = "2020-07-16")
    private String publishTime;

    @ApiModelProperty(value = "案件上诉时间", example = "2020-07-16")
    private String judgeDate;

    @ApiModelProperty(value = "案件类型", example = "刑事案件")
    private String doctype2;

    private String province;

    @ApiModelProperty(value = "案件原告", example = "阿里爸爸")
    private String defendant;

    @ApiModelProperty(value = "案件被告", example = "马爸爸")
    private String accuser;

    @ApiModelProperty(value = "案件id", example = "HHKK89")
    private String caseId;

    private String judgeData;

    private String companyNameTitle;

    private String courtArea;

    private String courtName;

    private String sentimentScore;

    private String otherInfo;

    @ApiModelProperty(value = "案件内容", example = "裁判文书网。。。")
    private String rawContent;

    @ApiModelProperty(value = "法院等级", example = "高级")
    private String level;

    private String docid;

    private String encaseType;

    private String sourceType;

    private String abstractStr;

    @ApiModelProperty(value = "案件url", example = "http://www.judege.cn")
    private String url;

    private String doctype;

    private String companyName;

    private String courtCountry;

    private String district;

    private String effectHierarchy;

    private String sentimentType;

    private String otherName;

    private String pickedAbstract;

    private String caseReason;
}
