package com.liyz.dubbo.common.socket.msg;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:数据消息体
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 15:15
 */
@Getter
@Setter
@Builder
public class DataMsg<T> implements Serializable {
    private static final long serialVersionUID = -3356073865844234625L;

    private String biz;

    private String action;

    private String op;

    private List<T> data;
}
