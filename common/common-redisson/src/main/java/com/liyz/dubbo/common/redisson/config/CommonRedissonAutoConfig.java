package com.liyz.dubbo.common.redisson.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注释:redisson自动配置
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/28 11:14
 */
@Configuration
public class CommonRedissonAutoConfig {

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(@Value("${spring.redis.host}") String host,
                                   @Value("${spring.redis.port}") int port,
                                   @Value("${spring.redis.database}") int database,
                                   @Value("${spring.redis.username}") String username,
                                   @Value("${spring.redis.password}") String password) {
        Config config = new Config();
        String address = "redis://".concat(host).concat(":").concat(String.valueOf(port));
        SingleServerConfig ssc = config.useSingleServer().setDatabase(database).setAddress(address);
        if (StringUtils.isNotBlank(username)) {
            ssc.setUsername(username);
        }
        if (StringUtils.isNotBlank(password)) {
            ssc.setPassword(password);
        }
        return Redisson.create(config);
    }
}
