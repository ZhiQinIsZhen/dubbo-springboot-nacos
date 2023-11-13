package com.liyz.dubbo.service.search.service.abs;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.IdsQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.google.common.collect.Lists;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.service.search.bo.BaseBO;
import com.liyz.dubbo.service.search.exception.RemoteSearchServiceException;
import com.liyz.dubbo.service.search.exception.SearchExceptionCodeEnum;
import com.liyz.dubbo.service.search.query.BasePageQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
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
public abstract class AbstractNewSearchServiceImpl<BO extends BaseBO, BaseQuery extends BasePageQuery> extends AbstractSearchService<BO, BaseQuery> {

    @Resource
    protected ElasticsearchClient client;

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
        SearchRequest request = SearchRequest.of(s -> s.index(properties.getIndex()).query(q -> q
                .ids(IdsQuery.of(idq -> idq.values(id))))
                .from(0)
                .size(10)
        );
        RemotePage<BO> remotePage = doQuery(request);
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
        SearchRequest request = SearchRequest.of(s -> s.index(properties.getIndex()).query(q -> q
                        .ids(IdsQuery.of(idq -> idq.values(ids))))
                .from(0)
                .size(10)
        );
        RemotePage<BO> remotePage = doQuery(request);
        if (CollectionUtils.isEmpty(remotePage.getList())) {
            return Lists.newArrayList();
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
        SearchRequest request = SearchRequest.of(s -> buildQuery(baseQuery).from(0).size(10));
        RemotePage<BO> remotePage = doQuery(request);
        if (CollectionUtils.isEmpty(remotePage.getList())) {
            return null;
        }
        return remotePage.getList().get(0);
    }

    protected SearchRequest.Builder buildQuery(BaseQuery baseQuery) {
        SearchRequest.Builder builder = new SearchRequest.Builder()
                .index(properties.getIndex());
        BoolQuery.Builder bqBuild = new BoolQuery.Builder();
        if (StringUtils.isNotBlank(baseQuery.getCompanyId())) {
            bqBuild.must(m -> m.term(t -> t.field("company_id").value(baseQuery.getCompanyId())));
        }
        if (StringUtils.isNotBlank(baseQuery.getCompanyName())) {
            bqBuild.must(m -> m.match(mp -> mp
                    .field("company_name_tag")
                    .query(baseQuery.getCompanyName())
                    .minimumShouldMatch("85%")
                    .operator(co.elastic.clients.elasticsearch._types.query_dsl.Operator.And))
            );
        }
        Query.Builder qBuild = new Query.Builder();
        qBuild.bool(bqBuild.build());
        builder.query(qBuild.build());
        return builder;
    }

    /**
     * 查询列表数据
     *
     * @param query 查询条件
     * @return 结果
     */
    @Override
    public List<BO> searchList(BaseQuery query) {
        if (StringUtils.isNotBlank(query.getId())) {
            return this.getByIds(Lists.newArrayList(query.getId()));
        }
        if (!CollectionUtils.isEmpty(query.getIds())) {
            return this.getByIds(query.getIds());
        }
        SearchRequest request = SearchRequest.of(s -> buildQuery(query).from(0).size(query.getListMaxCount()));
        RemotePage<BO> remotePage = doQuery(request);
        if (CollectionUtils.isEmpty(remotePage.getList())) {
            return Lists.newArrayList();
        }
        return remotePage.getList();
    }

    /**
     * 分页查询数据
     *
     * @param query 查询条件
     * @return 结果
     */
    @Override
    public RemotePage<BO> searchPage(BaseQuery query) {
        if (StringUtils.isNotBlank(query.getId())) {
            List<BO> boList = this.getByIds(Lists.newArrayList(query.getId()));
            return new RemotePage<>(boList, boList.size(), query.getPageNum(), query.getPageSize());
        }
        if (!CollectionUtils.isEmpty(query.getIds())) {
            List<BO> boList = this.getByIds(query.getIds());
            return new RemotePage<>(boList, boList.size(), query.getPageNum(), query.getPageSize());
        }
        SearchRequest request = SearchRequest.of(s -> buildQuery(query).from(query.getPageNum() - 1).size(query.getPageSize()));
        return doQuery(request);
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
     * @param request 查询条件
     * @return 命中结果
     */
    private RemotePage<BO> doQuery(SearchRequest request) {
        log.info("es index : [{}], body : {}", properties.getIndex(), request.toString());
        try {
            SearchResponse<BO> response = client.search(request, boClass);
            List<BO> boList = response.hits().hits()
                    .stream()
                    .map(item -> {
                        BO bo = item.source();
                        bo.setId(item.id());
                        return bo;
                    })
                    .peek(this::afterHandle)
                    .collect(Collectors.toList());
            return new RemotePage<>(boList, response.hits().hits().size(), Math.max(0, request.from()) + 1, request.size());
        } catch (IOException e) {
            log.error("query es error", e);
            throw new RemoteSearchServiceException(SearchExceptionCodeEnum.ES_SEARCH_ERROR);
        }
    }
}
