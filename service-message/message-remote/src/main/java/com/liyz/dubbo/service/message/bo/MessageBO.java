package com.liyz.dubbo.service.message.bo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 注释:消息实体类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/6 10:12
 */
@Getter
@Setter
public class MessageBO implements Serializable {
    private static final long serialVersionUID = -7688579438072180123L;

    @NotNull(groups = {Mobile.class, Email.class}, message = "模板编号不能为空")
    private Integer code;

    @NotNull(groups = {Mobile.class, Email.class}, message = "消息类型不能为空")
    private Integer type;

    @NotBlank(groups = {Mobile.class, Email.class}, message = "收件人地址不能为空")
    private String address;

    private String subject;

    @NotBlank(groups = {Mobile.class, Email.class}, message = "模板语言类型不能为空")
    private String locale;

    private Map<String, Object> params;

    private String content;

    //附件地址
    private List<String> attachmentUrls;

    public interface Mobile {}

    public interface Email {}
}
