package com.liyz.dubbo.service.transaction.bo;

import lombok.Getter;
import lombok.Setter;

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
public class AccountBO implements Serializable {
    private static final long serialVersionUID = -11129079730727113L;

    private Integer id;

    private String userId;

    private Double amount;
}
