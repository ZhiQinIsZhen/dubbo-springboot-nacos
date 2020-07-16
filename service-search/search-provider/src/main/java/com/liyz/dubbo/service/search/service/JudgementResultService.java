package com.liyz.dubbo.service.search.service;

import com.liyz.dubbo.common.remote.bo.PageBaseBO;
import com.liyz.dubbo.service.search.bo.JudgementResultPageQueryBO;
import com.liyz.dubbo.service.search.model.JudgementResultDO;
import com.liyz.dubbo.service.search.repository.JudgementResultRepository;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/6/15 16:20
 */
@Service
public class JudgementResultService {

    @Autowired
    JudgementResultRepository judgementResultRepository;
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 新增/修改
     *
     * @param judgementResultDO
     * @return
     */
    public int save(JudgementResultDO judgementResultDO) {
        List<JudgementResultDO> list = new ArrayList<>();
        list.add(judgementResultDO);
        return save(list);
    }

    /**
     * 新增/修改
     *
     * @param list
     * @return
     */
    public int save(List<JudgementResultDO> list) {
        Iterable<JudgementResultDO> iterable = judgementResultRepository.saveAll(list);
        Iterator<JudgementResultDO> iterator = iterable.iterator();
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
    public void delete(String id) {
        List<String> ids = new ArrayList<>();
        ids.add(id);
        delete(ids);
    }

    /**
     * 删除
     *
     * @param ids
     */
    public void delete(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<JudgementResultDO> doList = new ArrayList<>(ids.size());
            for (String id : ids) {
                JudgementResultDO judgementResultDO = new JudgementResultDO();
                judgementResultDO.setId(id);
                doList.add(judgementResultDO);
            }
            judgementResultRepository.deleteAll(doList);
        }
    }

    /**
     * 简单分页查询数据
     *
     * @param pageBaseBO
     * @return
     */
    public Page<JudgementResultDO> search(PageBaseBO pageBaseBO) {
        Pageable pageable = PageRequest.of(pageBaseBO.getPageNum() >= 1 ? pageBaseBO.getPageNum() - 1 : 0, pageBaseBO.getPageSize());
        return judgementResultRepository.findAll(pageable);
    }

    /**
     * 分页查询
     *
     * @param queryBO
     * @return
     */
    public Page<JudgementResultDO> search(JudgementResultPageQueryBO queryBO) {
        NativeSearchQueryBuilder builder = getBuilder(queryBO);
        //分页
        Pageable pageable = PageRequest.of(queryBO.getPageNum() >= 1 ? queryBO.getPageNum() - 1 : 0, queryBO.getPageSize());
        builder.withPageable(pageable);
        //排序
        builder.withSort(SortBuilders.fieldSort("judge_date").order(SortOrder.DESC));
        builder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = builder.build();
        return judgementResultRepository.search(searchQuery);
    }

    /**
     * 创建queryBuilder
     *
     * @param queryBO
     * @return
     */
    private NativeSearchQueryBuilder getBuilder(JudgementResultPageQueryBO queryBO) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        if (StringUtils.isNotBlank(queryBO.getKeyWord())) {
            BoolQueryBuilder keyWordMatch = QueryBuilders.boolQuery();
            MatchPhraseQueryBuilder title = QueryBuilders.matchPhraseQuery("defendant", queryBO.getKeyWord()).slop(100);
            MatchPhraseQueryBuilder content = QueryBuilders.matchPhraseQuery("accuser",queryBO.getKeyWord()).slop(100);
            keyWordMatch.should(title).should(content);
            builder.withQuery(keyWordMatch);
        }
        return builder;
    }
}
