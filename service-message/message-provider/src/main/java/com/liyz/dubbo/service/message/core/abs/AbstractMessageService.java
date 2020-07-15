package com.liyz.dubbo.service.message.core.abs;

import com.liyz.dubbo.common.base.util.CommonConverterUtil;
import com.liyz.dubbo.common.base.util.DateUtil;
import com.liyz.dubbo.common.base.util.SpringContextUtil;
import com.liyz.dubbo.common.remote.exception.RemoteServiceException;
import com.liyz.dubbo.common.remote.exception.enums.CommonCodeEnum;
import com.liyz.dubbo.service.message.bo.MessageBO;
import com.liyz.dubbo.service.message.constant.MessageEnum;
import com.liyz.dubbo.service.message.core.service.MessageService;
import com.liyz.dubbo.service.message.model.MessageLogDO;
import com.liyz.dubbo.service.message.model.MsgTemplateDO;
import com.liyz.dubbo.service.message.service.MessageLogService;
import com.liyz.dubbo.service.message.service.MsgTemplateService;
import com.liyz.dubbo.service.message.util.MessageCacheUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.io.StringWriter;
import java.util.Objects;

/**
 * 注释:消息发送抽象类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 9:36
 */
@Slf4j
public abstract class AbstractMessageService implements MessageService {

    private MsgTemplateService msgTemplateService;
    private MessageLogService messageLogService;

    public AbstractMessageService() {
        msgTemplateService = SpringContextUtil.getBean("msgTemplateService", MsgTemplateService.class);
        if (msgTemplateService == null) {
            throw new NoSuchBeanDefinitionException("no bean msgTemplateService");
        }
        messageLogService = SpringContextUtil.getBean("messageLogService", MessageLogService.class);
        if (messageLogService == null) {
            throw new NoSuchBeanDefinitionException("no bean messageLogService");
        }
    }

    /**
     * 发送消息
     *
     * @param messageBO
     * @return
     */
    @Override
    public boolean send(MessageBO messageBO) {
        if (Objects.isNull(messageBO) || Objects.isNull(messageBO.getType())) {
            throw new NullPointerException();
        }
        MessageEnum.MessageType messageType = MessageEnum.MessageType.getByCode(messageBO.getType());
        if (messageType != getMessageType()) {
            throw new IllegalArgumentException("messageType not match!");
        }
        //获取消息模板
        boolean success = getMsgTemplate(messageBO);
        if (!success) {
            return false;
        }
        //将消息参数写入模板中
        messageBO.setContent(writeParams(messageBO));
        //发送消息
        success = doSend(messageBO);
        //记录消息发送结果
        recordMsgLog(messageBO, success);
        return success;
    }

    /**
     * 获取消息模板
     *
     * @param messageBO
     * @return
     */
    private boolean getMsgTemplate(MessageBO messageBO) {
        MsgTemplateDO queryDO = new MsgTemplateDO();
        queryDO.setCode(messageBO.getCode());
        queryDO.setType(messageBO.getType());
        queryDO.setLocale(messageBO.getLocale());
        MsgTemplateDO msgTemplateDO = MessageCacheUtil.getMsgTemplate(queryDO);
        if (Objects.isNull(msgTemplateDO)) {
            msgTemplateDO = msgTemplateService.getOne(queryDO);
            if (Objects.isNull(msgTemplateDO)) {
                log.error("email template not exist, code:{},locale:{}", messageBO.getCode(), messageBO.getLocale());
                return false;
            }
            MessageCacheUtil.pubMsgTemplate(msgTemplateDO);
        }
        if (StringUtils.isBlank(messageBO.getSubject())) {
            messageBO.setSubject(msgTemplateDO.getName());
        }
        messageBO.setContent(msgTemplateDO.getContent());
        return true;
    }

    protected abstract boolean doSend(MessageBO messageBO);

    /**
     * 记录消息发送日志
     *
     * @param messageBO
     * @param success
     */
    private void recordMsgLog(MessageBO messageBO, boolean success) {
        MessageLogDO messageLogDO = CommonConverterUtil.beanCopy(messageBO, MessageLogDO.class);
        messageLogDO.setSuccess(success ? 1 : 0);
        messageLogDO.setCreateTime(DateUtil.currentDate());
        messageLogDO.setUpdateTime(DateUtil.currentDate());
        messageLogService.save(messageLogDO);
    }

    /**
     * 将参数写入内容中
     *
     * @param messageBO
     */
    private String writeParams(MessageBO messageBO) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        configuration.setTemplateLoader(templateLoader);
        configuration.setDefaultEncoding("UTF-8");
        StringWriter stringWriter = new StringWriter();
        Template template;
        try {
            template = new Template(messageBO.getCode().toString(), messageBO.getContent(), configuration);
            template.process(messageBO.getParams(), stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            log.error("模板消息写入参数错误",e);
            throw new RemoteServiceException(CommonCodeEnum.ParameterError);
        }
    }
}
