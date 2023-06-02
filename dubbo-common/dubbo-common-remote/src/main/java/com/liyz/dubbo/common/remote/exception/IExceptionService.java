package com.lyz.auth.common.remote.exception;

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
     * @return
     */
    String getCode();

    /**
     * 获取异常信息
     *
     * @return
     */
    String getMessage();
}
