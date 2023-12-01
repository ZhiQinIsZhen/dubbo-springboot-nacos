package com.liyz.dubbo.service.search.repository;

import com.liyz.dubbo.service.search.model.BiddingDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/12/1 9:56
 */
public interface BiddingRepository extends ElasticsearchRepository<BiddingDO, String> {
}
