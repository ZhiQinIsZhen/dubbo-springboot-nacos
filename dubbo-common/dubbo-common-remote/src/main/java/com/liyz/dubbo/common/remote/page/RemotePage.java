package com.liyz.dubbo.common.remote.page;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:Page query result package class
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/8 15:35
 */
public class RemotePage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public RemotePage(List<T> list, long total, long pageNum, long pageSize) {
        this.list = list;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
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
     * 当前页
     */
    private long pageNum;

    /**
     * 每页的数量
     */
    private long pageSize;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        return this.total % this.pageSize == 0 ? this.total / this.pageSize : this.total / this.pageSize + 1;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = Math.max(1L, pageNum);
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = Math.max(1L, pageSize);
    }

    public boolean isHasNextPage() {
        return getPages() > pageNum;
    }
}
