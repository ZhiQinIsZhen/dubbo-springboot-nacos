package com.liyz.dubbo.common.desensitize.util;

import com.google.common.collect.Maps;
import com.liyz.dubbo.common.desensitize.enums.DesensitizationType;
import com.liyz.dubbo.common.desensitize.service.DesensitizeService;
import com.liyz.dubbo.common.desensitize.service.impl.*;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Objects;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/2 15:07
 */
@UtilityClass
public class DesensitizeUtil {

    private static final Map<DesensitizationType, DesensitizeService> SERVICE_MAP = Maps.newEnumMap(DesensitizationType.class);

    static {
        putService(new DefaultDesensitizeServiceImpl());
        putService(new IgnoreDesensitizeServiceImpl());
        putService(new CardNoDesensitizeServiceImpl());
        putService(new EmailDesensitizeServiceImpl());
        putService(new EncryptDecryptDesensitizeServiceImpl());
        putService(new MobileDesensitizeServiceImpl());
        putService(new RealNameDesensitizeServiceImpl());
        putService(new SelfDefinitionDesensitizeServiceImpl());
        putService(new DfaDesensitizeServiceImpl());
    }

    /**
     * 根据脱敏类型获取对应的脱敏服务
     *
     * @param desensitizationType 脱敏类型
     * @return 对应服务实例 注：没有对应的服务实列会返回默认的服务实例
     */
    public static DesensitizeService getService(DesensitizationType desensitizationType) {
        desensitizationType = Objects.isNull(desensitizationType) ? DesensitizationType.DEFAULT : desensitizationType;
        DesensitizeService service = SERVICE_MAP.get(desensitizationType);
        return Objects.nonNull(service) ? service : SERVICE_MAP.get(DesensitizationType.DEFAULT);
    }

    /**
     * 初始化脱敏类型
     *
     * @param service 脱敏实例
     */
    private static void putService(DesensitizeService service) {
        if (Objects.nonNull(service)) {
            SERVICE_MAP.put(service.getType(), service);
        }
    }
}
