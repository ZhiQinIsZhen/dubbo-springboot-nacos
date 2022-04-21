package com.liyz.dubbo.common.desensitize.strategy;

import com.google.common.collect.Maps;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import com.liyz.dubbo.common.desensitize.service.IDesensitizeService;

import java.util.Map;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/20 19:32
 */
public abstract class AbstractDesensitizeService implements IDesensitizeService {

    private static Map<DesensitizationType, IDesensitizeService> strategyMap = Maps.newHashMap();

    public AbstractDesensitizeService() {
        strategyMap.put(getType(), this);
    }

    /**
     * 根据脱敏类型获取对应的脱敏服务
     *
     * @param desensitizationType
     * @return
     */
    public static IDesensitizeService getDesensitizeServiceByType(DesensitizationType desensitizationType) {
        IDesensitizeService service = strategyMap.get(desensitizationType);
        return Objects.nonNull(service) ? service : strategyMap.get(DesensitizationType.DEFAULT);
    }

    /**
     * 获取对应脱敏类型
     *
     * @return
     */
    protected abstract DesensitizationType getType();
}
