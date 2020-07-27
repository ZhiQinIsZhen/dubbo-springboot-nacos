package com.liyz.dubbo.common.socket.storage;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注释:在线用户容器
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 13:43
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OnlineUserStorage {

    private static final Map<String, String> CHANNELS = new ConcurrentHashMap<>(1024);

    public static void add(String userId, String clientId) {
        CHANNELS.put(userId, clientId);
    }

    public static String get(String userId) {
        return CHANNELS.get(userId);
    }

    public static void remove(String userId) {
        CHANNELS.remove(userId);
    }

    public static int count(){
        return CHANNELS.size();
    }

    /**
     * 出于性能的考虑，该方式不推荐使用
     *
     * @param clientId
     */
    @Deprecated
    public static void removeClientId(String clientId) {
        //查看是否包含
        if (CHANNELS.containsValue(clientId)) {
            String key = null;
            String value = null;
            for (Map.Entry<String, String> entry : CHANNELS.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (value.equals(clientId)) {
                    break;
                }
            }
            CHANNELS.remove(key);
        }
    }

    /**
     * 出于性能的考虑，该方式不推荐使用
     *
     * @param clientId
     * @return
     */
    @Deprecated
    public static boolean hasClient(String clientId) {
        return CHANNELS.containsValue(clientId);
    }
}
