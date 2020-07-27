package com.liyz.dubbo.common.socket.listener;

import com.liyz.dubbo.common.socket.constant.DataTypeService;

import java.util.Set;

/**
 * 注释:消息监听器接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 11:08
 */
public interface ListenerService<T> {

    /**
     * 订阅某个类型消息
     *
     * @param dataTypeService
     * @param clientId
     */
    void addSubscriber(DataTypeService dataTypeService, String clientId);

    /**
     * 修改订阅消息
     *
     * @param dataTypeService
     * @param data
     */
    void updateSubscriber(DataTypeService dataTypeService, T data);

    /**
     * 取消订阅
     *
     * @param dataTypeService
     * @param clientId
     */
    boolean removeSubscriber(DataTypeService dataTypeService, String clientId);

    /**
     * 通过数据类型获取订阅者set
     *
     * @param dataTypeService
     * @return
     */
    Set<String> getSubscribesByDataType(DataTypeService dataTypeService);

    /**
     * 是否需要登录
     *
     * @return
     */
    default boolean needLogin() {
        return false;
    }
}
