package com.liyz.dubbo.common.desensitize.strategy;

import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 注释:忽略此字符
 * 注：如果此字符为null，则返回null；如果不为null，则返回一个长度为0的空字符
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 20:32
 */
@Component
public class IgnoreDesensitizeService extends AbstractDesensitizeService{

    @Override
    public String desensitize(String value, Desensitization annotation) {
        return Objects.nonNull(value) ? StringUtils.EMPTY : null;
    }

    @Override
    protected DesensitizationType getType() {
        return DesensitizationType.IGNORE;
    }
}
