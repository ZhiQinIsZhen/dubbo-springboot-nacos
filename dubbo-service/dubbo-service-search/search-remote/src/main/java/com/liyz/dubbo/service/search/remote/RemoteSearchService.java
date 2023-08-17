package com.liyz.dubbo.service.search.remote;

import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.service.search.bo.SearchBO;
import com.liyz.dubbo.service.search.bo.SearchPageBO;
import com.liyz.dubbo.service.search.constant.SearchType;

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
     * @param searchBO 查询条件
     * @return 结果
     * @param <T> 泛型
     */
    <T> T search(SearchType searchType, SearchBO searchBO);

    /**
     * 查询列表数据
     *
     * @param searchType 查询类型
     * @param searchBO 查询条件
     * @return 结果
     * @param <T> 泛型
     */
    <T> List<T> searchList(SearchType searchType, SearchBO searchBO);

    /**
     * 分页查询数据
     *
     * @param searchType 查询类型
     * @param searchPageBO 查询条件
     * @return 结果
     * @param <T> 泛型
     */
    <T> RemotePage<T> searchPage(SearchType searchType, SearchPageBO searchPageBO);
}
