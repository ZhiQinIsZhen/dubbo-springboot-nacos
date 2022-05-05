package com.liyz.dubbo.common.desensitize.strategy;

import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 注释:邮箱脱敏
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 20:30
 */
@Component
public class EmailDesensitizeService extends AbstractDesensitizeService{

    private static final String Email_Regex = "(\\w+)\\w{%d}@(\\w+)";

    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value)) {
            int index = value.indexOf("@");
            if (index > 0) {
                index = (int) (index * 0.6);
                value = value.replaceAll(String.format(Email_Regex, index), "$1****$2");
            }
        }
        return value;
    }

    @Override
    protected DesensitizationType getType() {
        return DesensitizationType.EMAIL;
    }
}
