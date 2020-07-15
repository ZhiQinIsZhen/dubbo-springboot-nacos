package com.liyz.dubbo.service.message.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/6 11:24
 */
@Data
@Table(name = "msg_template")
public class MsgTemplateDO implements Serializable {
    private static final long serialVersionUID = 3568313728744635411L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Integer code;

    private Integer type;

    private String name;

    private String content;

    private String locale;
}
