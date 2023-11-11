package com.liyz.dubbo.service.search.service;

import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.service.search.query.BasePageQuery;

import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 15:22
 */
public interface SearchService<BO, BaseQuery extends BasePageQuery> {

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 结果
     */
    BO getById(String id);

    /**
     * 根据主键列表查询
     *
     * @param ids 主键列表
     * @return 结果
     */
    List<BO> getByIds(List<String> ids);

    /**
     * 查询单条数据
     *
     * @param baseQuery 查询条件
     * @return 结果
     */
    BO search(BaseQuery baseQuery);

    /**
     * 查询列表数据
     *
     * @param baseQuery 查询条件
     * @return 结果
     */
    List<BO> searchList(BaseQuery baseQuery);

    /**
     * 分页查询数据
     *
     * @param baseQuery 查询条件
     * @return 结果
     */
    RemotePage<BO> searchPage(BaseQuery baseQuery);
}
