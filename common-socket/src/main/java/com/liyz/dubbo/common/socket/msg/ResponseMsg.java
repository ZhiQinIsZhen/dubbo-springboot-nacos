package com.liyz.dubbo.common.socket.msg;

import com.liyz.dubbo.common.base.util.JsonMapperUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:返回消息体
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 15:07
 */
@Getter
@Setter
@Builder
public class ResponseMsg<T> implements Serializable {
    private static final long serialVersionUID = -594990126735684969L;

    private int code;

    private String message;

    private DataMsg<T> data;

    public static ResponseMsg ok(DataMsg data) {
        return ok(null, data);
    }

    public static ResponseMsg ok(String message) {
        return ok(message, null);
    }

    public static ResponseMsg ok(String message, DataMsg data) {
        return ResponseMsg.builder().code(0).message(message).data(data).build();
    }

    public static ResponseMsg error(String message) {
        return error(message, null);
    }

    public static ResponseMsg error(String message, DataMsg data) {
        return ResponseMsg.builder().code(0).message(message).data(data).build();
    }

    @Override
    public String toString() {
        return JsonMapperUtil.toJSONString(this);
    }
}
