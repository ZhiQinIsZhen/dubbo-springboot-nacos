package com.liyz.dubbo.common.excel.service;

import com.alibaba.excel.annotation.ExcelProperty;
import com.google.common.collect.Maps;
import com.liyz.dubbo.common.util.DateUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

/**
 * 注释:excel读列的抽象类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/5/27 9:33
 */
@Slf4j
public abstract class AbstractExcelReadRowService implements ExcelReadRowService {

    /**
     * 映射对照表
     */
    private static Map<String, Field> fieldProperties = Maps.newHashMap();

    /**
     * 初始化映射表
     *
     * @param clazz
     */
    protected static void initFieldProperties(Class<? extends AbstractExcelReadRowService> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelProperty.class)) {
                ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
                fieldProperties.put(excelProperty.value()[0], field);
            }
        }
    }

    /**
     * 注入属性
     *
     * @param rowName
     * @param rowValue
     */
    @SneakyThrows
    @Override
    public void invoke(String rowName, String rowValue) {
        if (fieldProperties.containsKey(rowName)) {
            Field field = fieldProperties.get(rowValue);
            field.setAccessible(true);
            if (field.getType().equals(String.class)) {
                field.set(this, rowValue);
            } else if (field.getType().equals(Date.class)) {
                if (StringUtils.isNotBlank(rowValue)) {
                    if (rowValue.contains("-")) {
                        field.set(this, DateUtil.parse(rowValue, DateUtil.PATTERN_DATE));
                    } else {
                        field.set(this, DateUtil.parse(rowValue, DateUtil.PATTERN_DATE_1));
                    }
                }
            } else if (field.getType().equals(Double.class)) {
                field.set(this, Double.valueOf(rowValue));
            }
        }
    }
}
