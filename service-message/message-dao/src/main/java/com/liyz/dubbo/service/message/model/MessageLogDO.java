package com.liyz.dubbo.service.message.model;

import lombok.Data;

import javax.persistence.*;
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
@Table(name = "message_log")
public class MessageLogDO implements Serializable {
    private static final long serialVersionUID = 7951605908404100316L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "biz_id")
    private String bizId;

    private Integer code;

    private Integer type;

    private String subject;

    private Integer success;

    private String content;

    private String address;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
