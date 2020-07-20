package com.liyz.dubbo.service.message.remote;

import com.liyz.dubbo.service.message.bo.MessageLogBO;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/20 18:04
 */
public interface RemoteMessageLogService {

    MessageLogBO insert(MessageLogBO messageLogBO);
}
