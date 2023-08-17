package com.liyz.dubbo.service.search.service;

import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.service.search.bo.SearchBO;
import com.liyz.dubbo.service.search.bo.SearchPageBO;

import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 15:22
 */
public interface SearchService<T> {

    /**
     * 查询单条数据
     *
     * @param searchBO 查询条件
     * @return 结果
     */
    T search(SearchBO searchBO);

    /**
     * 查询列表数据
     *
     * @param searchBO 查询条件
     * @return 结果
     */
    List<T> searchList(SearchBO searchBO);

    /**
     * 分页查询数据
     *
     * @param searchPageBO 查询条件
     * @return 结果
     */
    RemotePage<T> searchPage(SearchPageBO searchPageBO);
}
