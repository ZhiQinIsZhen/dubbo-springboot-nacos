package com.liyz.dubbo.service.message.consumer;

import com.alibaba.fastjson.JSON;
import com.liyz.dubbo.service.message.bo.MessageBO;
import com.liyz.dubbo.service.message.constant.MessageEnum;
import com.liyz.dubbo.service.message.util.MessageServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 注释:消息消费端
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/14 17:06
 */
@Slf4j
@Service
public class MessageConsumer {

    @KafkaListener(topics = "${cloud.topic.sms}")
    public void smsReceive(ConsumerRecord<String, String> record) {
        String payload = record.value();
        log.info("payload: {}, offset：{}", payload, record.offset());
        try {
            MessageBO messageBO = JSON.parseObject(payload, MessageBO.class);
            if (Objects.isNull(messageBO) || Objects.isNull(messageBO.getType())) {
                return;
            }
            MessageEnum.MessageType messageType = MessageEnum.MessageType.getByCode(messageBO.getType());
            if (messageType == null) {
                return;
            }
            boolean success = MessageServiceUtil.getServiceByType(messageType.getType()).send(messageBO);
            log.info("offset : {}  send message result : {}", record.offset(), success);
        } catch (Exception e) {
            log.error("offset : {} ------>  error when readValue", record.offset(), e);
            return;
        }
    }
}
