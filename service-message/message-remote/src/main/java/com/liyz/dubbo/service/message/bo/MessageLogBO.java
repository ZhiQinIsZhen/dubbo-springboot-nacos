package com.liyz.dubbo.service.message.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/15 16:43
 */
@Data
public class MessageLogBO implements Serializable {
    private static final long serialVersionUID = 7951605908404100316L;

    private Integer id;

    private String bizId;

    private Integer code;

    private Integer type;

    private String subject;

    private Integer success;

    private String content;

    private String address;

    private Date createTime;

    private Date updateTime;
}
