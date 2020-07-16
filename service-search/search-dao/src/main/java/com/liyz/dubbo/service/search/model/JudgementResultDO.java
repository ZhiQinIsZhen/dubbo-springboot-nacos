package com.liyz.dubbo.service.search.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 注释:法诉
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/6/15 15:58
 */
@Getter
@Setter
@Document(indexName = "judgement_result", type = "_doc")
public class JudgementResultDO implements Serializable {
    private static final long serialVersionUID = 4409406614081850959L;

    @Id
    private String id;

    @Field("court_city")
    private String courtCity;

    private String city;

    @Field("court_province")
    private String courtProvince;

    @Field("amount_of_fund")
    private String amountOfFund;

    @Field("key_word")
    private String keyWord;

    @Field("case_reason2")
    private String caseReason2;

    private String source;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String title;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String content;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @Field(name = "create_time", format = DateFormat.custom, type = FieldType.Date, pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(name = "create_time", type = FieldType.Text)
    private String createTime;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @Field(name = "update_time", format = DateFormat.custom, type = FieldType.Date, pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(name = "update_time", type = FieldType.Text)
    private String updateTime;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @Field(name = "publish_time", format = DateFormat.custom, type = FieldType.Date, pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(name = "publish_time", type = FieldType.Text)
    private String publishTime;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @Field(name = "judge_date", format = DateFormat.custom, type = FieldType.Date, pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(name = "judge_date", type = FieldType.Text)
    private String judgeDate;

    private String doctype2;

    private String province;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String defendant;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String accuser;

    @Field("case_id")
    private String caseId;

    @Field("judge_data")
    private String judgeData;

    @Field("company_name_title")
    private String companyNameTitle;

    @Field("court_area")
    private String courtArea;

    @Field("court_name")
    private String courtName;

    @Field("sentiment_score")
    private String sentimentScore;

    @Field("other_info")
    private String otherInfo;

    @Field("raw_content")
    private String rawContent;

    private String level;

    private String docid;

    @Field("encase_type")
    private String encaseType;

    @Field("source_type")
    private String sourceType;

    @Field("abstract")
    private String abstractStr;

    private String url;

    private String doctype;

    @Field("company_name")
    private String companyName;

    @Field("court_country")
    private String courtCountry;

    private String district;

    @Field("effect_hierarchy")
    private String effectHierarchy;

    @Field("sentiment_type")
    private String sentimentType;

    @Field("other_name")
    private String otherName;

    @Field("picked_abstract")
    private String pickedAbstract;

    @Field("case_reason")
    private String caseReason;
}
