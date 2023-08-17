package com.liyz.dubbo.service.search.service.impl;

import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.service.search.bo.SearchBO;
import com.liyz.dubbo.service.search.bo.company.CompanyBO;
import com.liyz.dubbo.service.search.constant.SearchType;
import com.liyz.dubbo.service.search.model.CompanyDO;
import com.liyz.dubbo.service.search.repository.CompanyRepository;
import com.liyz.dubbo.service.search.service.abs.AbstractSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 15:36
 */
@Slf4j
@Service
public class CompanySearchServiceImpl extends AbstractSearchService<CompanyBO> {

    @Resource
    private CompanyRepository companyRepository;

    @Override
    protected SearchType getSearchType() {
        return SearchType.COMPANY;
    }

    @Override
    public CompanyBO search(SearchBO searchBO) {
        CompanyDO companyDO = companyRepository.findById(searchBO.getId()).orElse(null);
        return BeanUtil.copyProperties(companyDO, CompanyBO::new);
    }
}
