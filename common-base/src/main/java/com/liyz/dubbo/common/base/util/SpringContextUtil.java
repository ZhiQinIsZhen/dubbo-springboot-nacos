package com.liyz.dubbo.common.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 注释:获取springContext工具类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/8/28 14:41
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        if(applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) {
        if(applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(requiredType);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotation) throws BeansException {
        if(applicationContext == null) {
            return null;
        }
        return applicationContext.getBeansWithAnnotation(annotation);
    }

    public static String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotation) throws BeansException {
        if(applicationContext == null) {
            return null;
        }
        return applicationContext.getBeanNamesForAnnotation(annotation);
    }

    public static boolean containsBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
