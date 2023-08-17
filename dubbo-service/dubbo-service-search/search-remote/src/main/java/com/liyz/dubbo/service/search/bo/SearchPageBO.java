package com.liyz.dubbo.service.search.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/8/17 15:14
 */
@Getter
@Setter
public class SearchPageBO extends SearchBO {
    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页数据条数
     */
    private Integer pageSize = 20;
}
