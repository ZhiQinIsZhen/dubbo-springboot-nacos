package com.liyz.dubbo.common.remote.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:分页page
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/9/8 16:34
 */
@Getter
@Setter
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //结果集
    private List<T> list;
    //总记录数
    private long    total;
    //总页数
    private int pages;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //是否有下一页
    private Boolean hasNextPage;

    public Page() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public Page(List<T> list, long total, int pages, int pageNum, int pageSize, Boolean hasNextPage) {
        this.list = list;
        this.total = total;
        this.pages = pages;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.hasNextPage = hasNextPage;
    }
}
