package com.liyz.dubbo.common.base.desen.service;

import com.liyz.dubbo.common.base.desen.annotation.Desensitization;
import org.apache.commons.lang3.StringUtils;

/**
 * 注释:反序列化脱敏接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/6/2 16:49
 */
public interface DesensitizeService {

    default String desensitize(String value, Desensitization annotation) {
        if (annotation == null || StringUtils.isBlank(value)) {
            return value;
        }
        int length = value.trim().length();
        switch (annotation.value()) {
            case SELF_DEFINITION:
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
                break;
            case MOBILE:
                if (length == 11) {
                    value = value.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                } else {
                    value = String.format("%s****%s",value.substring(0, 3), value.substring(7));
                }
                break;
            case EMAIL:
                value = value.replaceAll("(\\w+)\\w{5}@(\\w+)", "$1****$2");
                break;
            case REAL_NAME:
                if (length > 1) {
                    value = value.replaceAll("(\\W)(\\W+)", "$1*");
                }
                break;
            case CARD_NO:
                if (length == 18) {
                    value = value.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1****$2");
                } else {
                    value = String.format("%s****%s",value.substring(0, 4), value.substring(14));
                }
                break;
            default:
                break;
        }
        return value;
    }
}
