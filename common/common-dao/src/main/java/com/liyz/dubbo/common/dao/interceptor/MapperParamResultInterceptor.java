package com.liyz.dubbo.common.dao.interceptor;

import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import com.liyz.dubbo.common.desensitize.strategy.AbstractDesensitizeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 注释:入参出参拦截器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/21 17:27
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args={PreparedStatement.class}),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args={Statement.class})
})
public class MapperParamResultInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //入参
        if (invocation.getTarget() instanceof ParameterHandler) {
            executorParams(invocation);
        }
        Object result = invocation.proceed();
        //出参
        if (invocation.getTarget() instanceof ResultSetHandler) {
            executorResults(result);
        }
        return result;
    }

    /**
     * 处理入参
     *
     * @param invocation
     */
    private void executorParams(Invocation invocation) throws Throwable {
        ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
        Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
        parameterField.setAccessible(true);
        Object parameterObject = parameterField.get(parameterHandler);
        if (Objects.nonNull(parameterObject)) {
            Class<?> cls = parameterObject.getClass();
            Field[] fields = cls.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (int i = 0, length = fields.length; i < length; i++) {
                    Field field = fields[i];
                    if (field.getType() == String.class && field.isAnnotationPresent(Desensitization.class)) {
                        field.setAccessible(true);
                        Desensitization desensitization = field.getAnnotation(Desensitization.class);
                        Object value = field.get(parameterObject);
                        if (Objects.nonNull(value) && value.toString().trim().length() > 0) {
                            field.set(
                                    parameterObject,
                                    AbstractDesensitizeService.getDesensitizeServiceByType(
                                            desensitization.value()
                                    ).desensitize(
                                            value.toString(),
                                            desensitization.value() == DesensitizationType.ENCRYPT_DECRYPT ? null : desensitization
                                    )
                            );
                        }
                    }
                }
            }
        }
    }

    /**
     * 处理出参
     *
     * @param result
     */
    private void executorResults(Object result) throws Throwable {
        if (Objects.isNull(result)){
            return;
        }
        if (result instanceof ArrayList) {
            ArrayList resultList = (ArrayList) result;
            if (!CollectionUtils.isEmpty(resultList)) {
                Set<Field> fields = getDesensitizeFields(resultList.get(0));
                if (CollectionUtils.isEmpty(fields)) {
                    return;
                }
                for (Object obj : resultList) {
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Desensitization desensitization = field.getAnnotation(Desensitization.class);
                        Object value = field.get(obj);
                        if (Objects.nonNull(value) && value.toString().trim().length() > 0) {
                            field.set(
                                    obj,
                                    AbstractDesensitizeService.getDesensitizeServiceByType(desensitization.value()).desensitize(
                                            value.toString(),
                                            desensitization
                                    )
                            );
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取该返回类型需要脱敏的字段
     *
     * @param target
     * @return
     */
    private Set<Field> getDesensitizeFields(Object target) {
        Set<Field> fieldSet = new HashSet<>();
        Class<?> cls = target.getClass();
        Field[] fields = cls.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (int i = 0, length = fields.length; i < length; i++) {
                Field field = fields[i];
                if (field.getType() == String.class && field.isAnnotationPresent(Desensitization.class)) {
                    fieldSet.add(field);
                }
            }
        }
        return fieldSet;
    }
}
