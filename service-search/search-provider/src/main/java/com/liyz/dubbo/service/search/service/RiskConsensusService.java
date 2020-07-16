package com.liyz.dubbo.service.search.service;

import com.liyz.dubbo.common.remote.bo.PageBaseBO;
import com.liyz.dubbo.service.search.bo.RiskConsensusPageQueryBO;
import com.liyz.dubbo.service.search.model.RiskConsensusDO;
import com.liyz.dubbo.service.search.repository.RiskConsensusRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/1/13 15:08
 */
@Slf4j
@Service
public class RiskConsensusService {

    @Autowired
    RiskConsensusRepository riskConsensusRepository;
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 新增/修改
     *
     * @param riskConsensusDO
     * @return
     */
    public int save(RiskConsensusDO riskConsensusDO) {
        List<RiskConsensusDO> list = new ArrayList<>();
        list.add(riskConsensusDO);
        return save(list);
    }

    /**
     * 新增/修改
     *
     * @param list
     * @return
     */
    public int save(List<RiskConsensusDO> list) {
        Iterable<RiskConsensusDO> iterable = riskConsensusRepository.saveAll(list);
        Iterator<RiskConsensusDO> iterator = iterable.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        return count;
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delete(Long id) {
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        delete(ids);
    }

    /**
     * 删除
     *
     * @param ids
     */
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<RiskConsensusDO> doList = new ArrayList<>(ids.size());
            for (Long id : ids) {
                RiskConsensusDO riskConsensusDO = new RiskConsensusDO();
                riskConsensusDO.setId(id);
                doList.add(riskConsensusDO);
            }
            riskConsensusRepository.deleteAll(doList);
        }
    }

    /**
     * 简单分页查询数据
     *
     * @param pageBaseBO
     * @return
     */
    public Page<RiskConsensusDO> search(PageBaseBO pageBaseBO) {
        Pageable pageable = PageRequest.of(pageBaseBO.getPageNum() >= 1 ? pageBaseBO.getPageNum() - 1 : 0, pageBaseBO.getPageSize());
        return riskConsensusRepository.findAll(pageable);
    }

    /**
     * 分页查询
     *
     * @param queryBO
     * @return
     */
    public Page<RiskConsensusDO> search(RiskConsensusPageQueryBO queryBO) {
        NativeSearchQueryBuilder builder = getBuilder(queryBO);
        //分页
        Pageable pageable = PageRequest.of(queryBO.getPageNum() >= 1 ? queryBO.getPageNum() - 1 : 0, queryBO.getPageSize());
        builder.withPageable(pageable);
        //排序
        builder.withSort(SortBuilders.fieldSort("publishTime").order(SortOrder.DESC));
        builder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = builder.build();
        return riskConsensusRepository.search(searchQuery);
    }

    private NativeSearchQueryBuilder getBuilder(RiskConsensusPageQueryBO queryBO) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //过滤
        if (StringUtils.isNotBlank(queryBO.getSentimentType())) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String[] types = queryBO.getSentimentType().split(",");
            BoolQueryBuilder sourceTerm = QueryBuilders.boolQuery();
            for (String type : types) {
                sourceTerm.should(QueryBuilders.termQuery("sentimentType", type));
            }
            boolQueryBuilder.must(sourceTerm);
            builder.withFilter(boolQueryBuilder);
        }
        if (StringUtils.isNotBlank(queryBO.getSourceType())) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String[] types = queryBO.getSourceType().split(",");
            BoolQueryBuilder sourceTerm = QueryBuilders.boolQuery();
            for (String type : types) {
                sourceTerm.should(QueryBuilders.termQuery("sourceType", type));
            }
            boolQueryBuilder.must(sourceTerm);
            builder.withFilter(boolQueryBuilder);
        }
        if (StringUtils.isNotBlank(queryBO.getKeyWord())) {
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("title", queryBO.getKeyWord()),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("context", queryBO.getKeyWord()),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            builder.withQuery(functionScoreQueryBuilder);
        }
        return builder;
    }

    /**
     * 分页查询--高亮
     *
     * @param queryBO
     * @return
     */
    public Page<RiskConsensusDO> searchForHighlight(RiskConsensusPageQueryBO queryBO) {
        NativeSearchQueryBuilder builder = getBuilder(queryBO);
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("title").field("content").numOfFragments(0);
        builder.withHighlightBuilder(highlightBuilder);
        //分页
        Pageable pageable = PageRequest.of(queryBO.getPageNum() >= 1 ? queryBO.getPageNum() - 1 : 0, queryBO.getPageSize());
        builder.withPageable(pageable);
        //排序
        builder.withSort(SortBuilders.fieldSort("publishTime").order(SortOrder.DESC));
        builder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = builder.build();
        return riskConsensusRepository.search(searchQuery);
    }

    /**
     * 聚合分页查询
     *
     * @param queryBO
     */
    public Map<String,Object> aggregateForSentimentType(RiskConsensusPageQueryBO queryBO) {
        NativeSearchQueryBuilder builder = getBuilder(queryBO);
        //聚合
        builder.addAggregation(AggregationBuilders.terms("sentimentTypes").field("sentimentType"));
        NativeSearchQuery searchQuery = builder.build();
        SearchHits<RiskConsensusDO> searchHits = elasticsearchRestTemplate.search(searchQuery, RiskConsensusDO.class, IndexCoordinates.of("risk_consensus"));
        if (!searchHits.hasAggregations()) {
            return null;
        }
        Aggregations aggregations = searchHits.getAggregations();
        Terms terms = aggregations.get("sentimentTypes");
        Map<String,Object> agg = new HashMap<>();
        for (Terms.Bucket entry : terms.getBuckets()) {
            agg.put(entry.getKey().toString(),entry.getDocCount());
        }
        return agg;
    }
}
