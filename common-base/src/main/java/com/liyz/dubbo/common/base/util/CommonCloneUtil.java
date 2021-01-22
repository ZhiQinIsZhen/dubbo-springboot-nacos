package com.liyz.dubbo.common.base.util;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liyz.dubbo.common.base.cglib.PageCopier;
import com.liyz.dubbo.common.base.cglib.PageInfoCopier;
import com.liyz.dubbo.common.base.cglib.SimpleBeanCopier;
import com.liyz.dubbo.common.base.constant.CommonConstant;
import com.liyz.dubbo.common.base.result.PageResult;
import com.liyz.dubbo.common.remote.page.Page;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注释:带缓存clone
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/1/22 16:10
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonCloneUtil {

    /**
     * 用户深拷贝{@link com.liyz.dubbo.common.base.cglib.SimpleBeanCopier}的副本
     */
    private static final SimpleBeanCopier copier = new SimpleBeanCopier();

    /**
     * 缓存容器
     */
    private static final Map<String, SimpleBeanCopier> COPIER_MAP = new ConcurrentHashMap<>(256);

    /**
     * 缓存bean拷贝
     *
     * @param source
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    public static <T,Y> Y objectClone(T source, Class<Y> targetClass) {
        if (source == null) {
            return null;
        }
        SimpleBeanCopier simpleBeanCopier = getCopier(source.getClass(), targetClass);
        return (Y) simpleBeanCopier.copy(source);
    }

    /**
     * list对象拷贝
     *
     * @param sourceList
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    public static <T,Y> List<Y> ListClone(List<T> sourceList, Class<Y> targetClass) {
        if (sourceList == null) {
            return null;
        }
        if (sourceList.size() == 0) {
            return Lists.newArrayList();
        }
        SimpleBeanCopier simpleBeanCopier = getCopier(sourceList.get(0).getClass(), targetClass);
        return Lists.transform(sourceList, simpleBeanCopier);
    }

    /**
     * pageInfo对象拷贝{@link PageInfo}
     *
     * @param sourcePage
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    public static <T,Y> PageInfo<Y> pageClone(PageInfo<T> sourcePage, Class<Y> targetClass) {
        if (sourcePage == null) {
            return null;
        }
        if (sourcePage.getSize() == 0) {
            return objectClone(sourcePage, PageInfo.class);
        }
        SimpleBeanCopier simpleBeanCopier = getCopier(sourcePage.getList().get(0).getClass(), targetClass);
        return PageInfoCopier.transform(sourcePage, simpleBeanCopier);
    }

    /**
     * page{@link org.springframework.data.domain.Page}对象拷贝
     *
     * @param sourcePage
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    public static <T,Y> org.springframework.data.domain.Page<Y> pageClone(org.springframework.data.domain.Page<T> sourcePage, Class<Y> targetClass) {
        if (sourcePage == null) {
            return null;
        }
        if (sourcePage.getTotalElements() == 0 || sourcePage.getContent().size() == 0) {
            return new PageImpl<Y>(new ArrayList<Y>(), sourcePage.getPageable(), sourcePage.getTotalElements());
        }
        SimpleBeanCopier simpleBeanCopier = getCopier(sourcePage.getContent().get(0).getClass(), targetClass);
        return PageCopier.transform(sourcePage, simpleBeanCopier);
    }

    /**
     * page{@link com.liyz.dubbo.common.remote.page.Page}对象拷贝
     *
     * @param sourcePage
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    public static <T,Y> Page<Y> pageClone(Page<T> sourcePage, Class<Y> targetClass) {
        if (sourcePage == null) {
            return null;
        }
        if (sourcePage.getTotal() == 0 || CollectionUtils.isEmpty(sourcePage.getList())) {
            return new Page<Y>(null, sourcePage.getTotal(), sourcePage.getPages(), sourcePage.getPageNum(), sourcePage.getPageSize(), sourcePage.getHasNextPage());
        }
        SimpleBeanCopier simpleBeanCopier = getCopier(sourcePage.getList().get(0).getClass(), targetClass);
        return PageCopier.transformPage(sourcePage, simpleBeanCopier);
    }

    /**
     * pageResult{@link PageResult}对象拷贝
     * @param sourcePage
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    public static <T,Y> PageResult<Y> pageClone(PageResult<T> sourcePage, Class<Y> targetClass) {
        if (sourcePage == null) {
            return null;
        }
        PageResult<Y> pageResult = objectClone(sourcePage, PageResult.class);
        List<T> tList = sourcePage.getData();
        List<Y> yList = null;
        if (!CollectionUtils.isEmpty(tList)) {
            yList = ListClone(tList, targetClass);
        } else {
            yList = Lists.newArrayList();
        }
        pageResult.setData(yList);
        return pageResult;
    }

    /**
     * pageInfo{@link PageInfo}拷贝成page{@link com.liyz.dubbo.common.remote.page.Page}对象
     *
     * @param sourcePage
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    public static <T, Y> Page<Y> pageInfoToPage(PageInfo<T> sourcePage, Class<Y> targetClass) {
        if (sourcePage == null) {
            return null;
        }
        if (sourcePage.getSize() == 0) {
            return objectClone(sourcePage, Page.class);
        }
        SimpleBeanCopier simpleBeanCopier = getCopier(sourcePage.getList().get(0).getClass(), targetClass);
        return PageInfoCopier.transformPage(sourcePage, simpleBeanCopier);
    }

    /**
     * page{@link org.springframework.data.domain.Page}对象拷贝成page{@link com.liyz.dubbo.common.remote.page.Page}对象
     *
     * @param sourcePage
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    public static <T,Y> Page<Y> pageToPage(org.springframework.data.domain.Page<T> sourcePage, Class<Y> targetClass) {
        if (sourcePage == null) {
            return null;
        }
        if (sourcePage.getTotalElements() == 0 || CollectionUtils.isEmpty(sourcePage.getContent())) {
            return new com.liyz.dubbo.common.remote.page.Page<Y>(null, sourcePage.getTotalElements(), sourcePage.getTotalPages(), sourcePage.getNumber(), sourcePage.getSize(), sourcePage.hasNext());
        }
        SimpleBeanCopier simpleBeanCopier = getCopier(sourcePage.getContent().get(0).getClass(), targetClass);
        return PageCopier.transformPage(sourcePage, simpleBeanCopier);
    }

    /**
     * 字节码拷贝{@link SimpleBeanCopier}
     *
     * @return
     */
    private static SimpleBeanCopier getClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oop = new ObjectOutputStream(bos);
            oop.writeObject(copier);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (SimpleBeanCopier) ois.readObject();
        } catch (Exception e) {
            return new SimpleBeanCopier();
        }
    }

    /**
     * 缓存池获取{@link com.liyz.dubbo.common.base.cglib.SimpleBeanCopier}
     *
     * @param sourceClass
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    private static <T,Y> SimpleBeanCopier getCopier(final Class<Y> sourceClass, final Class<T> targetClass) {
        String key = Joiner.on(CommonConstant.DEFAULT_JOINER).join(sourceClass.getName(), targetClass.getName());
        SimpleBeanCopier copier = COPIER_MAP.get(key);
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
     * 实体类转化为map对象
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null){
            return null;
        }

        Map<String, Object> map = Maps.newHashMap();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    /**
     * map对象转化为实体类
     *
     * @param map
     * @param targetClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T MapToBean(Map<String, Object> map, Class<T> targetClass) throws Exception {
        if (map == null) {
            return null;
        }
        Field[] declaredFields = targetClass.getDeclaredFields();
        T target = BeanUtils.instantiateClass(targetClass);
        String fieldName;
        Method method;
        for (Field field : declaredFields) {
            fieldName = field.getName();
            if (map.containsKey(fieldName) && map.get(fieldName) != null) {
                fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                method = targetClass.getMethod("set" + fieldName, field.getType());
                method.invoke(target,  map.get(field.getName()));
            }
        }
        return target;
    }
}
