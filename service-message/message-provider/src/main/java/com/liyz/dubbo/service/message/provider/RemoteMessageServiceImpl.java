package com.liyz.dubbo.service.message.provider;

import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import com.liyz.dubbo.service.message.bo.MessageBO;
import com.liyz.dubbo.service.message.core.service.MessageService;
import com.liyz.dubbo.service.message.remote.RemoteMessageService;
import com.liyz.dubbo.service.message.util.MessageServiceUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 注释:消息发送实现类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 15:07
 */
@Valid
@DubboService(version = "1.0.0")
public class RemoteMessageServiceImpl implements RemoteMessageService {

    @Override
    public boolean send(@NotNull @Validated({MessageBO.Email.class, MessageBO.Mobile.class}) MessageBO messageBO) {
        MessageService messageService = MessageServiceUtil.getServiceByType(messageBO.getType());
        if (messageService == null) {
            throw new RemoteServiceException(CommonCodeEnum.ParameterError);
        }
        return messageService.send(messageBO);
    }
}
