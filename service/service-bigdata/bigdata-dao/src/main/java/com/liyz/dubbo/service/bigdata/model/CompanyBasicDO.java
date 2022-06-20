package com.liyz.dubbo.service.bigdata.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/17 15:46
 */
@Getter
@Setter
@Document(indexName = "company_basic")
public class CompanyBasicDO implements Serializable {
    private static final long serialVersionUID = -1896609074308018355L;

    /**
     * 公司id
     */
    @Id
    private Long id;

    /**
     * 注册号
     */
    @Field(type = FieldType.Keyword)
    private String regNumber;

    /**
     * 经营状态
     */
    private String regStatus;

    /**
     * 统一社会信用代码
     */
    @Field(type = FieldType.Keyword)
    private String creditCode;

    /**
     * 成立日期
     */
    private String estiblishTime;

    /**
     * 注册资本
     */
    private String regCapital;

    /**
     * 公司类型 1-公司，2-香港公司，3-社会组织，4-律所，5-事业单位，6-基金会
     */
    @Field(type = FieldType.Integer)
    private Integer companyType;

    /**
     * 公司名
     */
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;

    /**
     * 组织机构代码
     */
    private String orgNumber;

    /**
     * 1-公司 2-人
     */
    @Field(type = FieldType.Integer)
    private Integer type;

    /**
     * 省份
     */
    private String base;

    /**
     * 法人
     */
    private String legalPersonName;
}
