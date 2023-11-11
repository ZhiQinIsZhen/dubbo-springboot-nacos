package com.liyz.dubbo.service.search.service.abs;

import com.google.common.collect.Lists;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import com.liyz.dubbo.service.search.exception.RemoteSearchServiceException;
import com.liyz.dubbo.service.search.exception.SearchExceptionCodeEnum;
import com.liyz.dubbo.service.search.query.BasePageQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
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
public abstract class AbstractOldSearchServiceImpl<BO, BaseQuery extends BasePageQuery> extends AbstractSearchService<BO, BaseQuery> {

    @Resource
    protected RestHighLevelClient restHighLevelClient;

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
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .from(0)
                .size(10)
                .query(QueryBuilders.idsQuery().addIds(id));
        RemotePage<BO> remotePage = this.doQuery(sourceBuilder, null);
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
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .from(0)
                .size(10)
                .query(QueryBuilders.idsQuery().addIds(ids.toArray(new String[0])));
        RemotePage<BO> remotePage = this.doQuery(sourceBuilder, null);
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
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .from(0)
                .size(10)
                .query(this.buildQuery(baseQuery))
                .trackTotalHits(Boolean.TRUE)
                .sort(SortBuilders.scoreSort().order(SortOrder.DESC))
                ;
        RemotePage<BO> remotePage = doQuery(sourceBuilder, null);
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
     * @param searchSourceBuilder 查询条件
     * @param routingValue 路由值
     * @return 命中结果
     */
    private RemotePage<BO> doQuery(SearchSourceBuilder searchSourceBuilder, String routingValue) {
        SearchRequest searchRequest = new SearchRequest(properties.getIndex())
                .source(searchSourceBuilder)
                .routing(routingValue);
        log.info("es index : [{}], body : {}", properties.getIndex(), searchRequest.source());
        SearchResponse response;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("search error", e);
            throw new RemoteSearchServiceException(SearchExceptionCodeEnum.ES_SEARCH_ERROR);
        }
        SearchHits searchHits = response.getHits();
        TotalHits totalHits = searchHits.getTotalHits();
        long total = totalHits.value;
        List<BO> boList = Arrays.stream(searchHits.getHits())
                .map(item -> JsonMapperUtil.readValue(item.getSourceAsString(), boClass))
                .peek(this::afterHandle)
                .collect(Collectors.toList());
        return new RemotePage<>(boList, total, searchSourceBuilder.from() + 1, searchSourceBuilder.size());
    }
}
