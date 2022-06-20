package com.liyz.dubbo.service.bigdata.service.impl;

import com.liyz.dubbo.service.bigdata.bo.PageBaseBO;
import com.liyz.dubbo.service.bigdata.model.CompanyBasicDO;
import com.liyz.dubbo.service.bigdata.repository.CompanyBasicRepository;
import com.liyz.dubbo.service.bigdata.service.ICompanyBasicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/17 15:57
 */
@Slf4j
@Service
public class CompanyBasicServiceImpl implements ICompanyBasicService {

    @Autowired
    private CompanyBasicRepository companyBasicRepository;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Override
    public Page<CompanyBasicDO> page(PageBaseBO pageBaseBO) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        if (StringUtils.isNotBlank(pageBaseBO.getKeyword())) {
            BoolQueryBuilder keywordMatch = QueryBuilders.boolQuery();
            MatchPhraseQueryBuilder name = QueryBuilders.matchPhraseQuery("name", pageBaseBO.getKeyword()).slop(100);
            keywordMatch.must(name);
            builder.withQuery(keywordMatch);
        }
        Pageable pageable = PageRequest.of(pageBaseBO.getPageNum() >= 1 ? pageBaseBO.getPageNum() - 1 : 0, pageBaseBO.getPageSize());
        builder.withPageable(pageable);
        SearchHits<CompanyBasicDO> searchHits = elasticsearchRestTemplate.search(builder.build(), CompanyBasicDO.class);
        SearchPage<CompanyBasicDO> searchPage = SearchHitSupport.searchPageFor(searchHits, pageable);
        return (Page)SearchHitSupport.unwrapSearchHits(searchPage);
    }

    @Override
    public void delete(String id) {
        companyBasicRepository.deleteById(id);
        String result = elasticsearchRestTemplate.delete(id, CompanyBasicDO.class);
        log.info("result:{}", result);
    }
}
