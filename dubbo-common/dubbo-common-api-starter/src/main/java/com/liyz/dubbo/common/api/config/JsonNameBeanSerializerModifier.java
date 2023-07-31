package com.liyz.dubbo.common.api.config;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.google.common.collect.Lists;
import com.liyz.dubbo.common.api.annotation.MyJsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/27 16:41
 */
public class JsonNameBeanSerializerModifier extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        List<BeanPropertyWriter> list = Lists.newArrayList();
        for (BeanPropertyWriter writer : beanProperties) {
            MyJsonProperty jsonProperty = writer.getAnnotation(MyJsonProperty.class);
            if (jsonProperty == null) {
                list.add(writer);
                continue;
            }
            String value = jsonProperty.value();
            if (StringUtils.isBlank(value)) {
                list.add(writer);
                continue;
            }
            list.add(writer.rename(new NameTransformer() {
                @Override
                public String transform(String name) {
                    return value;
                }

                @Override
                public String reverse(String transformed) {
                    return transformed;
                }
            }));
        }

        return list;


    }
}
