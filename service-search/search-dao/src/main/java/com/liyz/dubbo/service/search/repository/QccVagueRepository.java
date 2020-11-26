package com.liyz.dubbo.service.search.repository;

import com.liyz.dubbo.service.search.model.QccVagueDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/11/17 15:44
 */
public interface QccVagueRepository extends ElasticsearchRepository<QccVagueDO, String> {
}
