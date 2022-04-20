package com.liyz.dubbo.common.core.cglib;

/**
 * 注释:基本示例类的深拷贝
 * 注：非数组、非分页
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 15:18
 */
public class SimpleBeanCopier<F, T> extends BaseBeanCopier<F, T> {

    public SimpleBeanCopier() {}

    public SimpleBeanCopier(Class<F> sourceClass, Class<T> targetClass) {
        setSourceClass(sourceClass);
        setTargetClass(targetClass);
        init();
    }
}
