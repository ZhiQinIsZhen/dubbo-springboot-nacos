package com.liyz.dubbo.common.remote.exception;

/**
 * Desc:Base interface
 *
 * @author lyz
 * @version 1.0.0
 * @date 2023/3/8 15:37
 */
public interface IExceptionService {

    /**
     * 获取异常code
     *
     * @return code
     */
    String getCode();

    /**
     * 获取异常信息
     *
     * @return 异常信息
     */
    String getMessage();
}
