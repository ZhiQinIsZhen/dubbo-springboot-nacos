package com.liyz.dubbo.service.message.core.impl;

import com.google.common.collect.Maps;
import com.liyz.dubbo.service.message.bo.MessageBO;
import com.liyz.dubbo.service.message.constant.MessageEnum;
import com.liyz.dubbo.service.message.core.abs.AbstractMessageService;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 注释:云片短息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 15:43
 */
@Slf4j
@Service
public class YunPianMessageServiceImpl extends AbstractMessageService {

    @Value("${yunpian.app.key:liyz}")
    private String yunpianAppKey;

    @Override
    protected boolean doSend(MessageBO messageBO) {
        YunpianClient yunpianClient = new YunpianClient(yunpianAppKey).init();
        Map<String, String> params = Maps.newHashMap();
        params.put(YunpianClient.MOBILE, messageBO.getAddress());
        //todo 确认 tpl_id
        params.put(YunpianClient.TPL_ID, messageBO.getCode().toString());
        params.put(YunpianClient.TPL_VALUE, getTplValue(messageBO.getParams()));
        Properties properties = new Properties();
        properties.setProperty("yp.version", YunpianClient.VERSION_V1);
        yunpianClient.getConf().with(properties);
        Result<SmsSingleSend> smsSingleSendResult = yunpianClient.init().sms().tpl_send(params);
        log.info("smsSingleSendResult:{}", smsSingleSendResult);
        return true;
    }

    @Override
    public MessageEnum.MessageType getMessageType() {
        return MessageEnum.MessageType.MOBILE;
    }

    /**
     * 设置tpl格式
     *
     * @param values
     * @return
     */
    private static String getTplValue(Map<String, Object> values) {
        boolean isFirst = true;
        StringBuilder builder = new StringBuilder();
        for (Iterator<String> keys = values.keySet().iterator(); keys.hasNext(); ) {
            String key = keys.next();
            if (isFirst) {
                builder.append("#").append(key).append("#").append("=").append(values.get(key));
                isFirst = false;
            } else {
                builder.append("&").append("#").append(key).append("#").append("=").append(values.get(key));
            }
        }
        return builder.toString();
    }
}
