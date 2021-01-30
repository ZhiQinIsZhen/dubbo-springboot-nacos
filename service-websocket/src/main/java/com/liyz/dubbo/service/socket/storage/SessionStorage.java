package com.liyz.dubbo.service.socket.storage;

import com.liyz.dubbo.service.socket.session.SocketSession;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注释:session容器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 13:49
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SessionStorage {

    private static final Map<String, SocketSession> CHANNELS = new ConcurrentHashMap<>(1024);

    public static SocketSession getAndSet(SocketSession session) {
        return CHANNELS.computeIfAbsent(session.getSessionId(), (k) -> session);
    }

    public static void add(SocketSession session) {
        CHANNELS.put(session.getSessionId(), session);
    }

    public static SocketSession get(String clientId) {
        return CHANNELS.get(clientId);
    }

    public static Map<String, SocketSession> getChannels() {
        return CHANNELS;
    }

    public static void remove(String clientId) {
        SocketSession session = get(clientId);
        if (session != null) {
            // 删除的时候也删除在线用户信息
            String userId = session.getUserId();
            CHANNELS.remove(clientId);
        }
    }

    public static int count() {
        return CHANNELS.size();
    }

    /**
     * 出于性能的考虑，该方式不推荐使用
     *
     * @param session
     */
    @Deprecated
    public static void remove(SocketSession session) {
        //查看是否包含
        if (CHANNELS.containsValue(session)) {
            String key = null;
            SocketSession value = null;
            for (Map.Entry<String, SocketSession> entry : CHANNELS.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (value == session) {
                    break;
                }
            }
            CHANNELS.remove(key);
        }
    }
}
