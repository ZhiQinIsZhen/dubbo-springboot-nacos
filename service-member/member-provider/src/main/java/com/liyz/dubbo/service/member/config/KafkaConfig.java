package com.liyz.dubbo.service.member.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/14 16:27
 */
//@Configuration
public class KafkaConfig {

    @Autowired
    ConsumerFactory<String, String> consumerFactory;

    @Bean("containerFactory")
    public KafkaListenerContainerFactory containerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setAutoStartup(false);
        factory.setConcurrency(1);
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
