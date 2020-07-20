package com.liyz.dubbo.service.message.provider;

import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.service.message.bo.MessageLogBO;
import com.liyz.dubbo.service.message.model.MessageLogDO;
import com.liyz.dubbo.service.message.remote.RemoteMessageLogService;
import com.liyz.dubbo.service.message.service.MessageLogService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/20 18:05
 */
@Slf4j
@DubboService(version = "1.0.0")
public class RemoteMessageLogServiceImpl implements RemoteMessageLogService {

    @Autowired
    MessageLogService messageLogService;

    @Override
    public MessageLogBO insert(MessageLogBO messageLogBO) {
        String xid = RootContext.getXID();
        log.info("xid : {}", xid);
        int count = messageLogService.save(CommonConverterUtil.beanCopy(messageLogBO, MessageLogDO.class));
        log.info("insert count : {}", count);
        return messageLogBO;
    }
}
