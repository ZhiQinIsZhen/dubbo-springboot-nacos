package com.liyz.dubbo.service.websocket.constant;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2021/2/1 15:18
 */
public interface HuoBiTopicConstant {

    /**
     * K线订阅
     */
    static String KLINE_SUB = "market.%s.kline.%s";

    /**
     * 交易深度
     */
    static String MARKET_DEPTH_SUB = "market.%s.depth.step0";

    /**
     * 交易行情
     */
    static String MARKET_TRADE_SUB = "market.%s.trade.detail";

    /**
     * 行情
     */
    static String MARKET_DETAIL_SUB = "market.%s.detail";

    /**
     * K线交易周期
     */
    static String[] PERIOD = {"1min" , "5min", "15min", "30min", "60min", "1day", "1mon", "1week", "1year"};
}
