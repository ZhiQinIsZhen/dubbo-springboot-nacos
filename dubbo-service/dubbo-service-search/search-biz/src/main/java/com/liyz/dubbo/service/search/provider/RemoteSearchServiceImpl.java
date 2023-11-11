package com.liyz.dubbo.service.search.provider;

import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.service.search.constant.SearchType;
import com.liyz.dubbo.service.search.query.BasePageQuery;
import com.liyz.dubbo.service.search.remote.RemoteSearchService;
import com.liyz.dubbo.service.search.service.abs.AbstractSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 14:27
 */
@Slf4j
@DubboService
public class RemoteSearchServiceImpl implements RemoteSearchService {

    /**
     * 查询单条数据
     *
     * @param searchType 查询类型
     * @param query 查询条件
     * @return 结果
     * @param <T> 泛型
     */
    @Override
    public <T> T search(SearchType searchType, BasePageQuery query) {
        return (T) AbstractSearchService.getSearchService(searchType).search(query);
    }

    /**
     * 查询列表数据
     *
     * @param searchType 查询类型
     * @param query 查询条件
     * @return 结果
     * @param <T> 泛型
     */
    @Override
    public <T> List<T> searchList(SearchType searchType, BasePageQuery query) {
        return (List<T>) AbstractSearchService.getSearchService(searchType).searchList(query);
    }

    /**
     * 分页查询数据
     *
     * @param searchType 查询类型
     * @param query 查询条件
     * @return 结果
     * @param <T> 泛型
     */
    @Override
    public <T> RemotePage<T> searchPage(SearchType searchType, BasePageQuery query) {
        return (RemotePage<T>) AbstractSearchService.getSearchService(searchType).searchPage(query);
    }
}
