package com.liyz.dubbo.api.openapi3.cglib;


import com.google.common.base.Function;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/8 16:31
 */
@Slf4j
public class BaseBeanCopier<S, T> implements Function<S, T> {

    private BeanCopier beanCopier;

    @Setter
    private Class<T> targetClass;
    @Setter
    private Class<S> sourceClass;

    @Override
    public T apply(S source) {
        if (Objects.isNull(source)) {
            return null;
        }
        try {
            T target = targetClass.newInstance();
            beanCopier.copy(source, target, null);
            return target;
        } catch (Exception e) {
            log.error("create object fail, class: {}", targetClass.getName(), e);
            throw new IllegalArgumentException();
        }
    }

    public void init() {
        this.beanCopier = BeanCopier.create(sourceClass, targetClass, false);
    }
}
