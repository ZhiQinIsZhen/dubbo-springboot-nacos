package com.liyz.dubbo.service.bigdata.repository;

import com.liyz.dubbo.service.bigdata.model.CompanyBasicDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/17 15:51
 */
public interface CompanyBasicRepository extends ElasticsearchRepository<CompanyBasicDO, String> {
}
