package com.liyz.dubbo.service.search.remote;

import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.service.search.constant.SearchType;
import com.liyz.dubbo.service.search.query.BasePageQuery;

import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 14:27
 */
public interface RemoteSearchService {

    /**
     * 查询单条数据
     *
     * @param searchType 查询类型
     * @param query 查询条件
     * @return 结果
     * @param <T> 泛型
     */
    <T> T search(SearchType searchType, BasePageQuery query);

    /**
     * 查询列表数据
     *
     * @param searchType 查询类型
     * @param query 查询条件
     * @return 结果
     * @param <T> 泛型
     */
    <T> List<T> searchList(SearchType searchType, BasePageQuery query);

    /**
     * 分页查询数据
     *
     * @param searchType 查询类型
     * @param query 查询条件
     * @return 结果
     * @param <T> 泛型
     */
    <T> RemotePage<T> searchPage(SearchType searchType, BasePageQuery query);
}
