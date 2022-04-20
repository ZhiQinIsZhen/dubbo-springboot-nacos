package com.liyz.dubbo.common.remote.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:分页查询返回结果 page
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 14:19
 */
@Getter
@Setter
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public Page() {}

    public Page(List<T> list, long total, int pages, int pageNum, int pageSize, boolean hasNextPage) {
        this.list = list;
        this.total = total;
        this.pages = pages;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.hasNextPage = hasNextPage;
    }

    /**
     * 结果集
     */
    private List<T> list;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 当前页
     */
    private int pageNum;

    /**
     * 每页的数量
     */
    private int pageSize;

    /**
     * 是否有下一页
     */
    private boolean hasNextPage = false;
}
