package com.liyz.dubbo.service.search.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/10/10 11:06
 */
@Getter
@Setter
@Document(indexName = "qcc_mh_data", type = "_doc")
public class QccVagueDO implements Serializable {
    private static final long serialVersionUID = -944360333986051930L;

    @Id
    private String id;

    /**内部KeyNo*/
    private String keyNo;

    /**公司名称*/
    private String name;

    /**法人名称*/
    private String operName;

    /**成立日期*/
    private String startDate;

    /**企业状态*/
    private String status;

    /**注册号*/
    private String no;

    /**社会统一信用代码*/
    private String creditCode;
}