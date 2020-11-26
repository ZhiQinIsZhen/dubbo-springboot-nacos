package com.liyz.dubbo.service.search.service;

import com.liyz.dubbo.service.search.model.QccVagueDO;
import com.liyz.dubbo.service.search.repository.QccVagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/11/17 15:45
 */
@Service
public class QccVagueService {

    @Autowired
    QccVagueRepository qccVagueRepository;
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

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
            List<QccVagueDO> doList = new ArrayList<>(ids.size());
            for (String id : ids) {
                QccVagueDO qccVagueDO = new QccVagueDO();
                qccVagueDO.setId(id);
                doList.add(qccVagueDO);
            }
            qccVagueRepository.deleteAll(doList);
        }
    }
}
