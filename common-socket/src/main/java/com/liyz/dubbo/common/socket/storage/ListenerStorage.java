package com.liyz.dubbo.common.socket.storage;

import com.liyz.dubbo.common.socket.constant.DataTypeService;
import com.liyz.dubbo.common.socket.listener.abs.AbstractListenerService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 注释:监听事件容器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 11:27
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ListenerStorage<T extends AbstractListenerService> {

    private final Map<String, T> listenerMap = new ConcurrentHashMap<>(64);

    private static final ListenerStorage INSTANCE = new ListenerStorage();

    public static ListenerStorage getInstance() {
        return INSTANCE;
    }

    public void add(DataTypeService dataTypeService, Function<String, T> function) {
        listenerMap.computeIfAbsent(dataTypeService.getDataType(), function);
    }

    public T get(DataTypeService dataTypeService) {
        return listenerMap.get(dataTypeService.getDataType());
    }

    public void remove(DataTypeService dataTypeService) {
        listenerMap.remove(dataTypeService.getDataType());
    }
}
