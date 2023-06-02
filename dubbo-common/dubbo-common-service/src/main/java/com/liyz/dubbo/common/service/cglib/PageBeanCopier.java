package com.liyz.dubbo.common.service.cglib;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.liyz.dubbo.common.remote.page.RemotePage;

import java.util.List;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/8 16:44
 */
public class PageBeanCopier<S, T> extends BaseBeanCopier<S, T> {

    /**
     * 自定义page to page
     *
     * @param sourceRemotePageInfo
     * @param function
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> RemotePage<T> pageToPage(RemotePage<S> sourceRemotePageInfo, Function<? super S, ? extends T> function) {
        if (sourceRemotePageInfo == null) {
            return null;
        }
        List<T> targetList = Objects.nonNull(sourceRemotePageInfo.getList()) ?
                Lists.transform(sourceRemotePageInfo.getList(), function) :
                Lists.newArrayList();
        return new RemotePage<>(
                targetList,
                sourceRemotePageInfo.getTotal(),
                sourceRemotePageInfo.getPages(),
                sourceRemotePageInfo.getPageNum(),
                sourceRemotePageInfo.getPageSize(),
                sourceRemotePageInfo.isHasNextPage()
        );
    }
}
