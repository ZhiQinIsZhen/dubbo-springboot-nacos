package com.liyz.dubbo.common.socket.storage;

import com.liyz.dubbo.common.socket.constant.DataTypeService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 注释:数据类型容器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 13:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataTypeChannelStorage {

    private final static Map<String, Map<String, Boolean>> dataTypeMap = new ConcurrentHashMap<>(64);

    public static Map<String, Boolean> add(DataTypeService dataTypeService, Function<String, Map<String, Boolean>> function) {
        return dataTypeMap.computeIfAbsent(dataTypeService.getDataType(), function);
    }

    public static Map<String, Boolean> get(DataTypeService dataTypeService) {
        return dataTypeMap.get(dataTypeService.getDataType());
    }

    public static void remove(DataTypeService dataTypeService) {
        dataTypeMap.remove(dataTypeService.getDataType());
    }

    public static Set<String> dataTypes() {
        return dataTypeMap.keySet();
    }
}
