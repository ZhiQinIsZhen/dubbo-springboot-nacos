package com.liyz.dubbo.common.limit.service.impl;

import com.liyz.dubbo.common.limit.enums.LimitType;
import com.liyz.dubbo.common.limit.service.AbstractLimitService;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/8 19:36
 */
@Slf4j
public class CustomizationLimitServiceImpl extends AbstractLimitService {

    @Override
    protected LimitType getLimitType() {
        return LimitType.CUSTOMIZATION;
    }
}
