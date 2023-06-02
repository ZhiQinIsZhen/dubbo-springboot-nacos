package com.liyz.dubbo.common.api.result;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.Lists;
import com.liyz.dubbo.common.remote.exception.CommonExceptionCodeEnum;
import com.liyz.dubbo.common.remote.exception.IExceptionService;
import com.liyz.dubbo.common.remote.page.RemotePage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/9 10:25
 */
@Getter
@Setter
@JsonPropertyOrder({"code", "message", "total", "pages", "pageNum", "pageSize", "hasNextPage", "data"})
public class PageResult<T> {

    private String code;

    private String message;

    private Long total;

    private Long pages;

    private Long pageNum;

    private Long pageSize;

    private Boolean hasNextPage;

    private List<T> data;

    public static <T> PageResult<T> success(RemotePage<T> data) {
        return new PageResult<>(data);
    }

    public static <T> PageResult<T> error(String code, String message) {
        return new PageResult<T>(code, message);
    }

    public static <T> PageResult<T> error(IExceptionService codeEnum) {
        return new PageResult<T>(codeEnum.getCode(), codeEnum.getMessage());
    }

    public PageResult() {}

    public PageResult(RemotePage<T> data) {
        boolean isNull = data == null;
        this.setData(isNull ? Lists.newArrayList() : data.getList());
        this.total = isNull ? 0L : data.getTotal();
        this.pages = isNull ? 0 : data.getPages();
        this.hasNextPage = isNull ? false : data.isHasNextPage();
        this.pageNum = isNull ? 0 : data.getPageNum();
        this.pageSize = isNull ? 0 : data.getPageSize();
        this.code = CommonExceptionCodeEnum.SUCCESS.getCode();
        this.message = CommonExceptionCodeEnum.SUCCESS.getMessage();
    }

    public PageResult(String code, String message) {
        this.code = code;
        this.message = message;
        this.total = 0L;
        this.pages = 0L;
        this.pageNum = 0L;
        this.pageSize = 0L;
        this.hasNextPage = false;
    }
}
