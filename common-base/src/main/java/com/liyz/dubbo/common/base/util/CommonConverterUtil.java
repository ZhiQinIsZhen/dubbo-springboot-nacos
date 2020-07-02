package com.liyz.dubbo.common.base.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.liyz.dubbo.common.base.cglib.PageImplCopier;
import com.liyz.dubbo.common.base.cglib.PageInfoCopier;
import com.liyz.dubbo.common.base.cglib.SimpleBeanCopier;
import com.liyz.dubbo.common.base.result.PageResult;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注释: bean copy util
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/28 16:38
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonConverterUtil {

    private static final SimpleBeanCopier copier = new SimpleBeanCopier();

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
     * 通用BeanList转换
     * 注：使用的是BeanUtils，建议使用方法ListTransform，因为他使用的是BeanCopier，性能更好
     *
     * @param sourceList 源List
     * @param targetClass 目标类
     * @return
     */
    @Deprecated
    public static <T,Y> List<Y> ListConverter(List<T> sourceList, Class<Y> targetClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return new ArrayList<>();
        }
        List<Y> resultList = new ArrayList<>(sourceList.size());
        for (Object o : sourceList) {
            Y targetObject = BeanUtils.instantiateClass(targetClass);
            BeanUtils.copyProperties(o,targetObject);
            resultList.add(targetObject);
        }

        if (sourceList instanceof Page) {
            Page<T> sourcePage = (Page<T>) sourceList;
            Page<Y> page = new Page<>();
            BeanUtils.copyProperties(sourcePage, page);
            page.clear();
            page.addAll(resultList);
            return page;
        }
        return resultList;
    }

    public static <T,Y> List<Y> ListTransform(List<T> sourceList, Class<Y> targetClass) {
        if (sourceList == null) {
            return null;
        }
        if (sourceList.size() == 0) {
            return Lists.newArrayList();
        }
        SimpleBeanCopier simpleBeanCopier = getClone();
        simpleBeanCopier.setSourceClass(sourceList.get(0).getClass());
        simpleBeanCopier.setTargetClass(targetClass);
        simpleBeanCopier.init();
        return Lists.transform(sourceList, simpleBeanCopier);
    }

    /**
     * 建议使用PageTransform
     *
     * @param sourcePage
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    @Deprecated
    public static <T,Y> PageInfo<Y> PageConverter(PageInfo<T> sourcePage, Class<Y> targetClass) {
        if (sourcePage == null) {
            return null;
        }
        PageInfo<Y> targetPage = beanConverter(sourcePage, PageInfo.class);
        targetPage.setList(ListConverter(sourcePage.getList(), targetClass));
        return targetPage;
    }

    public static <T,Y> PageInfo<Y> PageTransform(PageInfo<T> sourcePage, Class<Y> targetClass) {
        if (sourcePage == null) {
            return null;
        }
        if (sourcePage.getSize() == 0) {
            return beanCopy(sourcePage, PageInfo.class);
        }
        SimpleBeanCopier simpleBeanCopier = getClone();
        simpleBeanCopier.setSourceClass(sourcePage.getList().get(0).getClass());
        simpleBeanCopier.setTargetClass(targetClass);
        simpleBeanCopier.init();
        return PageInfoCopier.transform(sourcePage, simpleBeanCopier);
    }

    public static <T,Y> PageImpl<Y> PageTransform(PageImpl<T> sourcePage, Class<Y> targetClass) {
        if (sourcePage == null) {
            return null;
        }
        if (sourcePage.getTotalElements() == 0 || sourcePage.getContent().size() == 0) {
            return new PageImpl<Y>(new ArrayList<Y>(), sourcePage.getPageable(), sourcePage.getTotalElements());
        }
        SimpleBeanCopier simpleBeanCopier = getClone();
        simpleBeanCopier.setSourceClass(sourcePage.getContent().get(0).getClass());
        simpleBeanCopier.setTargetClass(targetClass);
        simpleBeanCopier.init();
        return PageImplCopier.transform(sourcePage, simpleBeanCopier);
    }

    public static <T,Y> PageResult<Y> PageTransform(PageResult<T> sourcePage, Class<Y> targetClass) {
        if (sourcePage == null) {
            return null;
        }
        PageResult<Y> pageResult = beanCopy(sourcePage, PageResult.class);
        List<T> tList = sourcePage.getData();
        List<Y> yList = null;
        if (!CollectionUtils.isEmpty(tList)) {
            yList = ListTransform(tList, targetClass);
        } else {
            yList = Lists.newArrayList();
        }
        pageResult.setData(yList);
        return pageResult;
    }

    /**
     * 通用Bean转换
     * 注：使用的是BeanUtils，建议使用方法beanCopy，因为他使用的是BeanCopier，性能更好
     *
     * @param source
     * @param targetClass
     * @param <T>
     * @param <Y>
     * @return
     */
    @Deprecated
    public static <T,Y> Y beanConverter(T source, Class<Y> targetClass) {
        if (source == null) {
            return null;
        }
        Y target = BeanUtils.instantiateClass(targetClass);
        BeanUtils.copyProperties(source,target);
        return target;
    }

    public static <T,Y> Y beanCopy(T source, Class<Y> targetClass) {
        if (source == null) {
            return null;
        }
        SimpleBeanCopier simpleBeanCopier = getClone();
        simpleBeanCopier.setSourceClass(source.getClass());
        simpleBeanCopier.setTargetClass(targetClass);
        simpleBeanCopier.init();
        return (Y) simpleBeanCopier.copy(source);
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null){
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

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
