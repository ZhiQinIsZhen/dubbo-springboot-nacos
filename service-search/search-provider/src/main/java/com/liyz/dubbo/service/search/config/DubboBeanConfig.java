package com.liyz.dubbo.service.search.config;

import org.apache.dubbo.common.serialize.kryo.KryoSerialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/17 11:21
 */
@Configuration
public class DubboBeanConfig {

    @Bean(name = "kryo")
    public KryoSerialization kryoSerialization() {
        return new KryoSerialization();
    }
}
