package com.liyz.dubbo.common.socket.service.biz;

import com.liyz.dubbo.common.socket.constant.DataTypeService;

/**
 * 注释:业务请求接口
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/7/24 14:35
 */
public interface BizService<T, V> {

    T getBizData(V request);

    DataTypeService getDataType();
}
