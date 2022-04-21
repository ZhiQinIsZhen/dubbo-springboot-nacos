package com.liyz.dubbo.common.core.cglib;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.liyz.dubbo.common.remote.page.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Objects;

/**
 * 注释:分页结果的深拷贝
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 15:19
 */
public class PageBeanCopier<F, T> extends BaseBeanCopier<F, T> {

    /**
     * 自定义page to page
     *
     * @param sourcePageInfo
     * @param function
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> Page<T> pageToPage(Page<F> sourcePageInfo, Function<? super F, ? extends T> function) {
        List<T> targetList = Objects.nonNull(sourcePageInfo.getList()) ?
                Lists.transform(sourcePageInfo.getList(), function) :
                Lists.newArrayList();
        return new Page<T>(
                targetList,
                sourcePageInfo.getTotal(),
                sourcePageInfo.getPages(),
                sourcePageInfo.getPageNum(),
                sourcePageInfo.getPageSize(),
                sourcePageInfo.isHasNextPage()
        );
    }

    /**
     * domainPage to page
     *
     * @param sourcePageInfo
     * @param function
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> Page<T> domainPageToPage(org.springframework.data.domain.Page<F> sourcePageInfo,
                                                  Function<? super F, ? extends T> function) {
        List<T> targetList = Objects.nonNull(sourcePageInfo.getContent()) ?
                Lists.transform(sourcePageInfo.getContent(), function) :
                Lists.newArrayList();
        return new Page<T>(
                targetList,
                sourcePageInfo.getTotalElements(),
                sourcePageInfo.getTotalPages(),
                sourcePageInfo.getNumber(),
                sourcePageInfo.getSize(),
                sourcePageInfo.hasNext()
        );
    }

    /**
     * domainPage to domainPage
     *
     * @param sourcePageInfo
     * @param function
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> org.springframework.data.domain.Page<T> domainPageToDomainPage(
            org.springframework.data.domain.Page<F> sourcePageInfo,
            Function<? super F, ? extends T> function) {
        List<T> targetList = Objects.nonNull(sourcePageInfo.getContent()) ?
                Lists.transform(sourcePageInfo.getContent(), function) :
                Lists.newArrayList();
        return new PageImpl<>(targetList, sourcePageInfo.getPageable(), sourcePageInfo.getTotalElements());
    }

    /**
     * domainPageImpl to domainPageImpl
     *
     * @param sourcePageInfo
     * @param function
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> PageImpl<T> domainPageImplToDomainPageImpl(PageImpl<F> sourcePageInfo, Function<? super F, ? extends T> function) {
        List<T> targetList = Objects.nonNull(sourcePageInfo.getContent()) ?
                Lists.transform(sourcePageInfo.getContent(), function) :
                Lists.newArrayList();
        return new PageImpl<>(targetList, sourcePageInfo.getPageable(), sourcePageInfo.getTotalElements());
    }
}
