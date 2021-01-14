package com.liyz.dubbo.service.search.service;

import com.liyz.dubbo.service.search.model.ShortPathDO;
import com.liyz.dubbo.service.search.repository.ShortPathRepository;
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
 * @date 2021/1/12 16:15
 */
@Service
public class TycChangeService {

    @Autowired
    ShortPathRepository shortPathRepository;
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
            List<ShortPathDO> doList = new ArrayList<>(ids.size());
            for (String id : ids) {
                ShortPathDO shortPathDO = new ShortPathDO();
                shortPathDO.setId(id);
                doList.add(shortPathDO);
            }
            shortPathRepository.deleteAll(doList);
        }
    }
}
