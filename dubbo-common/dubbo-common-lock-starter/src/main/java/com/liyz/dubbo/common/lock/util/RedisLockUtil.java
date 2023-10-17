package com.liyz.dubbo.common.lock.util;

import com.liyz.dubbo.common.lock.exception.LockException;
import com.liyz.dubbo.common.lock.exception.LockExceptionCodeEnum;
import com.liyz.dubbo.common.lock.service.LockInterface;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/10/12 17:17
 */
@Slf4j
public class RedisLockUtil {

    private static RedissonClient REDISSON_CLIENT;

    public static void setRedissonClient(RedissonClient redissonClient) {
        REDISSON_CLIENT = redissonClient;
    }

    /**
     * 非公平加锁
     *
     * @param key 锁的key
     * @param func 方法
     * @return 返回结果
     * @param <T> 泛型
     */
    public static <T> T lock(String key, LockInterface func) {
        return lock(key, 1, TimeUnit.MINUTES, func, false);
    }

    /**
     * 非公平加锁
     *
     * @param key 锁的key
     * @param leaseTime 加锁时间
     * @param unit 单位
     * @param func 方法
     * @param heldThrow 未持有是否抛异常
     * @return 返回结果
     * @param <T> 泛型
     */
    public static <T> T lock(String key, long leaseTime, TimeUnit unit, LockInterface func, boolean heldThrow) {
        RLock rLock = REDISSON_CLIENT.getLock(key);
        try {
            rLock.lock(leaseTime, unit);
            return (T) func.callBack();
        } finally {
            if (rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            } else if (heldThrow) {
                throw new LockException(LockExceptionCodeEnum.NOT_HELD_LOCK);
            }
        }
    }

    /**
     * 公平加锁
     *
     * @param key 锁的key
     * @param func 方法
     * @return 返回结果
     * @param <T> 泛型
     */
    public static <T> T fairLock(String key, LockInterface func) {
        return fairLock(key, 1, TimeUnit.MINUTES, func, false);
    }

    /**
     * 公平加锁
     *
     * @param key 锁的key
     * @param leaseTime 加锁时间
     * @param unit 单位
     * @param func 方法
     * @param heldThrow 未持有是否抛异常
     * @return 返回结果
     * @param <T> 泛型
     */
    public static <T> T fairLock(String key, long leaseTime, TimeUnit unit, LockInterface func, boolean heldThrow) {
        RLock rLock = REDISSON_CLIENT.getFairLock(key);
        try {
            rLock.lock(leaseTime, unit);
            return (T) func.callBack();
        } finally {
            if (rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            } else if (heldThrow) {
                throw new LockException(LockExceptionCodeEnum.NOT_HELD_LOCK);
            }
        }
    }
}
