package com.liyz.dubbo.api.web.dto.transaction;

import io.swagger.annotations.ApiModelProperty;
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
public class AccountDTO implements Serializable {
    private static final long serialVersionUID = -11129079730727113L;

    @ApiModelProperty(value = "id", example = "2012")
    private Integer id;

    @ApiModelProperty(value = "用户Id", example = "2012")
    private String userId;

    @ApiModelProperty(value = "金额", example = "2012")
    private Double amount;
}
