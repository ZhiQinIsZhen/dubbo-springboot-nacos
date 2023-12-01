package com.liyz.dubbo.service.search.bo.bid;

import com.liyz.dubbo.service.search.bo.BaseBO;
import lombok.Getter;
import lombok.Setter;

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
public class BiddingBO extends BaseBO {
    private static final long serialVersionUID = -4916393730895627195L;

    /**
     * 中标企业
     */
    private String bidWin;

    private String businessType;

    private String province;

    private String city;

    private String dataSource;

    private String involvingMoney;

    private Integer useFlag;

    private Integer isHide;

    private String maxBudget;

    private String noticeType;

    private String noticeTypeSub;

    private String primaryKey;

    private String proxy;

    /**
     * 发布日期
     */
    private Date publishTime;

    /**
     * 发布年份
     */
    private String publishTimeYear;

    private String purchaser;

    private String title;

    private String unionId;

    private String uniqueId;

    private Integer updateSource;

    private String updateSourceId;

    private List<Party> party;


    @Getter
    @Setter
    public static class Party implements Serializable {
        private static final long serialVersionUID = 2530388216786274320L;

        /**
         * 公司ID
         */
        private String companyId;

        /**
         * 参与方名称
         */
        private String partyName;

        /**
         * 参与方类型
         */
        private String partyType;

        /**
         * 发布日期
         */
        private Date publishTime;

        /**
         * 省份
         */
        private String province;

        /**
         * 最高预算价
         */
        private String maxBudget;

        /**
         * 使用标记，0正常，1人工已修正，9删除
         */
        private String isHide;
    }
}
