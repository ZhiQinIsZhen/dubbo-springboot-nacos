package com.liyz.dubbo.common.base.cglib;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/10/14 17:10
 */
public class PageCopier<F, T> extends BaseBeanCopier<F, T> {

    public static <F, T> Page<T> transform(Page<F> sourcePageInfo, Function<? super F, ? extends T> function) {
        List<T> targetList = Objects.nonNull(sourcePageInfo.getContent()) ?
                Lists.transform(sourcePageInfo.getContent(), function) :
                Lists.newArrayList();
        Page<T> targetPage = new PageImpl<>(targetList, sourcePageInfo.getPageable(), sourcePageInfo.getTotalElements());
        return targetPage;
    }

    public static <F, T> com.liyz.dubbo.common.remote.page.Page<T> transformPage(Page<F> sourcePageInfo, Function<? super F, ? extends T> function) {
        List<T> targetList = Objects.nonNull(sourcePageInfo.getContent()) ?
                Lists.transform(sourcePageInfo.getContent(), function) :
                Lists.newArrayList();
        com.liyz.dubbo.common.remote.page.Page<T> targetPage = new com.liyz.dubbo.common.remote.page.Page<>(targetList, sourcePageInfo.getTotalElements(), sourcePageInfo.getTotalPages(), sourcePageInfo.getNumber(), sourcePageInfo.getSize(), sourcePageInfo.hasNext());
        return targetPage;
    }

    public static <F, T> com.liyz.dubbo.common.remote.page.Page<T> transformPage(com.liyz.dubbo.common.remote.page.Page<F> sourcePageInfo, Function<? super F, ? extends T> function) {
        List<T> targetList = Objects.nonNull(sourcePageInfo.getList()) ?
                Lists.transform(sourcePageInfo.getList(), function) :
                Lists.newArrayList();
        com.liyz.dubbo.common.remote.page.Page<T> targetPage = new com.liyz.dubbo.common.remote.page.Page<>(targetList, sourcePageInfo.getTotal(), sourcePageInfo.getPages(), sourcePageInfo.getPageNum(), sourcePageInfo.getPageSize(), sourcePageInfo.getHasNextPage());
        return targetPage;
    }
}
