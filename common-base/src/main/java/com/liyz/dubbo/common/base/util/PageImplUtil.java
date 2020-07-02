package com.liyz.dubbo.common.base.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/1/16 17:07
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageImplUtil {

    public static <T> PageImpl<T> toPageImpl(Page<T> page) {
        return new PageImpl<T>(page.getContent(), page.getPageable(), page.getTotalElements());
    }
}
