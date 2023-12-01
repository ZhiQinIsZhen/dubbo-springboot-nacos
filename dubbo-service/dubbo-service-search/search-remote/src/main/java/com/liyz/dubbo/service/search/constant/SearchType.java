package com.liyz.dubbo.service.search.constant;

import com.liyz.dubbo.service.search.bo.bid.BiddingBO;
import com.liyz.dubbo.service.search.bo.company.CompanyBO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Desc:查询类型枚举
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 15:10
 */
@Getter
@AllArgsConstructor
public enum SearchType {
    COMPANY(CompanyBO.class, "公司基本信息"),
    BIDDING(BiddingBO.class, "招投标"),
    ;

    private final Class<?> clazz;

    private final String desc;
}
