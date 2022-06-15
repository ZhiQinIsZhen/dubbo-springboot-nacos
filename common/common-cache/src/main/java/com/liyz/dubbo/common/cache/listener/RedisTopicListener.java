package com.liyz.dubbo.common.cache.listener;

import com.liyz.dubbo.common.cache.config.CacheRedisCaffeineProperties;
import com.liyz.dubbo.common.cache.core.RedisCaffeineCacheManager;
import com.liyz.dubbo.common.cache.message.CacheMessage;
import com.liyz.dubbo.common.util.JsonMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;

/**
 * 注释:redis topic listener
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/6/15 10:47
 */
@Slf4j
public class RedisTopicListener implements ApplicationRunner, Ordered {

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisCaffeineCacheManager redisCaffeineCacheManager;
    @Autowired
    private CacheRedisCaffeineProperties cacheRedisCaffeineProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RTopic topic = redissonClient.getTopic(cacheRedisCaffeineProperties.getRedis().getTopic(), JsonJacksonCodec.INSTANCE);
        topic.addListener(CacheMessage.class, new MessageListener<CacheMessage>() {
            @Override
            public void onMessage(CharSequence charSequence, CacheMessage cacheMessage) {
                log.info("redis message : {}", JsonMapperUtil.toJSONString(cacheMessage));
                redisCaffeineCacheManager.clearLocal(cacheMessage.getCacheName(), cacheMessage.getKey());
            }
        });
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
