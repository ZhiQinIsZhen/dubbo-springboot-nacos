package com.liyz.dubbo.service.search.repository;

import com.liyz.dubbo.service.search.model.CompanyDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 14:49
 */
public interface CompanyRepository extends ElasticsearchRepository<CompanyDO, String> {
}
