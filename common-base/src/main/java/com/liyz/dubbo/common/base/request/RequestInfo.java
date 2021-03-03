package com.liyz.dubbo.common.base.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/3/3 15:39
 */
@Getter
@Setter
public class RequestInfo implements Serializable {
    private static final long serialVersionUID = -6097576238337192216L;

    private String requestId;

    private Long requestTime = System.currentTimeMillis();

    private String method;
}
