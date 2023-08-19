package com.liyz.dubbo.service.search.constant;

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
@AllArgsConstructor
public enum SearchType {
    COMPANY(CompanyBO.class, "公司基本信息"),
    ;

    @Getter
    private final Class<?> clazz;

    @Getter
    private final String desc;
}
