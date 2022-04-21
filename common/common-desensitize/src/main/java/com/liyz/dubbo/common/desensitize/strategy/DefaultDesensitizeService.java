package com.liyz.dubbo.common.desensitize.strategy;

import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import org.springframework.stereotype.Component;

/**
 * 注释:默认脱敏服务
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 19:50
 */
@Component
public class DefaultDesensitizeService extends AbstractDesensitizeService {


    @Override
    public String desensitize(String value, Desensitization annotation) {
        return value;
    }

    @Override
    protected DesensitizationType getType() {
        return DesensitizationType.DEFAULT;
    }
}
