package com.liyz.dubbo.service.search.repository;

import com.liyz.dubbo.service.search.model.TycChangeDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/11/5 10:40
 */
public interface TycChangeRepository extends ElasticsearchRepository<TycChangeDO, String> {
}
