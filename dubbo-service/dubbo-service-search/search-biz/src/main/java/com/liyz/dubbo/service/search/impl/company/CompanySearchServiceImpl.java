package com.liyz.dubbo.service.search.impl.company;

import com.liyz.dubbo.service.search.bo.company.CompanyBO;
import com.liyz.dubbo.service.search.constant.SearchType;
import com.liyz.dubbo.service.search.query.company.CompanySearchQuery;
import com.liyz.dubbo.service.search.service.abs.AbstractNewSearchServiceImpl;
import com.liyz.dubbo.service.search.service.abs.AbstractOldSearchServiceImpl;
import com.liyz.dubbo.service.search.service.abs.AbstractSearchServiceImpl;
import com.liyz.dubbo.service.search.service.company.CompanySearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 15:36
 */
@Slf4j
@Service
public class CompanySearchServiceImpl extends AbstractNewSearchServiceImpl<CompanyBO, CompanySearchQuery>
        implements CompanySearchService {

    @Override
    protected SearchType getSearchType() {
        return SearchType.COMPANY;
    }
}
