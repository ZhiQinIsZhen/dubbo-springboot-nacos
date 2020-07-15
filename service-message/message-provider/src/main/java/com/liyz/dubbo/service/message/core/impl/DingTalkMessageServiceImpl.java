package com.liyz.dubbo.service.message.core.impl;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.liyz.dubbo.service.message.bo.MessageBO;
import com.liyz.dubbo.service.message.constant.MessageEnum;
import com.liyz.dubbo.service.message.core.abs.AbstractMessageService;
import com.taobao.api.ApiException;
import jodd.cache.TimedCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * 注释:钉钉消息
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 15:17
 */
@Slf4j
@Service
public class DingTalkMessageServiceImpl extends AbstractMessageService {

    private static final String DING_TALK_URL = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2";

    @Value("${dingtable.app.key:123}")
    private String appKey;
    @Value("${dingtable.app.secret:123}")
    private String appSecret;

    @Override
    protected boolean doSend(MessageBO messageBO) {
        DingTalkClient client = new DefaultDingTalkClient(DING_TALK_URL);
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setUseridList(messageBO.getAddress());
        //todo 设置 agentId
        request.setAgentId(1111L);
        request.setToAllUser(false);
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("text");
        msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
        msg.getText().setContent(messageBO.getContent());
        request.setMsg(msg);
        OapiMessageCorpconversationAsyncsendV2Response response = null;
        try {
            response = client.execute(request, getAccessTokenTimedCache().get("accessToken"));
            log.info("send ding talk response:{}",response);
        } catch (ApiException e) {
            log.info("send ding talk fail , e:{}",e);
            return false;
        }
        return true;
    }

    private TimedCache<String, String> getAccessTokenTimedCache(){
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appKey);
        request.setAppsecret(appSecret);
        request.setHttpMethod(HttpMethod.GET.name());
        OapiGettokenResponse response = null;
        try {
            response = client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        TimedCache<String, String> accessTokenTimedCache = new TimedCache((response.getExpiresIn() - 60) * 1000);
        accessTokenTimedCache.put("accessToken", response.getAccessToken());
        return accessTokenTimedCache;
    }

    @Override
    public MessageEnum.MessageType getMessageType() {
        return MessageEnum.MessageType.DING_TALK;
    }
}
