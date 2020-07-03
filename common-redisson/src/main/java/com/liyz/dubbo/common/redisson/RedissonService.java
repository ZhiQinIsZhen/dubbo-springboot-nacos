package com.liyz.dubbo.common.redisson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.liyz.dubbo.common.redisson.constant.RedissonConstant;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RList;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 注释:redisson 服务
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/7/22 10:47
 */
@Slf4j
@ConditionalOnClass({RedissonClient.class})
@Service
public class RedissonService {

    @Autowired
    RedissonClient redissonClient;

    private static final Codec STRING_CODE = new StringCodec();
    private static final JsonMapper MAPPER = new JsonMapper();

    /**
     * 获取一个key
     *
     * @param key
     * @return 字符串
     */
    public String getValue(String key) {
        Object result = redissonClient.getBucket(key, STRING_CODE).get();
        return result == null ? null : result.toString();
    }

    /**
     * 获取一个key，并转化为一个实体类
     *
     * @param key
     * @param targetClass
     * @param <T>
     * @return
     */
    public <T> T getValue(String key, Class<T> targetClass) {
        Object result = redissonClient.getBucket(key, STRING_CODE).get();
        try {
            return result == null ? null : MAPPER.readValue(result.toString(), targetClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存一个key
     *
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setValue(String key, T value) {
        redissonClient.getBucket(key, STRING_CODE).set(value);
    }

    /**
     * 保存一个key，并设置有效期
     *
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setValueExpire(String key, T value) {
        setValueExpire(key, value, RedissonConstant.DEFAULT_EXPIRE_TIME_DAY, TimeUnit.DAYS);
    }

    /**
     * 保存一个key，并设置有效期
     *
     * @param key
     * @param value
     * @param time
     * @param unit
     * @param <T>
     */
    public <T> void setValueExpire(String key, T value, long time, TimeUnit unit) {
        redissonClient.getBucket(key, STRING_CODE).set(value, time, unit);
    }

    /**
     * 判断是否存在一个key
     *
     * @param key
     * @return
     */
    public boolean isExists(String key) {
        return redissonClient.getBucket(key, STRING_CODE).isExists();
    }

    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public boolean delete(String key) {
        return redissonClient.getBucket(key, STRING_CODE).delete();
    }

    /**
     * 获取一个List的key
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RList<T> getList(String key) {
        return redissonClient.getList(key, STRING_CODE);
    }

    /**
     * 保存一个value到list的key中
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean setListValue(String key, T value) {
        return redissonClient.getList(key, STRING_CODE).add(value);
    }

    /**
     * 将一个list存入一个key中
     *
     * @param key
     * @param list
     * @param <T>
     * @return
     */
    public <T> boolean setSetList(String key, List<T> list) {
        return redissonClient.getSet(key, STRING_CODE).addAll(list);
    }

    /**
     * 从list中移除一个value
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean removeListValue(String key, T value) {
        return redissonClient.getList(key, STRING_CODE).remove(value);
    }

    /**
     * 判断list中有无此value
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean isExistsForList(String key, T value) {
        return redissonClient.getList(key, STRING_CODE).contains(value);
    }

    /**
     * 获取一个set
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> RSet<T> getSet(String key) {
        return redissonClient.getSet(key, STRING_CODE);
    }

    /**
     * 保存一个value到set中
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean setSetValue(String key, T value) {
        return redissonClient.getSet(key, STRING_CODE).add(value);
    }

    /**
     * 从set移除此value
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean removeSetValue(String key, T value) {
        return redissonClient.getSet(key, STRING_CODE).remove(value);
    }

    /**
     * 查看set中有无此value
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean isExistsForSet(String key, T value) {
        return redissonClient.getSet(key, STRING_CODE).contains(value);
    }

    /**
     * 比较并设置一个value到目标key中
     *
     * @param key
     * @param target
     * @param source
     * @param <T>
     * @return
     */
    public <T> boolean compareAndSet(String key, T target, T source) {
        return redissonClient.getBucket(key, STRING_CODE).compareAndSet(target, source);
    }

    /**
     * 对一个key设置一个默认有效期
     *
     * @param key
     * @return
     */
    public boolean expire(String key) {
        return expire(key, RedissonConstant.DEFAULT_EXPIRE_TIME_DAY, TimeUnit.DAYS);
    }

    /**
     * 对一个key设置一个有效期
     *
     * @param key
     * @param time
     * @param unit
     * @return
     */
    public boolean expire(String key, long time, TimeUnit unit) {
        return redissonClient.getBucket(key, STRING_CODE).expire(time, unit);
    }

    /**
     * 获取一个key的剩余存活时间
     *
     * @param key
     * @return
     */
    public long remainTimeToLive(String key) {
       return redissonClient.getBucket(key).remainTimeToLive();
    }

    /**
     * 获取一个key的值然后加1
     *
     * @param key
     * @return
     */
    public long getAndIncrement(String key) {
        return redissonClient.getAtomicLong(key).getAndIncrement();
    }

    /**
     * 获取一个key加1后的值
     *
     * @param key
     * @return
     */
    public long incrementAndGet(String key) {
        return redissonClient.getAtomicLong(key).incrementAndGet();
    }
}
