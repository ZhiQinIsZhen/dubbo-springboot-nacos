package com.liyz.dubbo.service.message.util;

import com.liyz.dubbo.common.base.util.SpringContextUtil;
import com.liyz.dubbo.service.message.core.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 注释:工具类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 11:30
 */
@Slf4j
@Service
public class MessageServiceUtil {

    private static Map<Integer, MessageService> holder = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, MessageService> map = SpringContextUtil.getBeansOfType(MessageService.class);
        if (!CollectionUtils.isEmpty(map)) {
            for (MessageService messageService : map.values()) {
                holder.put(messageService.getMessageType().type, messageService);
            }
        }
        log.info("messageService init success ! type size : {}", holder.size());
    }

    public static MessageService getServiceByType(Integer type) {
        return holder.get(type);
    }
}
