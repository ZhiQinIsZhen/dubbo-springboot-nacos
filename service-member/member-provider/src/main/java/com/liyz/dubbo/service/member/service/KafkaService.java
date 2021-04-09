package com.liyz.dubbo.service.member.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * 注释:kafka
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/14 21:40
 */
@Slf4j
@Service
public class KafkaService {

    @Value("${cloud.topic.sms:test_sms}")
    private String topicSms;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void send(final String topic, final String value) {
        log.info("send to topic {} with value {}", topic, value);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicSms, value);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("failure send for topic {} with value {}", topicSms, value, throwable);
            }

            @Override
            public void onSuccess(SendResult<String, String> sendResult) {
            }
        });
    }

    public void sendSms(final String value) {
        send(topicSms, value);
    }
}
