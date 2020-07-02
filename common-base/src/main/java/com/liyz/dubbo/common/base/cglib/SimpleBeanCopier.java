package com.liyz.dubbo.common.base.cglib;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/10/14 17:10
 */
public class SimpleBeanCopier<F, T> extends BaseBeanCopier<F, T> {

    public SimpleBeanCopier() {}

    public SimpleBeanCopier(Class<F> sourceClass, Class<T> targetClass) {
        setSourceClass(sourceClass);
        setTargetClass(targetClass);
        init();
    }
}
