package com.liyz.dubbo.common.dao.handler;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.liyz.dubbo.common.dao.inject.DeleteReal;
import com.liyz.dubbo.common.dao.inject.DeleteRealById;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2023/2/8 14:38
 */
public class MergeSqlInjector extends DefaultSqlInjector {

    private final List<AbstractSqlInjector> defaultList;

    public MergeSqlInjector(List<AbstractSqlInjector> defaultList) {
        this.defaultList = defaultList;
    }

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> list = new ArrayList<>(64);
        //加载默认的
        list.addAll(super.getMethodList(mapperClass, tableInfo));
        //加载自定义的
        list.add(new DeleteReal());
        list.add(new DeleteRealById());
        if (!CollectionUtils.isEmpty(defaultList)) {
            defaultList.stream().forEach(injector -> list.addAll(injector.getMethodList(mapperClass, tableInfo)));
        }
        return list;
    }
}
