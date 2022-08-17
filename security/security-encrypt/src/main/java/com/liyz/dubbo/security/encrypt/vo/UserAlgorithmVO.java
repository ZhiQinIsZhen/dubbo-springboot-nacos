package com.liyz.dubbo.security.encrypt.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/17 11:23
 */
@Getter
@Setter
public class UserAlgorithmVO implements Serializable {
    private static final long serialVersionUID = -4876505488186063635L;

    private Long userId;

    private String algorithm;

    private Integer type;

    private String privateKey;

    private String publicKey;

    private String algorithmKey;

    private String iv;
}
