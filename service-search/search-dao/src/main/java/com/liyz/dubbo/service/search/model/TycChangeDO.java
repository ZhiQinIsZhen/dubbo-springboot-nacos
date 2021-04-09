package com.liyz.dubbo.service.search.model;

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
 * @date 2020/11/5 10:37
 */
@Getter
@Setter
@Document(indexName = "tyc_change")
public class TycChangeDO implements Serializable {

    @Id
    private String id;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String companyName;
}
