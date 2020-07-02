package com.liyz.dubbo.common.base.util;

import lombok.extern.slf4j.Slf4j;

import java.net.UnknownHostException;

/**
 * 注释:雪花算法util
 *
 *  wordId的比特位占据8位(0~255),这样只要在初始化对象时,
 * 把机器内网ip的最末尾的数用来当作workerId即可,
 * 比较容易的解决了微服务多机部署带来的workerId配置困难问题。
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/10/24 14:29
 */
@Slf4j
public class SnowflakeIdUtil {

    /**
     * 开始时间截 (2018-10-01)
     */
    private final long twepoch = 1538323200000L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 8L;

    /**
     * 数据标识id所占的位数
     */
    private final long datacenterIdBits = 2L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~255)
     */
    private long workerId;

    /**
     * 数据中心ID(0~3)
     */
    private long datacenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    public SnowflakeIdUtil() throws UnknownHostException {
        String localIp = IPUtil.getLocalIP();
        workerId = Integer.parseInt(localIp.substring(localIp.lastIndexOf('.') + 1));
        log.info("init Snowflake workerId : {}", workerId);
    }


    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }

    public long getMaxWorkerId() {
        return maxWorkerId;
    }

    public long getMaxDatacenterId() {
        return maxDatacenterId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    private synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String
                    .format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (datacenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    protected long getNextId() {
        return nextId();
    }
}
