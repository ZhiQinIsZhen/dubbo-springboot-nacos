package com.liyz.dubbo.common.remote.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:Page query result package class
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/8 15:35
 */
@Getter
@Setter
public class RemotePage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public RemotePage(List<T> list, long total, long pages, long pageNum, long pageSize, boolean hasNextPage) {
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
    private long pages;

    /**
     * 当前页
     */
    private long pageNum;

    /**
     * 每页的数量
     */
    private long pageSize;

    /**
     * 是否有下一页
     */
    private boolean hasNextPage = false;
}
