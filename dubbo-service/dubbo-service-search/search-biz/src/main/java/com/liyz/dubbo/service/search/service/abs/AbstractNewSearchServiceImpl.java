package com.liyz.dubbo.service.search.service.abs;

import com.google.common.collect.Lists;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.service.search.exception.RemoteSearchServiceException;
import com.liyz.dubbo.service.search.exception.SearchExceptionCodeEnum;
import com.liyz.dubbo.service.search.query.BasePageQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/11/11 14:39
 */
@Slf4j
public abstract class AbstractNewSearchServiceImpl<BO, BaseQuery extends BasePageQuery> extends AbstractSearchService<BO, BaseQuery> {

    @Resource
    protected ElasticsearchRestTemplate esRestTemplate;

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 结果
     */
    @Override
    public BO getById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withIds(id)
                .withPageable(Pageable.ofSize(1).withPage(0))
                .build();
        RemotePage<BO> remotePage = doQuery(query);
        if (CollectionUtils.isEmpty(remotePage.getList())) {
            return null;
        }
        return remotePage.getList().get(0);
    }

    /**
     * 根据主键列表查询
     *
     * @param ids 主键列表
     * @return 结果
     */
    @Override
    public List<BO> getByIds(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withIds(ids)
                .withPageable(Pageable.ofSize(ids.size()).withPage(0))
                .build();
        RemotePage<BO> remotePage = doQuery(query);
        if (CollectionUtils.isEmpty(remotePage.getList())) {
            return null;
        }
        return remotePage.getList();
    }

    /**
     * 查询单条数据
     *
     * @param baseQuery 查询条件
     * @return 结果
     */
    @Override
    public BO search(BaseQuery baseQuery) {
        if (StringUtils.isNotBlank(baseQuery.getId())) {
            return this.getById(baseQuery.getId());
        }
        if (!CollectionUtils.isEmpty(baseQuery.getIds())) {
            List<BO> boList = this.getByIds(baseQuery.getIds());
            return CollectionUtils.isEmpty(boList) ? null : boList.get(0);
        }
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(this.buildQuery(baseQuery))
                .withPageable(Pageable.ofSize(baseQuery.getPageSize()).withPage(baseQuery.getPageNum() - 1))
                .withTrackTotalHits(Boolean.TRUE)
                .withMaxResults(baseQuery.getListMaxCount())
                .withSorts(SortBuilders.scoreSort().order(SortOrder.DESC))
                .build();
        RemotePage<BO> remotePage = doQuery(query);
        if (CollectionUtils.isEmpty(remotePage.getList())) {
            return null;
        }
        return remotePage.getList().get(0);
    }

    protected QueryBuilder buildQuery(BaseQuery baseQuery) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(baseQuery.getCompanyId())) {
            queryBuilder.filter(QueryBuilders
                    .boolQuery()
                    .filter(QueryBuilders.termQuery("company_id", baseQuery.getCompanyId()))
            );
        }
        if (StringUtils.isNotBlank(baseQuery.getCompanyName())) {
            queryBuilder.must(QueryBuilders
                    .matchQuery("company_name_tag", baseQuery.getCompanyName())
                    .operator(Operator.AND)
                    .minimumShouldMatch("85%")
            );
        }
        return queryBuilder;
    }

    /**
     * 查询列表数据
     *
     * @param query 查询条件
     * @return 结果
     */
    @Override
    public List<BO> searchList(BaseQuery query) {
        throw new RemoteSearchServiceException(SearchExceptionCodeEnum.NOT_SUPPORT_METHOD);
    }

    /**
     * 分页查询数据
     *
     * @param query 查询条件
     * @return 结果
     */
    @Override
    public RemotePage<BO> searchPage(BaseQuery query) {
        throw new RemoteSearchServiceException(SearchExceptionCodeEnum.NOT_SUPPORT_METHOD);
    }

    /**
     * 后置数据处理
     *
     * @param bo 数据体
     */
    protected void afterHandle(BO bo) {

    }

    /**
     * 查询es
     *
     * @param query 查询条件
     * @return 命中结果
     */
    private RemotePage<BO> doQuery(NativeSearchQuery query) {
        SearchHits<BO> searchHits = esRestTemplate.search(query, boClass, IndexCoordinates.of(properties.getIndex()));
        log.info("es index : [{}], hits : {}, body : {}", properties.getIndex(), searchHits.getTotalHits(), query);
        Pageable pageable = query.getPageable();
        List<BO> boList = searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .peek(this::afterHandle)
                .collect(Collectors.toList());
        return new RemotePage<>(boList, searchHits.getTotalHits(), Math.max(0, pageable.getPageNumber()) + 1, pageable.getPageSize());
    }
}
