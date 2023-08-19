package com.liyz.dubbo.service.search.service.abs;

import com.google.common.collect.Maps;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.service.search.bo.SearchBO;
import com.liyz.dubbo.service.search.bo.SearchPageBO;
import com.liyz.dubbo.service.search.constant.SearchType;
import com.liyz.dubbo.service.search.exception.RemoteSearchServiceException;
import com.liyz.dubbo.service.search.exception.SearchExceptionCodeEnum;
import com.liyz.dubbo.service.search.service.SearchService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 15:24
 */
public abstract class AbstractSearchService<T> implements SearchService<T>, ApplicationListener<ContextRefreshedEvent> {

    private static final Map<SearchType, SearchService<Object>> SEARCH_TYPE_MAP = Maps.newEnumMap(SearchType.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        SEARCH_TYPE_MAP.put(this.getSearchType(), event.getApplicationContext().getBean(this.getClass()));
    }

    public static SearchService<Object> getSearchService(SearchType searchType) {
        return getSearchService(searchType, false);
    }

    public static SearchService<Object> getSearchService(SearchType searchType, boolean noServiceException) {
        SearchService<Object> service = SEARCH_TYPE_MAP.get(searchType);
        if (noServiceException && Objects.isNull(service)) {
            throw new RemoteSearchServiceException(CommonExceptionCodeEnum.PARAMS_VALIDATED);
        }
        return service;
    }

    /**
     * 查询单条数据
     *
     * @param searchBO 查询条件
     * @return 结果
     */
    @Override
    public T search(SearchBO searchBO) {
        throw new RemoteSearchServiceException(SearchExceptionCodeEnum.NOT_SUPPORT_METHOD);
    }

    /**
     * 查询列表数据
     *
     * @param searchBO 查询条件
     * @return 结果
     */
    @Override
    public List<T> searchList(SearchBO searchBO) {
        throw new RemoteSearchServiceException(SearchExceptionCodeEnum.NOT_SUPPORT_METHOD);
    }

    /**
     * 分页查询数据
     *
     * @param searchPageBO 查询条件
     * @return 结果
     */
    @Override
    public RemotePage<T> searchPage(SearchPageBO searchPageBO) {
        throw new RemoteSearchServiceException(SearchExceptionCodeEnum.NOT_SUPPORT_METHOD);
    }

    /**
     * 获取搜索类型
     *
     * @return 搜索类型
     */
    protected abstract SearchType getSearchType();
}
