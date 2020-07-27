package com.liyz.dubbo.common.socket.listener.abs;

import com.liyz.dubbo.common.socket.constant.DataTypeService;
import com.liyz.dubbo.common.socket.listener.ListenerService;
import com.liyz.dubbo.common.socket.msg.ResponseMsg;
import com.liyz.dubbo.common.socket.session.SocketSession;
import com.liyz.dubbo.common.socket.storage.DataTypeChannelStorage;
import com.liyz.dubbo.common.socket.storage.SessionStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注释:监听器抽象类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 11:22
 */
@Slf4j
public abstract class AbstractListenerService<T> implements ListenerService<T> {

    protected abstract ResponseMsg<T> wrapper(DataTypeService dataTypeService, T data);

    @Override
    public void addSubscriber(DataTypeService dataTypeService, String clientId) {
        DataTypeChannelStorage.get(dataTypeService).remove(clientId);
        if (needLogin()) {
            SocketSession socketSession = SessionStorage.get(clientId);
            if (Objects.isNull(socketSession) || Objects.isNull(socketSession.getUserId())) {
                return;
            }
        }

        DataTypeChannelStorage.add(dataTypeService, (k) -> new ConcurrentHashMap<>(512)).put(clientId, Boolean.TRUE);
    }

    @Override
    public void updateSubscriber(DataTypeService dataTypeService, T data) {
        Map<String, Boolean> typeMap = DataTypeChannelStorage.get(dataTypeService);
        ResponseMsg<T> response = wrapper(dataTypeService, data);
        if (CollectionUtils.isEmpty(typeMap)) {
            return;
        }
        typeMap.keySet().forEach(client -> {
            SocketSession socketSession = SessionStorage.get(client);
            if (socketSession != null) {
                socketSession.send(response);
            } else {
                log.info("client is not exsist, client:{}", client);
                typeMap.remove(client);
            }
        });
    }

    @Override
    public boolean removeSubscriber(DataTypeService dataTypeService, String clientId) {
        return getSubscribesByDataType(dataTypeService).remove(clientId);
    }

    @Override
    public Set<String> getSubscribesByDataType(DataTypeService dataTypeService) {
        return DataTypeChannelStorage.get(dataTypeService).keySet();
    }
}
