package com.liyz.dubbo.service.websocket.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/5/27 14:59
 */
@Getter
@Setter
public class Kline implements Serializable {
    private static final long serialVersionUID = -2174619345707217538L;

    private String ch;

    private Long ts;

    private Tick tick;
}
