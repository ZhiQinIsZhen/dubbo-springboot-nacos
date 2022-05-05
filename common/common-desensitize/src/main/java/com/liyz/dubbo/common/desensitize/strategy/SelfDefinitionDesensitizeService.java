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
                String begin = StringUtils.left(value, beginIndex);
                String end = StringUtils.right(value, length - endIndex);
                end = StringUtils.leftPad(end, length - beginIndex, "*");
                value = begin + end;
            } else if (beginIndex == -1 && endIndex == -1) {
                value = StringUtils.leftPad(StringUtils.EMPTY, length, "*");
            } else if (beginIndex == -1 && endIndex >= 0 && endIndex <= length) {
                value = StringUtils.rightPad(StringUtils.left(value, length - endIndex), length, "*");
            } else if (endIndex == -1 && beginIndex >= 0 && beginIndex <= length) {
                value = StringUtils.leftPad(StringUtils.right(value, length - beginIndex), length, "*");
            }
        }
        return value;
    }

    @Override
    protected DesensitizationType getType() {
        return DesensitizationType.SELF_DEFINITION;
    }
}
