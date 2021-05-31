package com.liyz.dubbo.service.websocket.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/5/27 15:00
 */
@Getter
@Setter
public class Tick implements Serializable {
    private static final long serialVersionUID = -4004555979911105874L;

    private Long id;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal low;

    private BigDecimal high;

    private BigDecimal amount;

    private BigDecimal vol;

    private BigDecimal count;
}
