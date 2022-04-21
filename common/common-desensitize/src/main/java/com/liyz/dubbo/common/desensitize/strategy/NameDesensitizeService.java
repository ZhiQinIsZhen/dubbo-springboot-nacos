package com.liyz.dubbo.common.desensitize.strategy;

import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 注释:姓名脱敏
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 20:36
 */
@Component
public class NameDesensitizeService extends AbstractDesensitizeService{

    private static final String Name_Regex = "(\\W)(\\W+)";

    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value) && value.length() > 1) {
            value = value.replaceAll(Name_Regex, "$1*");
        }
        return value;
    }

    @Override
    protected DesensitizationType getType() {
        return DesensitizationType.REAL_NAME;
    }
}
