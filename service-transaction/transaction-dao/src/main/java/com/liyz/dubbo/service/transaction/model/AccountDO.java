package com.liyz.dubbo.service.transaction.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/20 14:43
 */
@Setter
@Getter
@Table(name = "t_account")
public class AccountDO implements Serializable {
    private static final long serialVersionUID = -11129079730727113L;

    @Id
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    private Double amount;
}
