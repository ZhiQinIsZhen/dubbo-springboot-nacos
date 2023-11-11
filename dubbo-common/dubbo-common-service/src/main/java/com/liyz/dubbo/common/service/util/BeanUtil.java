package com.liyz.dubbo.common.service.util;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.liyz.dubbo.common.remote.page.RemotePage;
import com.liyz.dubbo.common.service.cglib.SimpleBeanCopier;
import com.liyz.dubbo.common.service.constant.CommonServiceConstant;
import lombok.experimental.UtilityClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/8 16:50
 */
@UtilityClass
public class BeanUtil {

    /**
     * 深拷贝{@link SimpleBeanCopier}的副本
     */
    private static final SimpleBeanCopier copier = new SimpleBeanCopier<>();

    /**
     * 缓存容器
     */
    private static final Map<String, SimpleBeanCopier> COPIER_MAP = new ConcurrentHashMap<>(256);

    /**
     * 单个对象拷贝
     *
     * @param source 原对象
     * @param targetClass 目标对象class
     * @return 目标对象
     * @param <S> 原对象泛型
     * @param <T> 目标对象泛型
     */
    public static <S, T> T copyProperties(S source,  Class<T> targetClass) {
        return copyProperties(source, targetClass, null);
    }

    /**
     * 单个对象拷贝
     *
     * @param source 原对象
     * @param instanceTarget 目标对象函数接口
     * @return 目标对象
     * @param <S> 原对象泛型
     * @param <T> 目标对象泛型
     */
    public static <S, T> T copyProperties(S source,  Supplier<T> instanceTarget) {
        return (T) copyProperties(source, instanceTarget.get().getClass(), null);
    }

    /**
     * 单个对象拷贝
     *
     * @param source 原对象
     * @param targetClass 目标对象class
     * @param ext 目标对象函数接口
     * @return 目标对象
     * @param <S> 原对象泛型
     * @param <T> 目标对象泛型
     */
    public static <S, T> T copyProperties(S source,  Class<T> targetClass, BiConsumer<S, T> ext) {
        if (source == null) {
            return null;
        }
        SimpleBeanCopier<S, T> simpleBeanCopier = (SimpleBeanCopier<S, T>) getSimpleBeanCopier(source.getClass(), targetClass);
        T target = simpleBeanCopier.apply(source);
        if (Objects.nonNull(ext)) {
            ext.accept(source, target);
        }
        return target;
    }

    /**
     * 数组对象拷贝
     *
     * @param sources 原数组
     * @param targetClass 目标对象class
     * @return 目标数组
     * @param <S> 原对象泛型
     * @param <T> 目标对象泛型
     */
    public static <S, T> List<T> copyProperties(List<S> sources, Class<T> targetClass) {
        return copyProperties(sources, targetClass, (BiConsumer<S, T>) null);
    }

    /**
     * 数组对象拷贝
     *
     * @param sources 原数组
     * @param targetClass 目标对象class
     * @param ext 目标对象函数接口
     * @return 目标数组
     * @param <S> 原对象泛型
     * @param <T> 目标对象泛型
     */
    public static <S, T> List<T> copyProperties(List<S> sources, Class<T> targetClass, BiConsumer<S, T> ext) {
        if (sources == null) {
            return null;
        }
        if (sources.size() == 0) {
            return Lists.newArrayList();
        }
        return sources.stream().map(source -> copyProperties(source, targetClass, ext)).collect(Collectors.toList());
    }

    /**
     * 分页对象拷贝
     *
     * @param pageSource 原对象
     * @param targetClass 目标对象class
     * @return 目标对象
     * @param <S> 原对象泛型
     * @param <T> 目标对象泛型
     */
    public static <S, T> RemotePage<T> copyProperties(RemotePage<S> pageSource, Class<T> targetClass) {
        return copyProperties(pageSource, targetClass, (BiConsumer<S, T>) null);
    }

    /**
     * 分页对象拷贝
     *
     * @param pageSource 原对象
     * @param targetClass 目标对象class
     * @param ext 目标对象函数接口
     * @return 目标对象
     * @param <S> 原对象泛型
     * @param <T> 目标对象泛型
     */
    public static <S, T> RemotePage<T> copyProperties(RemotePage<S> pageSource, Class<T> targetClass, BiConsumer<S, T> ext) {
        if (pageSource == null) {
            return null;
        }
        return new RemotePage<>(
                copyProperties(pageSource.getList(), targetClass, ext),
                pageSource.getTotal(),
                pageSource.getPageNum(),
                pageSource.getPageSize()
        );
    }

    /**
     * 分页对象拷贝
     *
     * @param pageSource 原对象
     * @param targetClass 目标对象class
     * @return 目标对象
     * @param <S> 原对象泛型
     * @param <T> 目标对象泛型
     */
    public static <S, T> RemotePage<T> copyProperties(Page<S> pageSource, Class<T> targetClass) {
        return copyProperties(pageSource, targetClass, (BiConsumer<S, T>) null);
    }

    /**
     * 分页对象拷贝
     *
     * @param pageSource 原对象
     * @param targetClass 目标对象class
     * @param ext 目标对象函数接口
     * @return 目标对象
     * @param <S> 原对象泛型
     * @param <T> 目标对象泛型
     */
    public static <S, T> RemotePage<T> copyProperties(Page<S> pageSource, Class<T> targetClass, BiConsumer<S, T> ext) {
        if (pageSource == null) {
            return null;
        }
        return new RemotePage<>(
                copyProperties(pageSource.getRecords(), targetClass, ext),
                pageSource.getTotal(),
                pageSource.getCurrent(),
                pageSource.getSize()
        );
    }

    /**
     * MAP转化为对应的实体类
     *
     * @param map map实例
     * @param targetClass 目标对象class
     * @return 目标对象
     * @param <T> 目标对象泛型
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> targetClass) {
        if (Objects.isNull(map)) {
            return null;
        }
        T result = ReflectUtil.newInstance(targetClass);
        Map<String, Field> fieldMap = ReflectUtil.getFieldMap(targetClass);
        fieldMap.forEach((k, v) -> {
            Object value;
            if (map.containsKey(k) && Objects.nonNull(value = map.get(k))) {
                ReflectUtil.setFieldValue(result, v, value);
            }
        });
        return result;
    }

    /**
     * 获取缓存的基础拷贝
     *
     * @param sourceClass 原对象class
     * @param targetClass 目标对象class
     * @return BeanCopier实例
     * @param <S> 原对象泛型
     * @param <T> 目标对象泛型
     */
    private static <S, T> SimpleBeanCopier<S, T> getSimpleBeanCopier(final Class<S> sourceClass, final Class<T> targetClass) {
        String key = Joiner.on(CommonServiceConstant.DEFAULT_JOINER).join(sourceClass.getName(), targetClass.getName());
        SimpleBeanCopier<S, T> copier = COPIER_MAP.get(key);
        if (Objects.nonNull(copier)) {
            return copier;
        }
        copier = getClone();
        copier.setSourceClass(sourceClass);
        copier.setTargetClass(targetClass);
        copier.init();
        COPIER_MAP.putIfAbsent(key, copier);
        return copier;
    }

    /**
     * 字节码拷贝{@link SimpleBeanCopier}
     *
     * @return BeanCopier实例
     * @param <S> 原对象泛型
     * @param <T> 目标对象泛型
     */
    private static <S, T> SimpleBeanCopier<S, T> getClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oop = new ObjectOutputStream(bos);
            oop.writeObject(copier);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (SimpleBeanCopier<S, T>) ois.readObject();
        } catch (Exception e) {
            return new SimpleBeanCopier<>();
        }
    }
}
