package com.liyz.dubbo.api.user.dto.test;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/7/3 15:48
 */
@Getter
@Setter
public class LimitDTO implements Serializable {
    private static final long serialVersionUID = 206672950086028531L;

    private Integer count;
}
