package com.liyz.dubbo.common.desensitize.service.impl;

import cn.hutool.dfa.SensitiveUtil;
import com.liyz.dubbo.common.desensitize.annotation.Desensitization;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import com.liyz.dubbo.common.desensitize.service.DesensitizeService;
import org.apache.commons.lang3.StringUtils;

/**
 * Desc:dfa算法
 * 注：使用dfa算法时，一定要先init，否则不生效
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/9/14 10:08
 */
public class DfaDesensitizeServiceImpl implements DesensitizeService {

    @Override
    public String desensitize(String value, Desensitization annotation) {
        if (StringUtils.isNotBlank(value) && SensitiveUtil.isInited()) {
            value = SensitiveUtil.sensitiveFilter(value);
        }
        return value;
    }

    @Override
    public DesensitizationType getType() {
        return DesensitizationType.DFA;
    }
}
