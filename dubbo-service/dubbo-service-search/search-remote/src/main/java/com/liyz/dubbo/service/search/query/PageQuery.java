package com.liyz.dubbo.service.search.query;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/11/10 15:05
 */
@Getter
@Setter
public class PageQuery implements Serializable {
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
