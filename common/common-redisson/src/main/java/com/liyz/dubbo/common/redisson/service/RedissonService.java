package com.liyz.dubbo.common.redisson.service;

import lombok.Getter;
import org.redisson.client.codec.Codec;
import org.redisson.client.codec.StringCodec;

/**
 * 注释:redisson service
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/28 14:07
 */
public class RedissonService {

    /**
     * 默认编码方式
     */
    @Getter
    private static final Codec DEFAULT_STRING_CODE = new StringCodec();
    /**
     * 默认过期时间
     */
    @Getter
    private static final Integer DEFAULT_EXPIRE_TIME_DAY = 15;
}
