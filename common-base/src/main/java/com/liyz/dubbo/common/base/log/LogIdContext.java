package com.liyz.dubbo.common.base.log;

import org.apache.dubbo.common.threadlocal.InternalThreadLocal;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/12/25 15:11
 */
public class LogIdContext {

    private static final InternalThreadLocal<String> LOCAL = new InternalThreadLocal<>();

    protected LogIdContext() {}

    /**
     * 获取logId
     *
     * @return
     */
    public static String getLogId() {
        return LOCAL.get();
    }

    public static void setLogId(String logId) {
        LOCAL.set(logId);
    }

    /**
     * 移除logId
     */
    public static void removeLogId() {
        LOCAL.remove();
    }
}
