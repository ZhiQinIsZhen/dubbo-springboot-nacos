package com.liyz.dubbo.security.server.util;

import lombok.experimental.UtilityClass;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注释:dubbo GenericService 工具类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/24 13:57
 */
@UtilityClass
public class DubboGenericServiceUtil {

    private static final Object LOCK = new Object();

    private static final Map<String, GenericService> GENERIC_SERVICE_MAP = new ConcurrentHashMap<>(64);

    /**
     * 获取dubbo的GenericService
     * jdk1.8 ConcurrentHashMap.computeIfAbsent有死循环的可能性
     * 所以在此基础上又加了一把单例锁
     * 如果升级到9以上，可以优化为直接使用ConcurrentHashMap.computeIfAbsent方法
     *
     * @param className
     * @param version
     * @param group
     * @return
     */
    public static GenericService getByClassName(String className, String version, String group) {
        if (!GENERIC_SERVICE_MAP.containsKey(className)) {
            synchronized (LOCK) {
                if (!GENERIC_SERVICE_MAP.containsKey(className)) {
                    ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
                    // 弱类型接口名
                    reference.setInterface(className);
                    reference.setVersion(version);
                    // 声明为泛化接口
                    reference.setGeneric(true);
                    reference.setGroup(group);
                    reference.setTimeout(5000);
                    GENERIC_SERVICE_MAP.put(className, reference.get());
                }
            }
        }
        return GENERIC_SERVICE_MAP.get(className);
    }

    public static GenericService getByClassName(Class clazz, String version, String group) {
        return getByClassName(clazz.getName(), version, group);
    }
}
