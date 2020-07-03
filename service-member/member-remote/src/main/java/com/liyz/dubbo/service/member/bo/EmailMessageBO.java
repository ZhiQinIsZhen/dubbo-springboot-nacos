package com.liyz.dubbo.service.member.bo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/6 10:12
 */
@Getter
@Setter
public class EmailMessageBO implements Serializable {
    private static final long serialVersionUID = -7688579438072180123L;

    @NotNull(groups = {Email.class}, message = "邮件模板编号不能为空")
    private Integer code;

    @NotBlank(groups = {Email.class}, message = "收件人邮箱不能为空")
    private String address;

    @NotBlank(groups = {Email.class}, message = "邮件主题不能为空")
    private String subject;

    @NotBlank(groups = {Email.class}, message = "模板语言类型不能为空")
    private String locale;

    private List<String> params;

    private String content;

    public interface Email {}
}
