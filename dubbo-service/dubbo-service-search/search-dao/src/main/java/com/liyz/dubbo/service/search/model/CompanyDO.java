package com.liyz.dubbo.service.search.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 14:41
 */
@Getter
@Setter
@Document(indexName = "search-company-online", createIndex = false)
public class CompanyDO implements Serializable {
    private static final long serialVersionUID = -6944216764404424279L;

    @Id
    private String id;

    @Field(name = "company_id", type = FieldType.Keyword)
    private String companyId;

    @Field(name = "company_name_tag", type = FieldType.Text)
    private String companyName;

    @Field(name = "company_code", type = FieldType.Keyword)
    private String regNumber;

    @Field(name = "credit_no", type = FieldType.Keyword)
    private String creditCode;

    @Field(name = "legal_person_flag", type = FieldType.Keyword)
    private String legalPerson;

    @Field(name = "address_tag", type = FieldType.Keyword)
    private String address;

    @Field(name = "establishment_time", type = FieldType.Date, format = {DateFormat.epoch_second})
    private Date establishmentTime;
}
