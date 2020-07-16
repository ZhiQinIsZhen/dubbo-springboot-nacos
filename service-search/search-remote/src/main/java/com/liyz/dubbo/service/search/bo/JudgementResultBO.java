package com.liyz.dubbo.service.search.bo;

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
public class JudgementResultBO implements Serializable {
    private static final long serialVersionUID = 4409406614081850959L;

//    @Id
    private String id;

//    @JsonIgnore
    private String courtCity;

    private String city;

//    @JsonIgnore
    private String courtProvince;

    private String amountOfFund;

//    @JsonIgnore
    private String keyWord;

    private String caseReason2;

    private String source;

    private String title;

//    @JsonIgnore
    private String content;

//    @JsonIgnore
    private String createTime;

//    @JsonIgnore
    private String updateTime;

    private String publishTime;

    private String judgeDate;

    private String doctype2;

    private String province;

    private String defendant;

    private String accuser;

//    @JsonIgnore
    private String caseId;

//    @JsonIgnore
    private String judgeData;

//    @JsonIgnore
    private String companyNameTitle;

//    @JsonIgnore
    private String courtArea;

    private String courtName;

//    @JsonIgnore
    private String sentimentScore;

//    @JsonIgnore
    private String otherInfo;

    private String rawContent;

    private String level;

//    @JsonIgnore
    private String docid;

//    @JsonIgnore
    private String encaseType;

    private String sourceType;

//    @JsonIgnore
    private String abstractStr;

    private String url;

    private String doctype;

//    @JsonIgnore
    private String companyName;

//    @JsonIgnore
    private String courtCountry;

    private String district;

//    @JsonIgnore
    private String effectHierarchy;

//    @JsonIgnore
    private String sentimentType;

//    @JsonIgnore
    private String otherName;

//    @JsonIgnore
    private String pickedAbstract;

//    @JsonIgnore
    private String caseReason;
}
