package com.liyz.dubbo.service.search.service.impl;

import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.service.util.BeanUtil;
import com.liyz.dubbo.service.search.bo.SearchBO;
import com.liyz.dubbo.service.search.bo.SearchPageBO;
import com.liyz.dubbo.service.search.bo.company.CompanyBO;
import com.liyz.dubbo.service.search.constant.SearchType;
import com.liyz.dubbo.service.search.model.CompanyDO;
import com.liyz.dubbo.service.search.repository.CompanyRepository;
import com.liyz.dubbo.service.search.service.abs.AbstractSearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    protected SearchType getSearchType() {
        return SearchType.COMPANY;
    }

    @Override
    public CompanyBO search(SearchBO searchBO) {
        CompanyDO companyDO = companyRepository.findById(searchBO.getId()).orElse(null);
        return BeanUtil.copyProperties(companyDO, CompanyBO::new);
    }

    @Override
    public List<CompanyBO> searchList(SearchBO searchBO) {
        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("company_name_tag", searchBO.getCompanyName()).slop(100))
                .withPageable(Pageable.ofSize(searchBO.getListMaxCount()))
                .build();
        SearchHits<CompanyDO> searchHits = elasticsearchRestTemplate.search(query, CompanyDO.class, IndexCoordinates.of("search-company-online"));
        return searchHits.getSearchHits().stream().map(item -> BeanUtil.copyProperties(item.getContent(), CompanyBO.class)).collect(Collectors.toList());
    }

    @Override
    public RemotePage<CompanyBO> searchPage(SearchPageBO searchPageBO) {
        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("company_name_tag", searchPageBO.getCompanyName()).slop(100))
                .withPageable(Pageable.ofSize(searchPageBO.getPageSize()).withPage(searchPageBO.getPageNum() - 1))
                .withTrackTotalHits(Boolean.TRUE)
                .build();
        SearchHits<CompanyDO> searchHits = elasticsearchRestTemplate.search(query, CompanyDO.class, IndexCoordinates.of("search-company-online"));
        long pages = Double.valueOf(Math.ceil((double) searchHits.getTotalHits() / searchPageBO.getPageSize())).longValue();
        return new RemotePage<>(searchHits.getSearchHits().stream().map(item -> BeanUtil.copyProperties(item.getContent(), CompanyBO.class)).collect(Collectors.toList()),
                searchHits.getTotalHits(), pages, searchPageBO.getPageNum(), searchPageBO.getPageSize(),
                pages > searchPageBO.getPageNum());
    }
}
