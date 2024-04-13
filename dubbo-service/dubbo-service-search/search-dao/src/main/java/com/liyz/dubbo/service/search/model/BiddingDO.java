package com.liyz.dubbo.service.search.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/12/1 9:34
 */
@Getter
@Setter
@Document(indexName = "bidding-v1120", createIndex = false)
public class BiddingDO implements Serializable {
    private static final long serialVersionUID = -4916393730895627195L;

    @Id
    private String  id;

    /**
     * 中标企业
     */
    @Field(name = "bid_win", type = FieldType.Keyword)
    private String bidWin;

    @Field(name = "business_type", type = FieldType.Keyword)
    private String businessType;

    @Field(name = "province", type = FieldType.Keyword)
    private String province;

    @Field(name = "city", type = FieldType.Keyword)
    private String city;

    @Field(name = "data_source", type = FieldType.Keyword)
    private String dataSource;

    @Field(name = "involving_money", type = FieldType.Keyword)
    private String involvingMoney;

    @Field(name = "use_flag", type = FieldType.Integer)
    private Integer useFlag;

    @Field(name = "is_hide", type = FieldType.Integer)
    private Integer isHide;

    @Field(name = "max_budget", type = FieldType.Keyword)
    private String maxBudget;

    @Field(name = "notice_type", type = FieldType.Keyword)
    private String noticeType;

    @Field(name = "notice_type_sub", type = FieldType.Keyword)
    private String noticeTypeSub;

    @Field(name = "primary_key", type = FieldType.Keyword)
    private String primaryKey;

    @Field(name = "proxy", type = FieldType.Keyword)
    private String proxy;

    /**
     * 发布日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(name = "publish_time", type = FieldType.Date, format = {DateFormat.epoch_millis, DateFormat.date_optional_time,
            DateFormat.year_month_day, DateFormat.date_hour_minute_second}, pattern = {"yyyy-MM-dd HH:mm:ss"})
    private Date publishTime;

    /**
     * 发布年份
     */
    @Field(name = "publish_time_year", type = FieldType.Keyword)
    private String publishTimeYear;

    @Field(name = "purchaser", type = FieldType.Keyword)
    private String purchaser;

    @Field(name = "title", type = FieldType.Text)
    private String title;

    @Field(name = "union_id", type = FieldType.Keyword)
    private String unionId;

    @Field(name = "unique_id", type = FieldType.Keyword)
    private String uniqueId;

    @Field(name = "update_source", type = FieldType.Integer)
    private Integer updateSource;

    @Field(name = "update_source_id", type = FieldType.Keyword)
    private String updateSourceId;

    @Field(name = "party", type = FieldType.Nested)
    private List<Party> party;


    @Getter
    @Setter
    public static class Party implements Serializable {
        private static final long serialVersionUID = 2530388216786274320L;

        /**
         * 公司ID
         */
        @Field(name = "company_id", type = FieldType.Keyword)
        private String companyId;

        /**
         * 参与方名称
         */
        @Field(name = "party_name", type = FieldType.Text)
        private String partyName;

        /**
         * 参与方类型
         */
        @Field(name = "party_type", type = FieldType.Keyword)
        private String partyType;

        /**
         * 发布日期
         */
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @Field(name = "publish_time", type = FieldType.Date, format = {DateFormat.epoch_millis, DateFormat.date_optional_time,
                DateFormat.year_month_day, DateFormat.date_hour_minute_second}, pattern = {"yyyy-MM-dd HH:mm:ss"})
        private Date publishTime;

        /**
         * 省份
         */
        @Field(name = "province", type = FieldType.Keyword)
        private String province;

        /**
         * 最高预算价
         */
        @Field(name = "max_budget", type = FieldType.Keyword)
        private String maxBudget;

        /**
         * 使用标记，0正常，1人工已修正，9删除
         */
        @Field(name = "is_hide", type = FieldType.Keyword)
        private String isHide;
    }
}
