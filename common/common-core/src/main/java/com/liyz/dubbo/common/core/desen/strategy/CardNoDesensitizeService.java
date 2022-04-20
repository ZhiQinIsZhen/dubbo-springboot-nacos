package com.liyz.dubbo.common.core.desen.strategy;

import com.liyz.dubbo.common.core.desen.Desensitization;
import com.liyz.dubbo.common.core.desen.DesensitizationType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 注释:身份证脱敏
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 19:44
 */
@Component
public class CardNoDesensitizeService extends AbstractDesensitizeService{

    private static final String Card_No_Regex = "(\\d{4})\\d{10}(\\w{4})";

    @Override
    protected DesensitizationType getType() {
        return DesensitizationType.CARD_NO;
    }

    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value)) {
            if (value.length() == 18) {
                value = value.replaceAll(Card_No_Regex, "$1****$2");
            } else {
                value = String.format("%s****%s", value.substring(0, 4), value.substring(14));
            }
        }
        return value;
    }
}
