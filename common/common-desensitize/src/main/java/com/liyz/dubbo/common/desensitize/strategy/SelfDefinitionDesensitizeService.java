package com.liyz.dubbo.common.desensitize.strategy;

import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 注释:自定义脱敏
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 20:37
 */
@Component
public class SelfDefinitionDesensitizeService extends AbstractDesensitizeService{

    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value)) {
            int length = value.length();
            int beginIndex = annotation.beginIndex();
            int endIndex = annotation.endIndex();
            if (beginIndex >= 0 && beginIndex < endIndex && endIndex <= length) {
                StringBuilder format = new StringBuilder(beginIndex > 0 ? "%s" : "");
                for (int i = endIndex - beginIndex; i > 0; i--) {
                    format.append("*");
                }
                format.append(endIndex == length ? "" : "%s");
                if (beginIndex > 0) {
                    value = String.format(format.toString(), value.substring(0, beginIndex), value.substring(endIndex));
                } else {
                    value = String.format(format.toString(), value.substring(endIndex));
                }
            }
        }
        return value;
    }

    @Override
    protected DesensitizationType getType() {
        return DesensitizationType.SELF_DEFINITION;
    }
}
