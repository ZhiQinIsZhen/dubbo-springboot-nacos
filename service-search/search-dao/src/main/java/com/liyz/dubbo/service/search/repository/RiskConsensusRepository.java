package com.liyz.dubbo.service.search.repository;

import com.liyz.dubbo.service.search.model.RiskConsensusDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/1/13 15:23
 */
public interface RiskConsensusRepository extends ElasticsearchRepository<RiskConsensusDO, Long> {


}
