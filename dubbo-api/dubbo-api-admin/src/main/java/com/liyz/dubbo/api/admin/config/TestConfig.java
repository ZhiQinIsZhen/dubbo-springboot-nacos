package com.liyz.dubbo.api.admin.config;

import cn.hutool.core.util.ReflectUtil;
import com.liyz.dubbo.security.client.annotation.Anonymous;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/16 10:50
 */
@Slf4j
@Configuration
public class TestConfig implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, RequestMappingHandlerMapping> map = applicationContext.getBeansOfType(RequestMappingHandlerMapping.class);
        if (!CollectionUtils.isEmpty(map)) {
            map.values().forEach(handlerMapping -> {
                handlerMapping.getHandlerMethods().forEach((k, v) -> {
                    boolean hasAnonymous = v.getBeanType().isAnnotationPresent(Anonymous.class) || (v.hasMethodAnnotation(Anonymous.class));
                    if (hasAnonymous) {
                        if (Objects.nonNull(v.getMethodParameters())) {
                            Arrays.stream(v.getMethodParameters()).forEach(item -> {
                                Class<?> paramClass = item.getParameterType();
                                log.info("param type name : {}", paramClass.getTypeName());
                                Arrays.stream(ReflectUtil.getFields(paramClass))
                                        .filter(f -> f.isAnnotationPresent(ApiModelProperty.class))
                                        .forEach(field -> {
                                            ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                                            if (apiModelProperty != null) {
                                                log.info("       field name : {}, type : {}, remark : {}",
                                                        field.getName(), field.getType().getName(), apiModelProperty.value());
                                            }
                                        });
                                Type type = v.getMethod().getGenericReturnType();
                                if (type instanceof Class) {
                                    log.info("return type name : {}", ((Class<?>) type).getName());

                                    Arrays.stream(ReflectUtil.getFields((Class<?>) type))
                                            .filter(f -> f.isAnnotationPresent(ApiModelProperty.class))
                                            .forEach(field -> {
                                                ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                                                if (apiModelProperty != null) {
                                                    log.info("       field name : {}, type : {}, remark : {}",
                                                            field.getName(), field.getType().getName(), apiModelProperty.value());
                                                }
                                            });
                                }
                                if (type instanceof ParameterizedType) {
                                    log.info("return type name : {}", ((ParameterizedType) type).getRawType().getTypeName());
                                    try {
                                        Class clazz = Class.forName(((ParameterizedType) type).getRawType().getTypeName());
                                        if (clazz.getTypeParameters() != null) {
                                            for (int i = 0, j = clazz.getTypeParameters().length; i < j; i++) {
                                                log.info("index : {}, name : {}", i, clazz.getTypeParameters()[i].getName());
                                            }
                                        }
                                    } catch (Exception e) {

                                    }
                                    Class<?> rawTypeClass = (Class<?>)((ParameterizedType) type).getRawType();
                                    Arrays.stream(ReflectUtil.getFields(rawTypeClass))
                                            .filter(f -> f.isAnnotationPresent(ApiModelProperty.class))
                                            .forEach(field -> {
                                                log.info("       field signature : {}", ReflectUtil.getFieldValue(field, "signature"));
                                                ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                                                if (apiModelProperty != null) {
                                                    log.info("       field name : {}, type : {}, remark : {}",
                                                            field.getName(), field.getType().getName(), apiModelProperty.value());
                                                }
                                            });
                                    Type[] types = ((ParameterizedType) type).getActualTypeArguments();
                                    Arrays.stream(types).forEach(itemType -> {
                                        log.info("return type : {}", itemType);
                                        Arrays.stream(ReflectUtil.getFields((Class<?>) itemType))
                                                .filter(f -> f.isAnnotationPresent(ApiModelProperty.class))
                                                .forEach(field -> {
                                                    ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                                                    if (apiModelProperty != null) {
                                                        log.info("       field name : {}, type : {}, remark : {}",
                                                                field.getName(), field.getType().getName(), apiModelProperty.value());
                                                    }
                                                });
                                    });
                                }
                            });
                        }
                    }
                });
            });
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
