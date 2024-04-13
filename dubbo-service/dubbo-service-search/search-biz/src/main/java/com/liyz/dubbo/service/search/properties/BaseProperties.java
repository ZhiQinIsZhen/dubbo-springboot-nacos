package com.liyz.dubbo.service.search.properties;

import com.google.common.collect.Maps;
import com.liyz.dubbo.service.search.constant.SearchType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/11/11 14:25
 */
@Getter
@Setter
public class BaseProperties {

    public final static Map<SearchType, BaseProperties> SEARCH_TYPE_MAP = Maps.newEnumMap(SearchType.class);

    private String index;

    private SearchType searchType;

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
        SEARCH_TYPE_MAP.put(searchType, this);
    }
}
