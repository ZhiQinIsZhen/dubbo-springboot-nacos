package com.liyz.dubbo.common.desensitize.filter;

import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.strategy.AbstractDesensitizeService;
import org.apache.commons.lang3.StringUtils;

/**
 * 注释:自定义fastjson脱敏过滤器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/21 10:32
 */
public class FastjsonDesensitizationContextValueFilter implements ContextValueFilter {

    @Override
    public Object process(BeanContext context, Object object, String name, Object value) {
        if (context == null || object == null || !(value instanceof String)) {
            return value;
        }
        //获取该字段有无脱敏注解
        Desensitization annotation = context.getAnnation(Desensitization.class);
        if (annotation == null) {
            return value;
        }
        String propertyValue = (String) value;
        if (StringUtils.isEmpty(propertyValue)) {
            return value;
        }
        return AbstractDesensitizeService.getDesensitizeServiceByType(annotation.value()).desensitize(propertyValue, annotation);
    }
}
