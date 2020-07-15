package com.liyz.dubbo.service.message.core.service;

import com.liyz.dubbo.service.message.bo.MessageBO;
import com.liyz.dubbo.service.message.constant.MessageEnum;

/**
 * 注释:消息发送接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 9:33
 */
public interface MessageService {

    /**
     * 发送消息
     *
     * @param messageBO
     * @return
     */
    boolean send(MessageBO messageBO);

    /**
     * 获取消息类型
     *
     * @return
     */
    MessageEnum.MessageType getMessageType();
}
