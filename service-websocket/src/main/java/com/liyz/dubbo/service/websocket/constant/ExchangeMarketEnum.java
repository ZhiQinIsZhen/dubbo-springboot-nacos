package com.liyz.dubbo.service.websocket.constant;

import lombok.Getter;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/2/1 15:01
 */
public enum ExchangeMarketEnum {

    /**
     * https://www.zb.cn/i/developer
     */
    ZB(1),

    /**
     * okex http://www.okex.com
     */
    OKEX(2),

    /**
     * 币安 https://www.binance.com/
     */
    BINANCE(3),

    /**
     * 火币网 https://www.huobi.com/zh-cn/
     */
    HUOBI_PRO(4),

    /**
     * bitfinex https://www.bitfinex.com/
     */
    BITFINEX(6),

    /**
     * https://api.hitbtc.com/
     */
    HITBTC(10),

    /**
     * https://www.upbit.com/
     */
    UPBIT(12),

    /**
     * https://apidoc.bit-z.pro/cn/
     */
    BITZ(17),

    /**
     * https://support.bittrex.com/hc/en-us/articles/115003723911
     */
    BITTREX(38),

    /**
     * https://www.kucoin.com/#/
     */
    KUCOIN(73),

    /**
     * https://support.bittrex.com/hc/en-us/articles/115003723911
     */
    EXX(92),

    /**
     * https://cn.bitforex.com/
     */
    BITFOREX(256),

    /**
     * https://www.58coin.com/
     */
    CIOIN58(257),

    /**
     *https://pro.coinbase.com/
     */
    COINBASE(260),

    /**
     * https://www.fcoin.com/
     */
    FCOIN(46),

    /**
     * https://gateio.io/
     */
    GATE(53),

    /**
     * https://www.bibox.com/
     */
    BIBOX(16),

    /**
     * https://www.bithumb.com/
     */
    BITHUMB(14);

    @Getter
    private final int code;

    ExchangeMarketEnum(int code) {
        this.code = code;
    }
}
