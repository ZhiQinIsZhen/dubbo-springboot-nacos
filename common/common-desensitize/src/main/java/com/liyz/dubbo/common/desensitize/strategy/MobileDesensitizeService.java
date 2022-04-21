package com.liyz.dubbo.common.desensitize.strategy;

import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 注释:手机号码脱敏
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 20:34
 */
@Component
public class MobileDesensitizeService extends AbstractDesensitizeService{

    private static final String Mobile_Regex = "(\\d{3})\\d{4}(\\d{4})";

    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value)) {
            if (value.length() == 11) {
                value = value.replaceAll(Mobile_Regex, "$1****$2");
            } else {
                value = String.format("%s****%s", value.substring(0, 3), value.substring(7));
            }
        }
        return value;
    }

    @Override
    protected DesensitizationType getType() {
        return DesensitizationType.MOBILE;
    }
}
