package com.liyz.dubbo.api.user.vo.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/6/15 16:13
 */
@Getter
@Setter
public class AuthLoginVO implements Serializable {
    private static final long serialVersionUID = 1353574234588192865L;

    @ApiModelProperty(value = "鉴权token")
    private String token;

    @ApiModelProperty(value = "Token失效时间戳(ms)")
    private Long expiration;
}
