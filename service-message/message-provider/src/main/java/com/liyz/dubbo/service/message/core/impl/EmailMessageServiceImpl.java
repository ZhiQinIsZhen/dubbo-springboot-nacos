package com.liyz.dubbo.service.message.core.impl;

import com.liyz.dubbo.service.message.bo.MessageBO;
import com.liyz.dubbo.service.message.constant.MessageEnum;
import com.liyz.dubbo.service.message.core.abs.AbstractMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 注释:邮件发送
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 10:33
 */
@Slf4j
@Service
public class EmailMessageServiceImpl extends AbstractMessageService {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;

    @Override
    protected boolean doSend(MessageBO messageBO) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            if (StringUtils.isNotBlank(messageBO.getAddress())) {
                mimeMessageHelper.setTo(messageBO.getAddress().split(","));
            }
            if (StringUtils.isNotBlank(messageBO.getSubject())) {
                mimeMessageHelper.setSubject(messageBO.getSubject());
            }
            if (StringUtils.isNotBlank(messageBO.getContent())) {
                mimeMessageHelper.setText(messageBO.getContent(), true);
            }
            if (!CollectionUtils.isEmpty(messageBO.getAttachmentUrls())) {
                //todo 附件
//                mimeMessageHelper.addAttachment();
            }
            javaMailSender.send(mimeMessage);
            log.info("email send success, code : {}", messageBO.getCode());
            return true;
        } catch (MessagingException e) {
            log.error("email send fail, error", e);
            return false;
        }
    }

    @Override
    public MessageEnum.MessageType getMessageType() {
        return MessageEnum.MessageType.EMAIL;
    }
}
