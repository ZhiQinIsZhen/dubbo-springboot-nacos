package com.liyz.dubbo.service.message.remote;

import com.liyz.dubbo.service.message.bo.MessageBO;

import javax.validation.constraints.NotNull;

/**
 * 注释:消息服务对外暴露接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 15:06
 */
public interface RemoteMessageService {

    boolean send(@NotNull MessageBO messageBO);
}
